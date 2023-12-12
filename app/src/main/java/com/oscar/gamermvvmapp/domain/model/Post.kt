package com.oscar.gamermvvmapp.domain.model

import android.net.Uri
import com.google.gson.Gson

data class Post(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var category: String = "",
    var image: String = "",
    var idUser: String = "",
    var user: User = User(),
    var likes: ArrayList<String> = ArrayList()
){
    fun toJson(): String = Gson().toJson(Post(
        id = Uri.encode(id),
        name = Uri.encode(name),
        description = Uri.encode(description),
        category = Uri.encode(category),
        image = Uri.encode(image),
        idUser = Uri.encode(idUser),
        User(
            id = user?.id?:"",
            username = user?.username?:"",
            email = user?.email?:"",
            image = Uri.encode(user?.image)
        ),
        likes
    ))

    companion object{
        fun fromJson(data: String): Post = Gson().fromJson(data, Post::class.java)
    }
}
