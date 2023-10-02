package com.outcode.composetemplate.navigation
/**
 * Created by Ayush Shrestha$ on 2022/12/13$.
 */
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.outcode.composetemplate.ui.news.HomeScreen
import com.outcode.newsapp.ui.navigation.MainScreens

@Composable
fun SetNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MainScreens.HomeScreen.route){
        composable(route = MainScreens.HomeScreen.route){
           // HomeScreen(navController = navController)
        }
        composable(route = MainScreens.DetailScreen.route){
           // DetailedNewsScreen(n)

        }
    }
}