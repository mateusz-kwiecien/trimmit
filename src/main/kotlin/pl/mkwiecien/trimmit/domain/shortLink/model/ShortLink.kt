package pl.mkwiecien.trimmit.domain.shortLink.model

import java.time.Instant

data class ShortLink(
    val id: String?,
    val tag: String,
    val originalUrl: String,
    val created: Instant,
    val visits: Int
)