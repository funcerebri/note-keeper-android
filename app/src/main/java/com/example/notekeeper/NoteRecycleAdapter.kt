package com.example.notekeeper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRecycleAdapter(private val context: Context, private val notes: List<NoteInfo>) :
    RecyclerView.Adapter<NoteRecycleAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textCourse = itemView?.findViewById<TextView?>(R.id.textCourse)
        val textTitle = itemView?.findViewById<TextView?>(R.id.textTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_note_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]

        holder.textCourse?.text = note.course?.title
        holder.textTitle?.text = note.title
    }

    override fun getItemCount() = notes.size
}