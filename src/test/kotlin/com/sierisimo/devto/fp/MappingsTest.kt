package com.sierisimo.devto.fp

import com.sierisimo.devto.responses.ArticleResponse
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forExactly
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import java.net.URL

internal class MappingsTest : FunSpec({
	test("mapList should give a list of objects based on a function") {
		var result = mapList(listOf("A", "B", "C")) { it.length }
		result.size shouldBeExactly 3
		result.forExactly(3) {
			it shouldBeGreaterThan 0
			it shouldBeExactly 1
		}
	}

	test("you can get an article for a response") {
		val mockResponse = mockk<ArticleResponse> (relaxed = true)

		val result = mapArticleFromResponse(mockResponse)
		result.should {
			it.id shouldBeExactly mockResponse.id
			it.title shouldBe mockResponse.title
		}

	}
})