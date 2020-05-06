package com.sierisimo.devto

import com.sierisimo.devto.client.repositories.ArticleRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify

internal class DevToTest : FeatureSpec({
    feature("DevTo object") {
        val devTo = DevTo()
        scenario("fails when requested to post empty titled article") {
            shouldThrow<IllegalArgumentException> { devTo.createArticle(articleOf("", "")) }
        }

        scenario("fails when requested to post empty body article") {
            shouldThrow<IllegalArgumentException> { devTo.createArticle(articleOf("", "")) }
        }

        scenario("fails when requested to post article BUT apikey is empty") {
            val exception = shouldThrow<IllegalArgumentException> {
                devTo.createArticle(articleOf("Test", "Body"))
            }

            exception.message shouldBe "apikey cannot be blank for this operation. Create a new instance with a valid apikey"
        }

        scenario("calls a repository with the right data") {
            val repositoryMock = mockk<ArticleRepository>(relaxed = true)
            val devToInternal = DevTo("123", repositoryMock)
            devToInternal.createArticle(articleOf("Title", "Body"))

            verify(exactly = 1) {
                repositoryMock.createArticle(eq("123"), eq(articleOf("Title", "Body")))
            }

            confirmVerified(repositoryMock)
        }
    }
})