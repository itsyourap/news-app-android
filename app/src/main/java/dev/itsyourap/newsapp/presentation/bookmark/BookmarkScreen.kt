package dev.itsyourap.newsapp.presentation.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import dev.itsyourap.newsapp.domain.model.Article
import dev.itsyourap.newsapp.presentation.Dimens.MediumPadding1
import dev.itsyourap.newsapp.presentation.Dimens.SmallPadding1
import dev.itsyourap.newsapp.presentation.common.ArticleList

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .padding(
                    top = MediumPadding1,
                    start = MediumPadding1,
                    end = MediumPadding1
                )
        ) {
            Text(
                text = "Bookmark",
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(SmallPadding1))

        Box(
            modifier = Modifier
                .padding(
                    top = SmallPadding1,
                    start = SmallPadding1,
                    end = SmallPadding1
                )
        ) {
            ArticleList(articles = state.articles, onClick = navigateToDetails)
        }
    }
}