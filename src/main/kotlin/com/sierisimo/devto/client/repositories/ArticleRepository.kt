package com.sierisimo.devto.client.repositories

import com.sierisimo.devto.ArticleInformation
import com.sierisimo.devto.api.DevToAPI
import com.sierisimo.devto.data.Article
import com.sierisimo.devto.data.ArticleToPublish
import com.sierisimo.devto.fp.mapList
import com.sierisimo.devto.requests.ArticleRequest
import com.sierisimo.devto.responses.ArticleResponse

internal class ArticleRepository(
    apiClientFactory: () -> DevToAPI,
    private val responseMapper: (ArticleResponse) -> Article,
    private val mapperInfoPublish: (information: ArticleInformation) -> ArticleToPublish,
    private val mapperPublishRequest: (ArticleToPublish) -> ArticleRequest
) {
    private val api: DevToAPI by lazy(apiClientFactory)

    fun createArticle(apiKey: String, articleInformation: ArticleInformation) {
        api.createArticle(
            mapOf(
                "Content-Type" to "application/json",
                "api-key" to apiKey
            ),
            mapperPublishRequest(mapperInfoPublish(articleInformation))
        )
    }

    fun updateArticle(apiKey: String, articleId: Int, articleInformation: ArticleInformation) {
        api.updateArticle(
            mapOf(
                "Content-Type" to "application/json",
                "api-key" to apiKey
            ),
            articleId,
            mapperPublishRequest(mapperInfoPublish(articleInformation))
        )
    }

    fun getArticles(page: Int, limitPerPage: Int): List<Article> = mapList(
        api.articles(
            mapOf(
                "page" to page.toString(),
                "per_page" to limitPerPage.toString()
            )
        ), responseMapper
    )
}