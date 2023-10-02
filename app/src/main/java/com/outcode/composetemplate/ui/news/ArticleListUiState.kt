package com.outcode.myapplication.ui.news

import androidx.paging.PagingData
import com.outcode.composetemplate.data.response.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


/**
 * Created by Ayush Shrestha$ on 2022/11/25$.
 */
data class ArticleListUiState(
    val pagedData: Flow<PagingData<Article>> = emptyFlow(),
)

sealed class NewsListEvent {
     object LoadNews : NewsListEvent()
}