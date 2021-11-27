package com.example.app.fragments.descripcionFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.app.databinding.FragmentDescripcionBinding
import com.example.app.viewModel.AgendaViewModel

class DescripcionFragment : Fragment() {

    private val args by navArgs<DescripcionFragmentArgs>()
    private lateinit var mAgendaViewModel: AgendaViewModel
    private var _binding: FragmentDescripcionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDescripcionBinding.inflate(inflater, container, false)
        mAgendaViewModel = ViewModelProvider(this).get(AgendaViewModel::class.java)
        binding.tvFinal.setText(args.currentItem.finalizaTarea).toString()
        binding.tvDescripcionTarea.setText(args.currentItem.contenidoTarea).toString()

        return binding.root
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }
}