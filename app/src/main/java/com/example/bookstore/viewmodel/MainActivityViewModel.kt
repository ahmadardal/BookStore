package com.example.bookstore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookstore.data.Book
import com.example.bookstore.retrofit.RetroServiceInterface
import com.example.bookstore.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    private var liveDataList: MutableLiveData<List<Book>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<Book>> {
        return liveDataList
    }

    fun getBooksCall() {
        val retroInstance = RetrofitInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)

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