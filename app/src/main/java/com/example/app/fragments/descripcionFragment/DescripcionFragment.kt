package com.example.app.fragments.descripcionFragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.R
import com.example.app.databinding.FragmentDescripcionBinding
import com.example.app.model.Tarea
import com.example.app.viewModel.AgendaViewModel

class DescripcionFragment : Fragment() {

    private val args by navArgs<DescripcionFragmentArgs>()
    private lateinit var agendaViewModel: AgendaViewModel
    private var _binding: FragmentDescripcionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescripcionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setHasOptionsMenu(true)
        agendaViewModel = ViewModelProvider(this).get(AgendaViewModel::class.java)
        val adapter = ComentariosAdapter()
        val rvComentarios = binding.rvComentarios
        val cleanList = args.currentItem.comentarios.dropWhile { it == "[]" }.toMutableList()
        rvComentarios.layoutManager = LinearLayoutManager(requireContext())
        rvComentarios.adapter = adapter
        adapter.setData(cleanList)

        binding.tvFinal.text = "Finaliza: ${args.currentItem.fechaFinal}"
        binding.tvDescripcionTarea.text = args.currentItem.contenidoTarea
        binding.tvFecha.text = "Creada: ${args.currentItem.fechaInicio}"
        binding.toolbar2.title = args.currentItem.nombreTarea
        binding.toolbar2.inflateMenu(R.menu.descripcion_menu)
        binding.toolbar2.setOnMenuItemClickListener {

            when (it.itemId) {

                R.id.edit -> {

                    alerDialogEdit()
                }
                R.id.delete -> {

                    alertDialogDelete()
                }
                else -> {

                    false
                }
            }
        }
        binding.btnEnviar.setOnClickListener {

            adapter.addComentario(binding.etComentarioAgregar.text.toString())
            binding.etComentarioAgregar.setText("")
            val tarea = Tarea(
                args.currentItem.id,
                args.currentItem.nombreTarea,
                args.currentItem.contenidoTarea,
                args.currentItem.fechaFinal,
                args.currentItem.fechaInicio,
                args.currentItem.tareaEstaLista,
                cleanList
            )
            agendaViewModel.updateTarea(tarea)
        }
    }

    private fun alertDialogDelete(): Boolean {

        val alerDialogD = AlertDialog.Builder(context)
            .setTitle("Borrar Tarea")
            .setMessage("Estas seguro que deseas borrar la tarea?")
            .setPositiveButton("Aceptar") { dialog, wich ->

                val deleteTarea = Tarea(
                    args.currentItem.id,
                    args.currentItem.nombreTarea,
                    args.currentItem.contenidoTarea,
                    args.currentItem.fechaFinal,
                    "0",
                    false,
                    args.currentItem.comentarios
                )
                agendaViewModel.deleteTarea(deleteTarea)
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
            .setNegativeButton("Cancerlar") { dialog, wich ->

            }
        alerDialogD.show()
        return true
    }

    private fun alerDialogEdit(): Boolean {

        val alertDialog = AlertDialog.Builder(context)
        val content: View =
            LayoutInflater.from(getContext())
                .inflate(R.layout.update_alert_dialog, null)
        alertDialog.setView(content)

        val titulo = content.findViewById<EditText>(R.id.etTitulo)
        titulo.setText(args.currentItem.nombreTarea)
        val descripcion = content.findViewById<EditText>(R.id.etContenido)
        descripcion.setText(args.currentItem.contenidoTarea)
        val finaliza = content.findViewById<EditText>(R.id.etFinaliza)
        finaliza.setText(args.currentItem.fechaFinal)

        alertDialog.setPositiveButton("Aceptar") { dialog, wich ->

            if (inputCheck(
                    titulo.text.toString(),
                    descripcion.text.toString(),
                    finaliza.text.toString()
                )
            ) {

                val updateTarea = Tarea(
                    args.currentItem.id,
                    titulo.text.toString(),
                    descripcion.text.toString(),
                    finaliza.text.toString(),
                    args.currentItem.fechaInicio,
                    false,
                    mutableListOf()
                )
                agendaViewModel.updateTarea(updateTarea)
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            } else {

                Toast.makeText(
                    requireContext(),
                    "Llena todos los datos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        alertDialog.setNegativeButton("Cancelar") { dialog, wich ->
        }
        alertDialog.show()
        return true
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