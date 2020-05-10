package com.sierisimo.devto.client.repositories

import com.sierisimo.devto.api.DevToAPI
import com.sierisimo.devto.data.mapInfoToRequest
import com.sierisimo.devto.data.mapResponseToPublished
import com.sierisimo.devto.requests.ArticleRequest
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldNotBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.single
import io.kotest.property.arbitrary.string
import io.mockk.*

internal class ArticleRepositoryTest : ExpectSpec({
    context("ArticleRepository calling the creation of article") {
        expect("a call to create method should return an ArticlePublishedResponse in case of 200") {
            val actualRequest = slot<ArticleRequest>()
            val mockApi = mockk<DevToAPI>() {
                every { createArticle(capture(slot()), capture(actualRequest)) } answers {
                    mockk(relaxed = true) {
                        with(actualRequest.captured) {
                            every { id } returns 10
                            every { title } returns article.title
                            every { description } returns article.description
                            every { tagList } returns article.tags.joinToString()
                            every { tags } returns article.tags
                            every { canonicalUrl } returns article.canonicalUrl
                        }
                    }
                }
            }

            val repository = ArticleRepository({ mockApi }, ::mapInfoToRequest, ::mapResponseToPublished)
            val actualPublished =
                repository.createArticle(Arb.string(minSize = 3).single(), mockk(relaxed = true))

            verify { mockApi.createArticle(any(), any()) }

            actualPublished shouldNotBe Unit
            actualPublished.id shouldBeGreaterThan 1

            confirmVerified(mockApi)
        }
    }
})