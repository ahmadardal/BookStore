package com.example.bookstore.retrofit

import com.example.bookstore.data.AddBookResponse
import com.example.bookstore.data.Book
import com.example.bookstore.data.DeleteBook
import retrofit2.Call
import retrofit2.http.*

interface RetroServiceInterface {

    @GET("/books")
    fun getBooksList(): Call<List<Book>>

    @POST("/books")
    fun postBook(@Body book: Book): Call<AddBookResponse>

    @HTTP(method = "DELETE", path = "/books", hasBody = true)
    fun deleteBook(@Body bookTitle: DeleteBook): Call<AddBookResponse>

}
