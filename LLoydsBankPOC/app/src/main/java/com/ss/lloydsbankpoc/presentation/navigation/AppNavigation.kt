package com.ss.lloydsbankpoc.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sns.core.routes.AppRoutes
import com.sns.features.books.ui.BooksScreen
import com.sns.features.books.ui.DetailsScreen
import com.sns.features.books.ui.BooksViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = AppRoutes.BOOKS_SCREEN.routeName
) {
    val booksViewModel: BooksViewModel = viewModel()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(
                route = AppRoutes.BOOKS_SCREEN.routeName,
            ) {
                BooksScreen(navController,booksViewModel = booksViewModel)
            }
        composable(
            route = AppRoutes.DETAILS_SCREEN.routeName,
        ) {
            DetailsScreen(navController)
        }

    }
}



