package com.sierisimo.devto.client.repositories

import com.sierisimo.devto.api.DevToAPI
import com.sierisimo.devto.data.Article
import com.sierisimo.devto.data.ArticleToPublish
import com.sierisimo.devto.fp.mapList
import com.sierisimo.devto.requests.CreateArticleRequest
import com.sierisimo.devto.responses.ArticleResponse

internal class ArticleRepository(
	private val apiClientFactory: () -> DevToAPI,
	private val responseMapper: (ArticleResponse) -> Article,
	private val createMapper: (ArticleToPublish) -> CreateArticleRequest
) {
	private val api: DevToAPI by lazy { apiClientFactory() }

	fun getArticles(
		page: Int,
		limitPerPage: Int
	): List<Article> = mapList(
		api.articles(
			mapOf(
				"page" to page.toString(),
				"per_page" to limitPerPage.toString()
			)
		), responseMapper
	)

	fun createArticle(apiKey: String, articleToPublish: ArticleToPublish) {
		api.createArticle(
			mapOf(
				"Content-Type" to "application/json",
				"api-key" to apiKey
			),
			createMapper(articleToPublish)
		)
	}
}