package com.example.app.fragments.descripcionFragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.app.R
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
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentDescripcionBinding.inflate(inflater, container, false)
        mAgendaViewModel = ViewModelProvider(this).get(AgendaViewModel::class.java)
        binding.toolbar2.setTitle(args.currentItem.nombreTarea).toString()
        binding.toolbar2.inflateMenu(R.menu.descripcion_menu)
        binding.tvFinal.setText(args.currentItem.finalizaTarea).toString()
        binding.tvDescripcionTarea.setText(args.currentItem.contenidoTarea).toString()

        return binding.root
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.descripcion_menu, menu)
    }
*/
    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }
}