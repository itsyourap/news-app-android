package dev.itsyourap.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import dev.itsyourap.newsapp.domain.model.Article
import dev.itsyourap.newsapp.presentation.Dimens.ExtraSmallPadding2
import dev.itsyourap.newsapp.presentation.Dimens.MediumPadding1

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val handlePagingResult = handlePagingResult(modifier = modifier, articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2),
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    ArticleCard(
                        article = article,
                        onClick = { onClick(article) }
                    )
                }
            }
        }
    }
}

@Composable
private fun handlePagingResult(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>
): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect(modifier = modifier)
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffect(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MediumPadding1)
    ) {
        repeat(10) {
            ArticleCardShimmerEffect(modifier = modifier)
        }
    }
}