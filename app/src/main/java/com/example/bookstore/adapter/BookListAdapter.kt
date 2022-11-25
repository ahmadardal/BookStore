package com.example.bookstore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.data.Book
import com.example.bookstore.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.book_list_row.view.*

class BookListAdapter(var viewModel: MainActivityViewModel): RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {

    private var bookList: List<Book>? = null

    fun setBookList(bookList: List<Book>) {
        this.bookList = bookList
    }

    override fun getItemCount(): Int {
        if (bookList == null) {
            return 0
        }

        return bookList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(bookList?.get(position)!!)

        holder.itemView.setOnClickListener {
            val bookToRemove = bookList!!.get(position)

            viewModel.deleteBookCall(bookToRemove.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_list_row, parent, false)

        return MyViewHolder(view)
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val txtTitle = view.txtTitle
        val txtAuthor = view.txtAuthor
        val txtYear = view.txtYear
        val txtISBN = view.txtISBN

        fun bind(book: Book) {
            txtTitle.text = book.title
            txtAuthor.text = "Author: ${book.title}"
            txtYear.text = "Year: ${book.year}"
            txtISBN.text = "ISBN: ${book.isbn}"
        }

    }

}