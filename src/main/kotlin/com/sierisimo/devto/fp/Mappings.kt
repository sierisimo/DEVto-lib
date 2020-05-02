package com.sierisimo.devto.fp

import com.sierisimo.devto.data.Article
import com.sierisimo.devto.data.ArticleToPublish
import com.sierisimo.devto.requests.ArticleRequestInfo
import com.sierisimo.devto.requests.ArticleRequest
import com.sierisimo.devto.responses.ArticleResponse

internal fun <I, O> mapList(input: List<I>, mapSingle: (I) -> O): List<O> {
	return input.map { mapSingle(it) }
}

internal fun mapArticleFromResponse(response: ArticleResponse): Article {
	return Article(
		response.id,
		response.title
	)
}

internal fun mapCreateRequestFromToPublish(articleToPublish: ArticleToPublish) =
	articleToPublish.run {
		ArticleRequest(
			ArticleRequestInfo(
				title,
				body,
				tags,
				publish,
				series,
				mainImage,
				url,
				description
			)
		)
	}