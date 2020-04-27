package com.sierisimo.devto

import com.sierisimo.devto.client.repositories.ArticleRepository
import com.sierisimo.devto.client.repositories.buildAPIClient
import com.sierisimo.devto.data.Article
import com.sierisimo.devto.data.ArticleToPublish
import com.sierisimo.devto.fp.mapArticleFromResponse
import com.sierisimo.devto.fp.mapCreateRequestFromToPublish

/**
 * Starting point of library
 */
object DevTo {
	private val articleRepository =
		ArticleRepository(::buildAPIClient, ::mapArticleFromResponse, ::mapCreateRequestFromToPublish)

	/**
	 * Get the recent articles published
	 */
	fun articles(
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

	fun createArticle(
		apiKey: String,
		title: String,
		markdownBody: String,
		publish: Boolean = true,
		series: String? = null,
		imageUrl: String? = null,
		canonUrl: String? = null,
		description: String? = null
	) {
		require(apiKey.isNotBlank())
		require(title.isNotBlank())
		require(markdownBody.isNotBlank())

		articleRepository.createArticle(
			apiKey, ArticleToPublish(
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
}

