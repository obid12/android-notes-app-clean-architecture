package com.obidia.testagrii.presentation.inputdata

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.obidia.testagrii.data.entity.NoteEntity
import com.obidia.testagrii.databinding.FragmentInputDataBinding
import com.obidia.testagrii.presentation.listnote.NoteViewModel


class InputDataFragment : DialogFragment() {

    private val noteViewModel by viewModels<NoteViewModel>()
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
        val title = binding.etNoteTitle.text.toString()
        val category = binding.etKategory.text.toString()
        val detail = binding.etNoteBody.text.toString()


        if (inputCheck(title, category, detail)) {
            // Create User Object
            val user = NoteEntity(0, title, detail, category, false)
            // Add Data to Database
            noteViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            dialog?.dismiss()

        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, catgeory: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(
            catgeory
        ))
    }

}


