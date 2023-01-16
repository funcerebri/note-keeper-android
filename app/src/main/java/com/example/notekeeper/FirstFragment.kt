package com.example.notekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.notekeeper.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.listNotes.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            DataManager.notes)

        binding.listNotes.setOnItemClickListener { parent, view, position, id ->
            // Use the Kotlin extension in the fragment-ktx artifact
            setFragmentResult("SELECT_ITEM", bundleOf(NOTE_POSITION to position))
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        (binding.listNotes.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}