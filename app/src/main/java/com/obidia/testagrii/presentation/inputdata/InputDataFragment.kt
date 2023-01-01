package com.obidia.testagrii.presentation.inputdata

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.obidia.testagrii.databinding.FragmentInputDataBinding
import com.obidia.testagrii.presentation.listnote.NoteViewModel
import com.obidia.testagrii.presentation.listnote.UiEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class InputDataFragment : DialogFragment() {

    private val noteViewModel by activityViewModels<NoteViewModel>()
    private var _binding: FragmentInputDataBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInputDataBinding.inflate(inflater, container, false)

        binding.floatBtn.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root
    }


    private fun insertDataToDatabase() {
        noteViewModel.apply {
            setAktivitas(binding.etNoteTitle.text.toString())
            setDetail(binding.etNoteBody.text.toString())
            setKategori(binding.etKategory.text.toString())
            setColor(Color.RED)
            setSelesai(false)
            noteViewModel.addUser()
        }
        observe()
    }

    private fun observe() {
        noteViewModel.eventFlow.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: UiEvent) {
        when (state) {
            is UiEvent.SaveNote -> {
                Toast.makeText(requireContext(), "Input Data Berhasil", Toast.LENGTH_LONG).show()
                dialog?.dismiss()
            }
            is UiEvent.ShowSnackbar -> {
                Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


}


