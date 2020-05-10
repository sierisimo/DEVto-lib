package com.sierisimo.devto

import com.sierisimo.devto.client.repositories.ArticleRepository
import com.sierisimo.devto.data.articleOf
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.single
import io.kotest.property.arbitrary.string
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

internal class DevToTest : FeatureSpec({
    feature("DevTo instance when posting an article") {
        val devTo = DevTo()
        scenario("fails when requested to post empty titled article") {
            shouldThrow<IllegalArgumentException> {
                devTo.createArticle(
                    articleOf(
                        "",
                        ""
                    )
                )
            }
        }

        scenario("fails when requested to post empty body article") {
            shouldThrow<IllegalArgumentException> {
                devTo.createArticle(
                    articleOf(
                        "",
                        ""
                    )
                )
            }
        }

        scenario("fails when requested to post article BUT apikey is empty") {
            val exception = shouldThrow<IllegalArgumentException> {
                devTo.createArticle(articleOf("Test", "Body"))
            }

            exception.message shouldBe "apikey cannot be blank for this operation. Create a new instance with a valid apikey"
        }

        scenario("calls a repository with the right data") {
            val repositoryMock = mockk<ArticleRepository>(relaxed = true)

            val actual = DevTo("123", repositoryMock)
            actual.createArticle(articleOf("Title", "Body"))

            verify(exactly = 1) {
                repositoryMock.createArticle(
                    eq("123"), eq(
                        articleOf(
                            "Title",
                            "Body"
                        )
                    )
                )
            }

            confirmVerified(repositoryMock)
        }

        scenario("returns an object with a published article") {
            val repositoryMock = mockk<ArticleRepository>(relaxed = true) {
                every { createArticle(any(), any()) } returns mockk(relaxed = true) {
                    every { typeOf } returns "article"
                    every { id } returns 10
                }
            }

            val actual = DevTo(Arb.string(minSize = 3).single(), repositoryMock)
            val actualPublished = actual.createArticle(articleOf("Title", "Body"))

            actualPublished.typeOf shouldBe "article"
            actualPublished.id shouldBeGreaterThan 0

            verify { repositoryMock.createArticle(any(), any()) }

            confirmVerified(repositoryMock)
        }
    }

    feature("DevTo instance when requesting an article by id") {
        val devTo = DevTo("", mockk(relaxed = true))
        scenario("fail if the apikey is not provided") {
            val exception = shouldThrow<IllegalArgumentException> {
                devTo.getArticleById(1.toUInt())
            }
            exception.message shouldBe "apikey cannot be blank for this operation. Create a new instance with a valid apikey"
        }
    }
})