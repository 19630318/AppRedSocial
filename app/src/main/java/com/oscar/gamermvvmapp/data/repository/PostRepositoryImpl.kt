package com.oscar.gamermvvmapp.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.storage.StorageReference
import com.oscar.gamermvvmapp.core.Constants
import com.oscar.gamermvvmapp.domain.model.Post
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.domain.repository.PostRepository
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named
import com.oscar.gamermvvmapp.core.Constants.POSTS
import com.oscar.gamermvvmapp.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class PostRepositoryImpl @Inject constructor(
    @Named(POSTS) private val postRef: CollectionReference,
    @Named(POSTS) private val storagePostRef: StorageReference,
    @Named(Constants.USERS) private val usersRef: CollectionReference,
) : PostRepository {
    override suspend fun create(post: Post, file: File): Response<Boolean> {
        return try {

            //IMAGEN
            val fromFile = Uri.fromFile(file)
            val ref = storagePostRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()

            //DATA
            post.image = url.toString()
            postRef.add(post).await()

            //RESPONSE
            return Response.Success(true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)

        }
    }

    override suspend fun update(post: Post, file: File?): Response<Boolean> {
        return try {

            //IMAGEN
            if(file != null) {
                val fromFile = Uri.fromFile(file)
                val ref = storagePostRef.child(file.name)
                val uploadTask = ref.putFile(fromFile).await()
                val url = ref.downloadUrl.await()
                post.image = url.toString()
            }

            val map: MutableMap<String, Any> = HashMap()
            map["name"] = post.name
            map["description"] = post.description
            map["image"] = post.image
            map["category"] = post.category

            //DATA
            postRef.document(post.id).update(map).await()

            //RESPONSE
            Response.Success(true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)

        }
    }

    override fun getPost(): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener = postRef.addSnapshotListener { snapshot, e ->
            GlobalScope.launch(Dispatchers.IO) {
                val postResponse = if (snapshot != null) {
                    val posts = snapshot.toObjects(Post::class.java)
                    val idUserArray = ArrayList<String>()

                    snapshot.documents.forEachIndexed { index, documentSnapshot ->
                        posts[index].id = documentSnapshot.id
                    }

                    posts.forEach{
                        idUserArray.add(it.idUser)
                    }

                    val idUserList = idUserArray.toSet().toList()// ELEMENTOS DE USUARIO SIN REPETIR

                    idUserList.map { id ->
                        async {
                            val user = usersRef.document(id).get().await().toObject(User::class.java)!!

                            posts.forEach{
                                if(it.idUser == id){
                                    it.user = user
                                }
                            }

                        }
                    }.forEach {
                        it.await()
                    }
                    Response.Success(posts)
                } else {
                    Response.Failure(e)
                }
                trySend(postResponse)
            }
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getPostByUserId(idUser: String): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener = postRef.whereEqualTo("idUser", idUser).addSnapshotListener { snapshot, e ->
            val postResponse = if (snapshot != null) {
                val posts = snapshot.toObjects(Post::class.java)
                snapshot.documents.forEachIndexed { index, documentSnapshot ->
                    posts[index].id = documentSnapshot.id
                }

                Response.Success(posts)
            } else {
                Response.Failure(e)
            }
            trySend(postResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun delete(idPost: String): Response<Boolean> {
        return try {

            postRef.document(idPost).delete().await()

            Response.Success(true)
        }catch (e:Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun like(idPost: String, idUser: String): Response<Boolean> {
        return try {

            postRef.document(idPost).update("likes", FieldValue.arrayUnion(idUser)).await()

            Response.Success(true)
        }catch (e:Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun deleteLike(idPost: String, idUser: String): Response<Boolean> {
        return try {

            postRef.document(idPost).update("likes", FieldValue.arrayRemove(idUser)).await()

            Response.Success(true)
        }catch (e:Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

}