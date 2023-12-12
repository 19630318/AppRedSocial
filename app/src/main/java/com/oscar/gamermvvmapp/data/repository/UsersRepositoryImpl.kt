package com.oscar.gamermvvmapp.data.repository

import android.net.Uri
import androidx.compose.runtime.snapshotFlow
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.StorageReference
import com.oscar.gamermvvmapp.core.Constants
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.domain.model.User
import com.oscar.gamermvvmapp.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import com.oscar.gamermvvmapp.core.Constants.USERS
import javax.inject.Named

class UsersRepositoryImpl @Inject constructor(
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(USERS) private val storageUser: StorageReference
) : UsersRepository {

    override suspend fun create(user: User): Response<Boolean> {
        return try {
            user.password = ""
            usersRef.document(user.id).set(user).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<User> = callbackFlow {
        // Se crea un SnapshotListener para escuchar cambios en el documento con el ID proporcionado.
        val snapshotListener = usersRef.document(id).addSnapshotListener { snapshot, e ->
            // Dentro de esta lambda se maneja la respuesta del SnapshotListener.

            // Se intenta obtener un objeto User a partir del snapshot.
            val user = snapshot?.toObject(User::class.java) ?: User()

            // Se intenta enviar el objeto User al flujo.
            trySend(user)
        }

        // Este bloque se ejecuta cuando el flujo es cerrado.
        awaitClose {
            // Se elimina el SnapshotListener para dejar de escuchar cambios en el documento.
            snapshotListener.remove()
        }
    }

    override suspend fun saveImage(file: File): Response<String> {
        return try {
            val fromFile = Uri.fromFile(file)
            val ref = storageUser.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()
            return Response.Success(url.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun update(user: User): Response<Boolean> {
        return try {
            val map = HashMap<String, Any>()
            map["username"] = user.username
            map["image"] = user.image
            usersRef.document(user.id).update(map).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

}