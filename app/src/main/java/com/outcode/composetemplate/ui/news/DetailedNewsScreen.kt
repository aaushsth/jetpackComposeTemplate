package com.outcode.composetemplate.ui.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.outcode.composetemplate.R
import com.outcode.composetemplate.components.remoteImage
import com.outcode.composetemplate.extension.getDateWithServerTimeStamp
import com.outcode.composetemplate.navigation.NavigationProvider
import com.outcode.composetemplate.ui.theme.articleTitleStyle
import com.outcode.composetemplate.ui.theme.dateTextStyle
import com.outcode.composetemplate.ui.theme.sourceTextStyle
import com.outcode.myapplication.components.HeightSpacer
import com.outcode.myapplication.components.TopAppBarWithBack
import com.outcode.composetemplate.data.response.Article
import com.outcode.myapplication.ui.news.NewsViewModel
import com.ramcosta.composedestinations.annotation.Destination


/**
 * Created by Ayush Shrestha$ on 2022/12/13$.
 */

@Destination
@Composable
fun DetailedNewsScreen(
    id: String = "",
    viewModel: NewsViewModel = hiltViewModel(),
    navigator: NavigationProvider,
    article: Article
) {
    Surface(
    ) {
        Column() {
            TopAppBarWithBack(titleResource = R.string.app_name) {
                navigator.navigateUp()
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)) {
                Text(
                    text = article.title,
                    style = articleTitleStyle.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ),
                )
                HeightSpacer(value = 12.dp)
                Row {
                    Text(
                        maxLines = 1,
                        text = "By ${article.author + "on Updated "}",
                        style = dateTextStyle.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )

                    )
                    article.publishedAt.getDateWithServerTimeStamp()?.let {
                        Text(
                            maxLines = 1,
                            text = it,
                            style = dateTextStyle.copy(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )

                        )
                    }
                }
                HeightSpacer(value = 12.dp)

                remoteImage(
                    url = article.urlToImage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                HeightSpacer(value = 12.dp)
                Text(
                    text = article.content,
                    style = sourceTextStyle.copy(color = Color.Black),
                    overflow = TextOverflow.Ellipsis
                )
            }

        }


    }
}