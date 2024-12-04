package com.sns.features.books.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.sns.core.myres.Dimens.columnPaddingVr
import com.sns.core.myres.Dimens.columnSpace
import com.sns.core.myres.Dimens.subTitleFonSize
import com.sns.core.myres.Dimens.titleFonSize
import com.sns.core.myres.MyColors.TITLE_COLOR
import com.sns.features.R

@Composable
fun DetailsScreen(navController: NavController) {

    val descriptionArgument = navController.previousBackStackEntry?.savedStateHandle?.get<String>(
        "descriptionArgument"
    )
    val releaseDateArgument = navController.previousBackStackEntry?.savedStateHandle?.get<String>(
        "releaseDateArgument"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Spacer(modifier = Modifier.size(columnSpace))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = columnPaddingVr)
        ) {
            Spacer(modifier = Modifier.size(columnSpace))
            Row {
                Text(
                    text = stringResource(id = R.string.book_details),
                    fontSize = titleFonSize,
                    color = Color(TITLE_COLOR.toColorInt())
                )
            }
            Spacer(modifier = Modifier.size(columnSpace))
            Row {
                descriptionArgument?.let {
                    Text(
                        text = it,
                        fontSize = subTitleFonSize,
                        color = Color(TITLE_COLOR.toColorInt())
                    )
                }
            }
            Spacer(modifier = Modifier.size(columnSpace))
            Row {
                releaseDateArgument?.let {
                    Text(
                        text = it,
                        fontSize = subTitleFonSize,
                        color = Color(TITLE_COLOR.toColorInt())
                    )
                }
            }
            Spacer(modifier = Modifier.size(columnSpace))
            Row(modifier = Modifier
                .background(Color.Transparent)
                .clickable(
                    onClick = {
                        navController.popBackStack()
                    }
                )) {
                Text(
                    text = stringResource(id = R.string.back_to_book_page),
                    fontSize = subTitleFonSize,
                    color = Color.Blue
                )
            }
        }
    }
}

