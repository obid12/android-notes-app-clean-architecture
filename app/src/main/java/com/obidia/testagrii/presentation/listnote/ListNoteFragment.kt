package com.obidia.testagrii.presentation.listnote

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.obidia.testagrii.databinding.FragmentListNoteBinding
import com.obidia.testagrii.presentation.inputdata.InputDataFragment
import com.obidia.testagrii.presentation.update.UpdateDataFragment

class ListNoteFragment : Fragment() {

    private lateinit var binding: FragmentListNoteBinding
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListNoteBinding.inflate(inflater, container, false)
        val adapter = ListAdapter(ListAdapter.OnClick {
            val dialogFragment = UpdateDataFragment(it)
            val fragmentManager = childFragmentManager
            dialogFragment.show(fragmentManager, dialogFragment::class.java.simpleName)
        })
        binding.rv.adapter = adapter
        binding.rv.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        noteViewModel.readAllData.observe(viewLifecycleOwner) { note ->
            adapter.submitData(note)
        }



        binding.floatBtn.setOnClickListener {
            val dialogFragment = InputDataFragment()
            val fragmentManager = childFragmentManager
            dialogFragment.show(fragmentManager, dialogFragment::class.java.simpleName)
        }

        return binding.root
    }


}