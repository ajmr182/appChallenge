package com.example.app.fragments.agandaFragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.R
import com.example.app.databinding.FragmentAgendaBinding
import com.example.app.model.Tarea
import com.example.app.viewModel.AgendaViewModel
import java.text.SimpleDateFormat
import java.util.*

class AgendaFragment : Fragment() {

    private lateinit var agendaViewModel: AgendaViewModel
    private var _binding: FragmentAgendaBinding? = null
    private val binding get() = _binding!!
    private var tareasList = listOf<Tarea>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAgendaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        agendaViewModel = ViewModelProvider(this).get(AgendaViewModel::class.java)
        agendaViewModel.getAllTareas()
        val adapter = AgendaAdpter(::setStatusTarea)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        agendaViewModel = ViewModelProvider(this).get(AgendaViewModel::class.java)
        agendaViewModel.readAllData.observe(viewLifecycleOwner, { lista ->
            adapter.setData(lista)
            tareasList = lista
        })

        binding.floatingActionButton.setOnClickListener {

            val alertDialog = AlertDialog.Builder(context)
            val content: View =
                LayoutInflater.from(getContext()).inflate(R.layout.add_alert_dialog, null)
            alertDialog.setView(content)
            alertDialog.setPositiveButton("Aceptar") { dialog, wich ->

                val titulo = content.findViewById<EditText>(R.id.etTitulo).text.toString()
                val descripcion = content.findViewById<EditText>(R.id.etContenido).text.toString()
                val finaliza = content.findViewById<EditText>(R.id.etFinaliza).text.toString()

                if (inputCheck(titulo, descripcion, finaliza)) {

                    val tarea = Tarea(
                        0, titulo, descripcion, finaliza, getCurrentDate(), false,
                      arrayListOf()
                    )
                    agendaViewModel.addTarea(tarea)
                    Toast.makeText(requireContext(), "Guardado", Toast.LENGTH_LONG).show()
                } else {

                    Toast.makeText(requireContext(), "Llena todos tus datos", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            alertDialog.setNegativeButton("Cancelar") { dialog, wich ->

            }
            alertDialog.show()
        }

        binding.ivFiltrarTareas.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {

                val listaFiltrada = tareasList.filter { !it.tareaEstaLista }
                adapter.getFilteredData(listaFiltrada)

            } else {

                adapter.setData(tareasList)
            }
        }
    }

    private fun getCurrentDate(): String {

        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        return formatter.format(Date(Date().time))
    }

    private fun setStatusTarea(tarea: Tarea) {

        agendaViewModel.updateTarea(tarea)
        agendaViewModel.getAllTareas()
    }

    private fun inputCheck(titulo: String, descripcion: String, finaliza: String): Boolean {

        return !(TextUtils.isEmpty(titulo) || TextUtils.isEmpty(descripcion) || TextUtils.isEmpty(
            finaliza
        ))
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }
}