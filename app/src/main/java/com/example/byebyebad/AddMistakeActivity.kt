package com.example.byebyebad

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddMistakeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_mistake)

        val titleEditText = findViewById<EditText>(R.id.edit_title)
        val descriptionEditText = findViewById<EditText>(R.id.edit_description)
        val saveButton = findViewById<Button>(R.id.button_save)

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()

            val resultIntent = Intent().apply {
                putExtra("title", title)
                putExtra("description", description)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
