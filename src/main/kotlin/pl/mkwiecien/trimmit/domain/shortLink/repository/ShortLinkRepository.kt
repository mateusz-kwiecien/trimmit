package pl.mkwiecien.trimmit.domain.shortLink.repository

import pl.mkwiecien.trimmit.domain.shortLink.model.ShortLink

interface ShortLinkRepository {

    fun save(shortLink: ShortLink): ShortLink

    fun findByTag(tag: String): ShortLink?

    fun update(shortLink: ShortLink): ShortLink
}