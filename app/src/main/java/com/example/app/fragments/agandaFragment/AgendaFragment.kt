package com.example.app.fragments.agandaFragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.R
import com.example.app.databinding.FragmentAgendaBinding
import com.example.app.model.Tarea
import com.example.app.viewModel.AgendaViewModel


class AgendaFragment : Fragment() {
    private lateinit var mProductosViewModel: AgendaViewModel
    private var _binding: FragmentAgendaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAgendaBinding.inflate(inflater, container, false)
        mProductosViewModel = ViewModelProvider(this).get(AgendaViewModel::class.java)
        var adapter=AgendaAdpter()
        val recyclerView=binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        mProductosViewModel = ViewModelProvider(this).get(AgendaViewModel::class.java)
        mProductosViewModel.readAllData.observe(viewLifecycleOwner, { user -> adapter.setData(user)
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

                    val tarea = Tarea(0, titulo, descripcion, finaliza)
                    mProductosViewModel.addTarea(tarea)
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
        return binding.root
    }

    private fun inputCheck(titulo: String, descripcion: String, finaliza: String): Boolean {
        return !(TextUtils.isEmpty(titulo) && TextUtils.isEmpty(descripcion) && TextUtils.isEmpty(
            finaliza
        ))
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }
}