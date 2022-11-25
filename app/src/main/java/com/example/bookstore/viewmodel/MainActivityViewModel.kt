package com.example.bookstore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstore.data.AddBookResponse
import com.example.bookstore.data.Book
import com.example.bookstore.data.DeleteBook
import com.example.bookstore.retrofit.RetroServiceInterface
import com.example.bookstore.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    private var liveDataList: MutableLiveData<List<Book>> = MutableLiveData()
    private val retroInstance = RetrofitInstance.getRetroInstance()
    private val retroService: RetroServiceInterface = retroInstance.create(RetroServiceInterface::class.java)

    fun getLiveDataObserver(): MutableLiveData<List<Book>> {
        return liveDataList
    }


    fun deleteBookCall(title: String) {
        val deleteBook = DeleteBook(title)

        val call = retroService.deleteBook(deleteBook)

        call.enqueue(object: Callback<AddBookResponse> {

            override fun onFailure(call: Call<AddBookResponse>, t: Throwable) {
                println("Error deleting book! Message: ${t.localizedMessage}")
            }
            override fun onResponse(call: Call<AddBookResponse>,
                                    response: Response<AddBookResponse>) {

            }
        })

    }

    fun addBookCall(newBook: Book) {

        val call = retroService.postBook(newBook)

        call.enqueue(object: Callback<AddBookResponse> {

            override fun onFailure(call: Call<AddBookResponse>, t: Throwable) {
                println("Error adding book! Message: ${t.localizedMessage}")
            }
            override fun onResponse(call: Call<AddBookResponse>,
                                    response: Response<AddBookResponse>) {

            }
        })
    }

    fun getBooksCall() {


        val call = retroService.getBooksList()

        call.enqueue(object: Callback<List<Book>> {

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                println("Error fetching books! Message: ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                liveDataList.postValue(response.body())
                println("Success fetching data!")
            }
        })

    }
}