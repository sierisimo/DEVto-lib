package com.sierisimo.devto.api

import com.sierisimo.devto.requests.ArticleRequest
import com.sierisimo.devto.responses.ArticlePublishedResponse
import com.sierisimo.devto.responses.ArticleResponse
import feign.*

@Headers("Accept: application/json")
internal interface DevToAPI {
    @RequestLine("GET /articles")
    fun articles(@QueryMap queryParams: Map<String, String>): List<ArticleResponse>

    @RequestLine("POST /articles")
    fun createArticle(@HeaderMap headers: Map<String, String>, articleRequest: ArticleRequest): ArticlePublishedResponse

    @RequestLine("GET /articles/{id}")
    fun getArticleById(@HeaderMap headers: Map<String, String>, @Param("id") articleId: Int): ArticlePublishedResponse

    @RequestLine("PUT /articles/{id}")
    fun updateArticle(@HeaderMap headers: Map<String, String>, @Param("id") id: Int, updateRequest: ArticleRequest)
}