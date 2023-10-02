package com.outcode.composetemplate.ui.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.outcode.composetemplate.R
import com.outcode.myapplication.base.BaseViewState
import com.outcode.composetemplate.components.EmptyView
import com.outcode.composetemplate.components.ErrorView
import com.outcode.composetemplate.components.remoteImage
import com.outcode.composetemplate.navigation.NavigationProvider
import com.outcode.myapplication.components.HeightSpacer
import com.outcode.myapplication.components.LoadingView
import com.outcode.myapplication.components.TopAppBar
import com.outcode.myapplication.components.WidthSpacer
import com.outcode.composetemplate.data.response.Article
import com.outcode.myapplication.extension.cast
import com.outcode.myapplication.extension.rememberFlowWithLifecycle
import com.outcode.myapplication.ui.news.ArticleListUiState
import com.outcode.myapplication.ui.news.NewsListEvent
import com.outcode.myapplication.ui.news.NewsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: NavigationProvider,
) {
    val viewModel: NewsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Column {
        TopAppBar(R.string.app_name, onThemeSwitch = {
            // onThemeSwitch()
        })
        NewsListUi(viewModel, uiState = uiState, navigator)

    }
}

@Composable
fun NewsListUi(
    viewModel: NewsViewModel = hiltViewModel(),
    uiState: BaseViewState<*>,
    navigator: NavigationProvider,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp),
        modifier = Modifier.fillMaxSize().padding(
            start = 10.dp, end = 10.dp, bottom = 10.dp
        )
    ) {
        when (uiState) {
            is BaseViewState.Data -> NewsList(viewState = uiState.cast<BaseViewState.Data<ArticleListUiState>>().value,
                onClick = {
                    navigator.openNewsDetails("it",it)
                }

            )

            is BaseViewState.Empty -> EmptyView(modifier = Modifier)
            is BaseViewState.Error -> ErrorView(e = uiState.cast<BaseViewState.Error>().throwable,
                action = {
                    viewModel.onTriggerEvent(NewsListEvent.LoadNews)
                })

            is BaseViewState.Loading -> LoadingView()
        }

        LaunchedEffect(key1 = viewModel, block = {
            viewModel.onTriggerEvent(NewsListEvent.LoadNews)
        })
    }
}

@Composable
fun NewsList(
    viewState: ArticleListUiState, onClick: (article: Article) -> Unit
) {
    val pagingItems = rememberFlowWithLifecycle(viewState.pagedData).collectAsLazyPagingItems()
    SwipeRefresh(state = rememberSwipeRefreshState(
        isRefreshing = pagingItems.loadState.refresh == LoadState.Loading
    ), onRefresh = { pagingItems.refresh() }, indicator = { state, trigger ->
        SwipeRefreshIndicator(
            state = state, refreshTriggerDistance = trigger, scale = true
        )
    }, content = {

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(top = 4.dp),
        ) {
            items(pagingItems.itemCount) { index ->
                pagingItems[index]?.let { data ->
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth().padding(10.dp).background(Color.White).clickable {
                            onClick(data)
                        }
                        , elevation = CardDefaults.cardElevation(10.dp)
                    ) {

                        Box(
                            modifier = Modifier.padding(5.dp)

                        ) {
                            Column {
                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    remoteImage(
                                        url = data.urlToImage, modifier = Modifier.size(90.dp)
                                    )
                                    WidthSpacer(value = 10.dp)
                                    Column {
                                        Text(
                                            text = data.title, maxLines = 2
                                        )
                                        HeightSpacer(value = 4.dp)
                                        Text(
                                            text = data.description,
                                            maxLines = 3,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        HeightSpacer(value = 4.dp)
                                        Text(
                                            maxLines = 1,
                                            text = "it",
                                        )
                                    }
                                }
                                HeightSpacer(value = 10.dp)
                            }
                        }

                    }
                }
            }

            if (pagingItems.loadState.append == LoadState.Loading) {
                item {
                    Box(
                        Modifier.fillMaxWidth().padding(24.dp)
                    ) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                }
            }
        }

    })

}