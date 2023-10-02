package com.outcode.myapplication.ui.news

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.outcode.myapplication.base.BaseUiViewModel
import com.outcode.myapplication.base.BaseViewState
import com.outcode.myapplication.domain.usecase.news.GetNews
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * Created by Ayush Shrestha$ on 2022/11/24$.
 */

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNews: GetNews,
) : BaseUiViewModel<BaseViewState<ArticleListUiState>, NewsListEvent>() {
    private val config = PagingConfig(pageSize = 20)

    override fun onTriggerEvent(eventType: NewsListEvent) {
        when (eventType) {
            is NewsListEvent.LoadNews -> onLoadNews()
        }
    }

    private fun onLoadNews() = safeLaunch {
        setState(BaseViewState.Loading)
        val params = GetNews.Params(config, hashMapOf())
        val pagedFlow = getNews(params).cachedIn(scope = viewModelScope)
        setState(BaseViewState.Data(ArticleListUiState(pagedData = pagedFlow)))
    }


}