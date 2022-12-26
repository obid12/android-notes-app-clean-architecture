package com.obidia.testagrii.presentation.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.obidia.testagrii.R
import com.obidia.testagrii.data.entity.NoteEntity
import com.obidia.testagrii.databinding.FragmentUpdateDataBinding
import com.obidia.testagrii.presentation.listnote.NoteViewModel

@Suppress("DEPRECATION")
class UpdateDataFragment(private val noteEntity: NoteEntity?) : DialogFragment() {

    private val noteViewModel by viewModels<NoteViewModel>()
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
        binding.floatBtn.setOnClickListener {
            updateDataToDatabase()
        }

        binding.selesai.setOnClickListener {
            if (noteEntity?.selesai == true){
                noteViewModel.setSelesai(false)
                noteViewModel.updateUser(NoteEntity(
                    noteEntity.id, noteEntity.acktivitas,noteEntity.detail,noteEntity.kategori,
                    noteViewModel.selesai.value!!
                ))
            }else{
                noteViewModel.setSelesai(true)
                noteViewModel.updateUser(NoteEntity(
                    noteEntity?.id!!, noteEntity.acktivitas,noteEntity.detail,noteEntity.kategori,
                    noteViewModel.selesai.value!!
                ))
            }
            dialog?.dismiss()

        }

        binding.selesai.apply {
            if (noteEntity?.selesai == true) {
                text = "Telah dikerjakan"
                setTextColor(resources.getColor(R.color.teal_200))
                }
            else {
                text ="tandai selesai"
                setTextColor(resources.getColor(R.color.purple_200))
            }


        }


        binding.etNoteTitle.setText(noteEntity?.acktivitas)
        binding.etNoteBody.setText(noteEntity?.detail)
        binding.etKategory.setText(noteEntity?.kategori)
        return binding.root


    }

    private fun updateDataToDatabase() {
        val title = binding.etNoteTitle.text.toString()
        val category = binding.etKategory.text.toString()
        val detail = binding.etNoteBody.text.toString()

        if (inputCheck(title, category, detail)) {
            // Create User Object
            val updatedUser =
                NoteEntity(noteEntity?.id!!, title, detail, category, noteEntity.selesai)
            // Update Current User
            noteViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            dialog?.dismiss()
            // Navigate Back
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, catgeory: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(
            catgeory
        ))
    }

}