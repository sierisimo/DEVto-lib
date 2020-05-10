package com.sierisimo.devto.client.repositories

import com.sierisimo.devto.api.DevToAPI
import com.sierisimo.devto.data.ArticleInformation
import com.sierisimo.devto.data.ArticlePublished
import com.sierisimo.devto.data.mapInfoToRequest
import com.sierisimo.devto.data.mapResponseToPublished
import com.sierisimo.devto.requests.ArticleRequest
import com.sierisimo.devto.responses.ArticlePublishedResponse

internal class ArticleRepository(
    apiClientFactory: () -> DevToAPI,
    private val mapperInfoToRequest: (ArticleInformation) -> ArticleRequest,
    private val mapperResponsePublished: (ArticlePublishedResponse) -> ArticlePublished
) {
    private val api: DevToAPI by lazy(apiClientFactory)

    fun createArticle(apiKey: String, articleInformation: ArticleInformation): ArticlePublished {
        val response = api.createArticle(
            mapOf(
                "Content-Type" to "application/json",
                "api-key" to apiKey
            ),
            mapperInfoToRequest(articleInformation)
        )
        return mapperResponsePublished(response)
    }

    @ExperimentalUnsignedTypes
    fun getById(articleId: UInt): ArticlePublished {
        val response = api.getArticleById(
            mapOf(
                "Content-Type" to "application/json"
            ),
            articleId.toInt()
        )
        return mapperResponsePublished(response)
    }
}

internal fun buildRepository(): ArticleRepository =
    ArticleRepository(::buildAPIClient, ::mapInfoToRequest, ::mapResponseToPublished)