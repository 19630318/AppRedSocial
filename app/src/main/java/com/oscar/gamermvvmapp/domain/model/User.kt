package com.oscar.gamermvvmapp.domain.model

import android.net.Uri
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class User(
    var id: String ="",
    var username: String = "",
    var email: String = "",
    var password: String = "",
    var image: String = ""
){
    fun toJson(): String = Gson().toJson(User(
        id = Uri.encode(id),
        username = Uri.encode(username),
        email = Uri.encode(email),
        password = Uri.encode(password),
        image = Uri.encode(image)
    ))

    companion object{
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }
}
