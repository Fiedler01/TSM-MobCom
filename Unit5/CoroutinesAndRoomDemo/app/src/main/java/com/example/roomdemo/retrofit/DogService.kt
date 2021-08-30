package com.example.roomdemo.retrofit

import retrofit2.http.GET

interface DogService {
    @GET("random")
    suspend fun getDogImage(): DogApiResponse
}
