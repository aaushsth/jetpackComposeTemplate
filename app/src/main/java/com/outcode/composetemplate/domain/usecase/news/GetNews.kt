package com.outcode.myapplication.domain.usecase.news

import androidx.annotation.VisibleForTesting
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.outcode.myapplication.data.repository.NewsRepository
import com.outcode.composetemplate.data.response.Article
import com.outcode.composetemplate.domain.usecase.news.NewsPagingResource
import com.outcode.myapplication.domain.usecase.FlowPagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNews  @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: NewsRepository
) : FlowPagingUseCase<GetNews.Params, Article>() {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    )

    override fun execute(params: Params): Flow<PagingData<Article>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = { NewsPagingResource(repository, params.options) }
        ).flow
    }
}