package com.sierisimo.devto

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.single
import io.kotest.property.arbitrary.string
import io.kotest.property.forAll

internal class ArticleOfTest : ShouldSpec({
    should("fail when title is empty") {
        shouldThrow<IllegalArgumentException> {
            articleOf(
                title = "",
                markdown = ""
            )
        }
    }

    should("fail when markdown is empty") {
        shouldThrow<IllegalArgumentException> {
            articleOf(
                title = "Test",
                markdown = ""
            )
        }
    }

    should("add corresponding and valid tags as list to the final article") {
        forAll(Arb.string(minSize = 1), Arb.string(minSize = 1), Arb.list(Arb.string())) { title, desc, tagList ->
            articleOf(title, desc, tagList) == ArticleInformation(title, desc, tagList.filter { it.isNotBlank() })
        }
    }
})

internal class ArticleInformationTest : FunSpec({
    context("The ArticleInformation") {
        test("fails validation when title is empty") {
            val article = ArticleInformation("", "", emptyList())
            shouldThrow<IllegalArgumentException> { article.requireValidTitle() }
        }
        test("fail validation when body is empty") {
            val article = ArticleInformation(Arb.string(minSize = 1).single(), "", emptyList())
            shouldThrow<IllegalArgumentException> { article.requireValidBody() }
        }
    }
})