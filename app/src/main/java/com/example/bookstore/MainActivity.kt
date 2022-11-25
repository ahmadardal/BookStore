package com.example.bookstore

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.adapter.BookListAdapter
import com.example.bookstore.data.Book
import com.example.bookstore.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_add_book.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: BookListAdapter
    lateinit var viewModel: MainActivityViewModel


    private val intentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        if (result.resultCode == Activity.RESULT_OK) {

            val title = result.data?.getStringExtra("title")
            val author = result.data?.getStringExtra("author")
            val year = result.data?.getIntExtra("year", 0)
            val isbn = result.data?.getStringExtra("isbn")

            val newBook = Book(author!!, year!!, title!!, isbn!!)

            viewModel.addBookCall(newBook)

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        initRecyclerView()
        initViewModel()

        btnShowAddBook.setOnClickListener {
            val intent = Intent(this, AddBookActivity::class.java)
            intentLauncher.launch(intent)
        }

    }


    private fun initRecyclerView() {

        bookListRecyclerView.layoutManager = LinearLayoutManager(this)

        recyclerAdapter = BookListAdapter(viewModel)

        bookListRecyclerView.adapter = recyclerAdapter

    }

    private fun initViewModel() {

        viewModel.getBooksCall()

        viewModel.getLiveDataObserver().observe(this) {

            if (it == null) {
                Toast.makeText(this, "Error fetching books!", Toast.LENGTH_SHORT).show()
            }

            recyclerAdapter.setBookList(it)
            recyclerAdapter.notifyDataSetChanged()

        }

    }

}