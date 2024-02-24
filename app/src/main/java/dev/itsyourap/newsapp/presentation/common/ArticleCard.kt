package dev.itsyourap.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.itsyourap.newsapp.domain.model.Article
import dev.itsyourap.newsapp.domain.model.Source
import dev.itsyourap.newsapp.presentation.Dimens
import dev.itsyourap.newsapp.presentation.Dimens.ArticleCardSize
import dev.itsyourap.newsapp.presentation.Dimens.ExtraSmallPadding1
import dev.itsyourap.newsapp.presentation.Dimens.ExtraSmallPadding2
import dev.itsyourap.newsapp.presentation.Dimens.SmallIconSize
import dev.itsyourap.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Row(modifier = modifier.clip(MaterialTheme.shapes.medium).clickable { onClick() }) {
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(ExtraSmallPadding2))
        Column(
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding1)
                .height(ArticleCardSize),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = article.source.name,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.secondary
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(SmallIconSize),
                    imageVector = Icons.Filled.AccessTime,
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
fun ArticleCardShimmerEffect(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )

        Column(
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding1)
                .height(ArticleCardSize),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(horizontal = Dimens.MediumPadding1)
                    .shimmerEffect()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.5f)
                        .height(15.dp)
                        .padding(horizontal = Dimens.MediumPadding1)
                        .shimmerEffect()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NewsAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ArticleCard(
                article = Article(
                    author = "",
                    content = "",
                    description = "",
                    publishedAt = "2 hours",
                    source = Source(id = "", name = "BBC"),
                    title = "Microsoft Buys Google without spending any money. This is crazy, isn't it? What do you think?",
                    url = "",
                    urlToImage = ""
                ),
                onClick = {

                }
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardShimmerEffectPreview() {
    NewsAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ArticleCardShimmerEffect()
        }
    }
}