package dev.itsyourap.newsapp.domain.usecases.news

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val deleteArticle: DeleteArticle,
    val upsertArticle: UpsertArticle,
    val selectArticle: SelectArticle
)
