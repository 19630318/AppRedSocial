package com.oscar.gamermvvmapp.di

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.oscar.gamermvvmapp.core.Constants.POSTS
import com.oscar.gamermvvmapp.core.Constants.USERS
import com.oscar.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.oscar.gamermvvmapp.data.repository.PostRepositoryImpl
import com.oscar.gamermvvmapp.data.repository.UsersRepositoryImpl
import com.oscar.gamermvvmapp.domain.repository.AuthRepository
import com.oscar.gamermvvmapp.domain.repository.PostRepository
import com.oscar.gamermvvmapp.domain.repository.UsersRepository
import com.oscar.gamermvvmapp.domain.usecase.auth.*
import com.oscar.gamermvvmapp.domain.usecase.post.*
import com.oscar.gamermvvmapp.domain.usecase.users.*
import com.oscar.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS)
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference = storage.reference.child(USERS)

    @Provides
    @Named(USERS)
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    @Named(POSTS)
    fun provideStoragePostRef(storage: FirebaseStorage): StorageReference = storage.reference.child(POSTS)

    @Provides
    @Named(POSTS)
    fun providePostRef(db: FirebaseFirestore): CollectionReference = db.collection(POSTS)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("GamerMVVMAppSharedPreferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideResultingActivityHandler(): ResultingActivityHandler = ResultingActivityHandler()

    @Provides
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository = authRepositoryImpl

    @Provides
    fun providesAuthUseCases(authRepository: AuthRepository) = AuthUseCase(
        login = Login(authRepository),
        getCurrentUser = GetCurrentUser(authRepository),
        logout = Logout(authRepository),
        signUp = SignUp(authRepository)
    )

    @Provides
    fun provideUsersRepository(usersRepositoryImpl: UsersRepositoryImpl): UsersRepository = usersRepositoryImpl

    @Provides
    fun providesUsersUseCases(usersRepository: UsersRepository) = UsersUseCase(
        create = Create(usersRepository),
        getUserById = GetUserById(usersRepository),
        update = Update(usersRepository),
        saveImage = SaveImage(usersRepository)
    )

    @Provides
    fun providePostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository = postRepositoryImpl

    @Provides
    fun providesPostUseCases(postRepository: PostRepository) = PostUseCase(
        createPost = CreatePost(postRepository),
        getPosts = GetPosts(postRepository),
        getPostsByUserId = GetPostsByUserId(postRepository),
        deletePost = DeletePost(postRepository),
        updatePost = UpdatePost(postRepository),
        likePost = LikePost(postRepository),
        deleteLikePost = DeleteLikePost(postRepository)
    )

}