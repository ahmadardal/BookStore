package com.example.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.adapter.BookListAdapter
import com.example.bookstore.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: BookListAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {

        bookListRecyclerView.layoutManager = LinearLayoutManager(this)

        recyclerAdapter = BookListAdapter()

        bookListRecyclerView.adapter = recyclerAdapter

    }

    private fun initViewModel() {

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

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