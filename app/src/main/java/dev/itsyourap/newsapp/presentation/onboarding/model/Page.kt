package dev.itsyourap.newsapp.presentation.onboarding.model

import androidx.annotation.DrawableRes
import dev.itsyourap.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Lorem ipsum dolor sit amet",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ut faucibus ipsum, ut facilisis velit.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem ipsum dolor sit amet",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ut faucibus ipsum, ut facilisis velit.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem ipsum dolor sit amet",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ut faucibus ipsum, ut facilisis velit.",
        image = R.drawable.onboarding3
    )
)