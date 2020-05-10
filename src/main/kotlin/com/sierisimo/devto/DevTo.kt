package com.sierisimo.devto

import com.sierisimo.devto.client.repositories.ArticleRepository
import com.sierisimo.devto.client.repositories.buildRepository
import com.sierisimo.devto.data.ArticleInformation
import com.sierisimo.devto.data.ArticlePublished
import com.sierisimo.devto.data.requireValidBody
import com.sierisimo.devto.data.requireValidTitle

class DevTo
internal constructor(
    private val apikey: String,
    private val articleRepository: ArticleRepository
) {
    @JvmOverloads
    constructor(apiKey: String = "") : this(apiKey, buildRepository())

    /**
     * Create a new article in Dev.to
     *
     * This will make a POST call for the DevTo API with the information provided in [ArticleInformation]
     *
     * @param articleInformation An object that represents the information to publish a new article.
     *
     * @return the created [ArticlePublished] or throws a [com.sierisimo.devto.client.DevToCallException]
     *
     * @throws [com.sierisimo.devto.client.DevToCallException] in case of a non 2XX response from DevTo API
     * with the error returned by the API.
     */
    fun createArticle(articleInformation: ArticleInformation): ArticlePublished {
        commonInformationValidations(articleInformation)

        return articleRepository.createArticle(apikey, articleInformation)
    }

    @ExperimentalUnsignedTypes
    fun getArticleById(articleId: UInt): ArticlePublished {
        commonValidations()

        return articleRepository.getById(articleId)
    }

    private fun commonValidations() {
        require(apikey.isNotBlank()) {
            "apikey cannot be blank for this operation. Create a new instance with a valid apikey"
        }
    }

    private fun commonInformationValidations(articleInformation: ArticleInformation) {
        commonValidations()

        articleInformation.requireValidTitle()
        articleInformation.requireValidBody()
    }
}