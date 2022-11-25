package com.example.bookstore

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_book.*

class AddBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)


        btnAddBook.setOnClickListener {

            val data = Intent()

            data.putExtra("title", txtAddBookTitle.text.toString())
            data.putExtra("author", txtAddBookAuthor.text.toString())
            data.putExtra("year", txtAddBookYear.text.toString().toInt())
            data.putExtra("isbn", txtAddBookISBN.text.toString())

            setResult(Activity.RESULT_OK, data)
            finish()
        }


    }
}