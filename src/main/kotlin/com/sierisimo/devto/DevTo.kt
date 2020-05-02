@file:JvmName("DevTo")

package com.sierisimo.devto

import com.sierisimo.devto.client.repositories.ArticleRepository
import com.sierisimo.devto.client.repositories.buildAPIClient
import com.sierisimo.devto.data.Article
import com.sierisimo.devto.data.ArticleToPublish
import com.sierisimo.devto.fp.mapArticleFromResponse
import com.sierisimo.devto.fp.mapCreateRequestFromToPublish
import com.sierisimo.devto.requests.ArticleRequest

private val articleRepository =
    ArticleRepository(::buildAPIClient, ::mapArticleFromResponse, ::fromInfoToPublish, ::mapCreateRequestFromToPublish)


data class ArticleInformation(
    val title: String,
    val bodyMarkdown: String,
    val tags: List<String>,
    val publish: Boolean = false,
    val series: String? = null,
    val mainImageUrl: String? = null,
    val canonicalUrl: String? = null,
    val description: String? = null
) {
    fun requireValidTitle() = require(title.isNotBlank())
    fun requireValidBody() = require(bodyMarkdown.isNotBlank())
}



fun fromInfoToPublish(information: ArticleInformation): ArticleToPublish {
    return with(information) {
        ArticleToPublish(
            title,
            bodyMarkdown,
            tags,
            publish,
            series ?: "",
            mainImageUrl ?: "",
            canonicalUrl ?: "",
            description ?: ""
        )
    }
}

/**
 * Create a new article in Dev.to
 *
 * This will make a POST call.
 */
fun createArticle(
    apiKey: String,
    articleInformation: ArticleInformation
) {
    require(apiKey.isNotBlank())

    articleInformation.requireValidTitle()
    articleInformation.requireValidBody()

    articleRepository.createArticle(apiKey, articleInformation)

}

fun updateArticle(
    apiKey: String,
    articleId: Int,
    title: String,
    markdownBody: String,
    publish: Boolean = false,
    series: String? = null,
    imageUrl: String? = null,
    canonUrl: String? = null,
    description: String? = null
) {
    require(apiKey.isNotBlank())
    require(title.isNotBlank())
    require(markdownBody.isNotBlank())

    articleRepository.updateArticle(
        apiKey, articleId,
        ArticleToPublish(
            title,
            markdownBody,
            listOf("kotlin"),
            publish,
            series ?: "",
            imageUrl ?: "",
            canonUrl ?: "",
            description ?: ""
        )
    )
}

/**
 * Get the recent articles published
 */
internal fun articles(
    page: Int = 1,
    limitPerPage: Int = 30
): List<Article> {
    require(page >= 1)
    require(limitPerPage >= 1)
    require(limitPerPage <= 1000)

    return articleRepository.getArticles(
        page, limitPerPage
    )
}