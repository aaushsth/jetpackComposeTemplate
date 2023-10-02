package com.outcode.composetemplate.navigation

import com.outcode.composetemplate.data.response.Article

interface NavigationProvider {
    fun openNewsDetails(characterId: String, article: Article)
    fun navigateUp()
}