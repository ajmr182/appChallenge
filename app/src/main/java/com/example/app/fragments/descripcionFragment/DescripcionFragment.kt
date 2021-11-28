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
import com.example.app.R
import com.example.app.databinding.FragmentDescripcionBinding
import com.example.app.model.Tarea
import com.example.app.viewModel.AgendaViewModel

class DescripcionFragment : Fragment() {

    private val args by navArgs<DescripcionFragmentArgs>()
    private lateinit var mAgendaViewModel: AgendaViewModel
    private var _binding: FragmentDescripcionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentDescripcionBinding.inflate(inflater, container, false)
        mAgendaViewModel = ViewModelProvider(this).get(AgendaViewModel::class.java)
        binding.tvFinal.setText(args.currentItem.finalizaTarea).toString()
        binding.tvDescripcionTarea.setText(args.currentItem.contenidoTarea).toString()
        binding.toolbar2.title = args.currentItem.nombreTarea
        binding.toolbar2.inflateMenu(R.menu.descripcion_menu)
        binding.toolbar2.setOnMenuItemClickListener {

            when (it.itemId) {

                R.id.edit -> {

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
                    finaliza.setText(args.currentItem.finalizaTarea)

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
                                finaliza.text.toString()
                            )
                            mAgendaViewModel.updateTarea(updateTarea)
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
                    true
                }

                R.id.delete -> {

                    val deleteTarea = Tarea(
                        args.currentItem.id,
                        args.currentItem.nombreTarea,
                        args.currentItem.contenidoTarea,
                        args.currentItem.finalizaTarea
                    )
                    mAgendaViewModel.deleteTarea(deleteTarea)
                    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)


                    true
                }
                else -> {

                    false
                }
            }
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