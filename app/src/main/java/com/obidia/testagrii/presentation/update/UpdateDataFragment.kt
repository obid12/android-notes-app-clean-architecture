package com.obidia.testagrii.presentation.update

import android.app.AlertDialog
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
import com.obidia.testagrii.R
import com.obidia.testagrii.databinding.FragmentUpdateDataBinding
import com.obidia.testagrii.domain.entity.NoteEntity
import com.obidia.testagrii.presentation.listnote.NoteViewModel
import com.obidia.testagrii.presentation.listnote.UiEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UpdateDataFragment(private val noteEntity: NoteEntity?) : DialogFragment() {

    private val noteViewModel by activityViewModels<NoteViewModel>()
    private var _binding: FragmentUpdateDataBinding? = null
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
        _binding = FragmentUpdateDataBinding.inflate(inflater, container, false)

        binding.floatBtnDelete.setOnClickListener {
            dialogDelete()
        }
        binding.floatBtn.setOnClickListener {
            updateDataToDatabase()
        }

        binding.selesai.setOnClickListener {
            noteEntity?.acktivitas?.let { it1 -> noteViewModel.setAktivitas(it1) }
            noteEntity?.detail?.let { it1 -> noteViewModel.setDetail(it1) }
            noteEntity?.kategori?.let { it1 -> noteViewModel.setKategori(it1) }
            if (noteEntity?.selesai == true) {
                noteViewModel.setSelesai(false)
                noteViewModel.setColor(Color.RED)
                noteViewModel.setId(noteEntity.id)
                noteViewModel.updateUser()
            } else {
                noteViewModel.setSelesai(true)
                noteViewModel.setColor(Color.GREEN)
                noteEntity?.id?.let { id -> noteViewModel.setId(id) }
                noteViewModel.updateUser()
            }
            dialog?.dismiss()

        }

        binding.selesai.apply {
            if (noteEntity?.selesai == true) {
                text = context.getString(R.string.telah_dikerjakan)
            } else {
                text = "tandai selesai"
            }
        }
        binding.etNoteTitle.setText(noteEntity?.acktivitas)
        binding.etNoteBody.setText(noteEntity?.detail)
        binding.etKategory.setText(noteEntity?.kategori)
        return binding.root


    }

    private fun dialogDelete() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->

            noteViewModel.deleteUser(noteEntity!!)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${noteEntity.acktivitas}",
                Toast.LENGTH_SHORT
            ).show()
            dialog?.dismiss()

        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${noteEntity?.acktivitas}?")
        builder.setMessage("Are you sure you want to delete ${noteEntity?.acktivitas}?")
        builder.create().show()
    }

    private fun updateDataToDatabase() {
        noteViewModel.apply {
            setAktivitas(binding.etNoteTitle.text.toString())
            setDetail(binding.etNoteBody.text.toString())
            setKategori(binding.etKategory.text.toString())
            noteEntity?.id?.let { setId(it) }
            noteEntity?.color?.let { setColor(it) }
            noteEntity?.selesai?.let { setSelesai(it) }
            updateUser()
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