package com.oscar.gamermvvmapp.presentation.screens.postlist.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.domain.model.Post

@Composable
fun PostListContent(navHostController: NavHostController,paddingValues: PaddingValues, post: List<Post>) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(paddingValues).padding(bottom = 70.dp)
    ) {
        items(post){
            CardPost(navHostController = navHostController,it)
        }
    }

}