package com.sns.features.books.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sns.core.data.states.DataState
import com.sns.core.myres.Dimens.cardRowPadding
import com.sns.core.myres.Dimens.cardRowSpace
import com.sns.core.myres.Dimens.cardRowSubTitleFontSize
import com.sns.core.myres.Dimens.cardRowTitleFontSize
import com.sns.core.myres.Dimens.cardRowTitleSpace
import com.sns.core.myres.Dimens.columnSpace
import com.sns.core.myres.Dimens.lazyColumnPadding
import com.sns.core.myres.Dimens.lazyColumnVSpace
import com.sns.core.myres.Dimens.titleBarPadding
import com.sns.core.myres.Dimens.titleFonSize
import com.sns.core.myres.MyColors.TITLE_COLOR
import com.sns.core.routes.AppRoutes
import com.sns.features.R
import com.sns.features.books.data.entity.Books

@Composable
fun BooksScreen(
    navController: NavController,
    booksViewModel: BooksViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        when (booksViewModel.uiState.value) {
            is DataState.Loading -> LoadingUI()
            is DataState.Success -> SuccessUI(navController, booksViewModel)
            is DataState.Error -> FailureUI()
        }
    }
}

@Composable
private fun SuccessUI(navController: NavController, booksViewModel: BooksViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(columnSpace))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = titleBarPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row {
                Text(
                    text = stringResource(id = R.string.books_title),
                    fontSize = titleFonSize,
                    color = Color(TITLE_COLOR.toColorInt())
                )

            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(lazyColumnPadding),
            verticalArrangement = Arrangement.spacedBy(lazyColumnVSpace),
        ) {
            items((booksViewModel.uiState.value as DataState.Success<List<Books>>).data) {book->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier
                        .padding(cardRowPadding)
                        .background(Color.Transparent)
                        .clickable(
                            onClick = {

                                navController.currentBackStackEntry?.savedStateHandle?.apply {
                                    set("descriptionArgument", book.description)
                                    set("releaseDateArgument", book.releaseDate)
                                }
                                navController.navigate(AppRoutes.DETAILS_SCREEN.routeName)
                            }
                        )) {
                        AsyncImage(
                            model = book.cover,
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.size(cardRowSpace))
                        Column {
                            Text(
                                text = book.title,
                                color = Color(TITLE_COLOR.toColorInt()),
                                fontSize = cardRowTitleFontSize
                            )
                            Spacer(modifier = Modifier.size(cardRowTitleSpace))
                            Text(
                                text = book.originalTitle,
                                color = Color(TITLE_COLOR.toColorInt()),
                                fontSize = cardRowSubTitleFontSize
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingUI() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            progress = 0.89f,
        )
    }
}

@Composable
private fun FailureUI() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.record_not_found),
            color = Color(TITLE_COLOR.toColorInt()),
            fontSize = 22.sp
        )
    }
}
