package com.example.myretrofitdemo

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") User: Int): Response<Albums>

    @GET("/albums/{id}")
    suspend fun getSpecificAlbum(@Path("id") albumId: Int): Response<AlbumsItem>
}