package com.sierisimo.devto

import com.sierisimo.devto.data.Article
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.ints.shouldBeExactly

internal class DevToTest : FeatureSpec({
	feature("DevTo singleton") {
		scenario("gives a List of Articles") {
			val articles: List<Article> = DevTo.articles()

			articles.shouldNotBeEmpty()
		}

		scenario("can paginate") {
			val articles: List<Article> = DevTo.articles(page = 2)

			articles.shouldNotBeEmpty()
		}

		scenario("cannot paginate under 1") {
			shouldThrow<IllegalArgumentException> { DevTo.articles(page = 0) }

			shouldThrow<IllegalArgumentException> { DevTo.articles(page = -1) }
		}

		scenario("can limit per page") {
			val articles: List<Article> = DevTo.articles(limitPerPage = 5)

			articles.shouldNotBeEmpty()
			articles.size shouldBeExactly 5
		}
	}
})