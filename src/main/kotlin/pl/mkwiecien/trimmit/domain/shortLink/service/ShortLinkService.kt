package pl.mkwiecien.trimmit.domain.shortLink.service

import pl.mkwiecien.trimmit.domain.shortLink.model.ShortLink
import pl.mkwiecien.trimmit.domain.shortLink.repository.ShortLinkRepository
import java.time.Instant

class ShortLinkService(
    private val shortLinkRepository: ShortLinkRepository
) {

    fun create(request: CreateRequest): ShortLink {
        val shortLink = createFromRequest(request)
        return shortLinkRepository.save(shortLink)
    }

    fun update() {

    }

    fun findByTag() {

    }

    private fun generateTag(length: Int = 6): String {
        val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }

    private fun createFromRequest(request: CreateRequest): ShortLink {
        return ShortLink(
            id = null,
            tag = generateTag(),
            originalUrl = request.originalUrl,
            created = Instant.now(),
            visits = 0
        )
    }

    data class CreateRequest(
        val originalUrl: String
    )
}