package dev.itsyourap.newsapp.presentation.bookmark

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
import dev.itsyourap.newsapp.presentation.Dimens.MediumPadding1
import dev.itsyourap.newsapp.presentation.Dimens.SmallPadding1
import dev.itsyourap.newsapp.presentation.common.ArticleList
import dev.itsyourap.newsapp.presentation.navgraph.Route

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = SmallPadding1,
                start = SmallPadding1,
                end = SmallPadding1
            ),
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticleList(articles = state.articles, onClick = { navigate(Route.DetailsScreen.route) })
    }
}