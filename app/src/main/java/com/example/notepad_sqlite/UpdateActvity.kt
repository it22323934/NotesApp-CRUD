package com.example.notepad_sqlite

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notepad_sqlite.databinding.ActivityUpdateActvityBinding

class UpdateActvity : AppCompatActivity() {

    private lateinit var binding:ActivityUpdateActvityBinding
    private lateinit var db:NoteDatabaseHelper
    private var noteId:Int=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db=NoteDatabaseHelper(this)

        noteId=intent.getIntExtra("note_id",-1)
        if(noteId==-1){
            finish()
            return
        }

        val note=db.getNoteByID(noteId)
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.UpdateSaveButton.setOnClickListener{
            val newTitle=binding.updateTitleEditText.text.toString()
            val newContent=binding.updateContentEditText.text.toString()
            val updatedNote=Note(noteId,newTitle,newContent)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this,"Changes Saved",Toast.LENGTH_SHORT).show()
        }
    }
}