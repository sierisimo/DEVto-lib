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

    constructor(apiKey: String = "") : this(apiKey, buildRepository())

    /**
     * Create a new article in Dev.to
     *
     * This will make a POST call.
     */
    fun createArticle(articleInformation: ArticleInformation): ArticlePublished {
        commonValidations(articleInformation)

        return articleRepository.createArticle(apikey, articleInformation)
    }

    private fun commonValidations(articleInformation: ArticleInformation) {
        require(apikey.isNotBlank()) {
            "apikey cannot be blank for this operation. Create a new instance with a valid apikey"
        }

        articleInformation.requireValidTitle()
        articleInformation.requireValidBody()
    }
}