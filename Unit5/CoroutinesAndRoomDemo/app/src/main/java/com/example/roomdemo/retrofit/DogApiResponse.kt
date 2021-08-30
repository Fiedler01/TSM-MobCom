package com.example.roomdemo.retrofit

import com.google.gson.annotations.SerializedName

class DogApiResponse {
    @SerializedName("message")
    var message: String? = ""
    @SerializedName("status")
    var status: String? = ""
}
