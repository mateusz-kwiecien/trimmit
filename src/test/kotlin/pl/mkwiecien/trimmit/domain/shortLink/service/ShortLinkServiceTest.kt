package pl.mkwiecien.trimmit.domain.shortLink.service

import org.assertj.core.api.Assertions
import pl.mkwiecien.trimmit.adapter.outbound.database.ShortLinkInMemoryRepository
import pl.mkwiecien.trimmit.domain.shortLink.repository.ShortLinkRepository
import kotlin.test.Test

class ShortLinkServiceTest {
    val shortLinkRepository: ShortLinkRepository = ShortLinkInMemoryRepository()
    val shortLinkService: ShortLinkService = ShortLinkService(shortLinkRepository)

    @Test
    fun `Should create short link from given request`() {
        // given
        val originalUrl = "https://example.com"
        val request = ShortLinkService.CreateRequest(originalUrl)

        // when
        val shortLink = shortLinkService.create(request)

        // then
        Assertions.assertThat(shortLink.originalUrl).isEqualTo(originalUrl)
        Assertions.assertThat(shortLink.tag).isNotEmpty()
        Assertions.assertThat(shortLink.created).isNotNull()
        Assertions.assertThat(shortLink.visits).isEqualTo(0)
    }

    @Test
    fun `Should find short link by tag`() {
        // given
        val originalUrl = "https://example.com"
        val request = ShortLinkService.CreateRequest(originalUrl)
        val shortLink = shortLinkService.create(request)

        // when
        val foundShortLink = shortLinkService.findByTag(shortLink.tag)

        // then
        Assertions.assertThat(foundShortLink).isNotNull
        Assertions.assertThat(foundShortLink?.originalUrl).isEqualTo(originalUrl)
    }
}