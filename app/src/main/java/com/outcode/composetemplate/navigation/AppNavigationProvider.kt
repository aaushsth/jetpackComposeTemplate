package com.outcode.composetemplate.navigation

import androidx.navigation.NavController
import com.outcode.composetemplate.ui.news.destinations.DetailedNewsScreenDestination
import com.outcode.composetemplate.data.response.Article
import com.ramcosta.composedestinations.navigation.navigate

class AppNavigationProvider constructor(
    private val navController: NavController
) : NavigationProvider {

    override fun openNewsDetails(characterId: String, article: Article) {
        navController.navigate(DetailedNewsScreenDestination(characterId,article))
    }

    override fun navigateUp() {
        navController.navigateUp()
    }
}