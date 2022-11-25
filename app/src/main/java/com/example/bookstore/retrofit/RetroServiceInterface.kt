package com.example.bookstore.retrofit

import com.example.bookstore.data.Book
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("/books")
    fun getBooksList(): Call<List<Book>>

}
