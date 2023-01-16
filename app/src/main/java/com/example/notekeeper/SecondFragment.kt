package com.example.notekeeper

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat.getDrawable
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.notekeeper.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private var notePosition = POSITION_NOT_SET
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        setFragmentResultListener("SELECT_ITEM") { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?:
                            bundle.getInt(NOTE_POSITION)
            
        }

        if(notePosition != POSITION_NOT_SET)
            displayNote()
        else {
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        val ctx = requireContext()
        val adapterCourses = ArrayAdapter<CourseInfo>(
            ctx,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCourse.adapter = adapterCourses

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt(NOTE_POSITION, notePosition)
    }

    private fun displayNote() {
        val note = DataManager.notes[notePosition]
        binding.textNoteTitle.setText(note.title)
        binding.textNoteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        binding.spinnerCourse.setSelection(coursePosition)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_next -> {
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext(){
        notePosition++
        displayNote()
        requireActivity().invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if(notePosition >= DataManager.notes.lastIndex) {
            val menuItem = menu.findItem(R.id.action_next)
            if(menuItem != null) {
                menuItem.icon = getDrawable(requireContext(), R.drawable.ic_baseline_block_24)
                menuItem.isEnabled = false
            }
        }
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.text = binding.textNoteText.text.toString()
        note.title = binding.textNoteTitle.text.toString()
        note.course = binding.spinnerCourse.selectedItem as CourseInfo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}