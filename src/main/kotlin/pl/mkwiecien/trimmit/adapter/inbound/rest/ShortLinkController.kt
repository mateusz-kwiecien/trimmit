package pl.mkwiecien.trimmit.adapter.inbound.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.mkwiecien.trimmit.domain.shortLink.model.ShortLink
import pl.mkwiecien.trimmit.domain.shortLink.service.ShortLinkService

@RestController
class ShortLinkController(
    private val shortLinkService: ShortLinkService
) {

    @GetMapping("{tag}")
    fun redirect(@PathVariable("tag")tag: String): ResponseEntity<Void> {
        val shortLink = shortLinkService.findByTag(tag)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).build()
        return ResponseEntity.status(HttpStatus.MOVED_TEMPORARILY.value())
            .header("Location", shortLink.originalUrl)
            .build()
    }

    @PostMapping("api/shortLink")
    fun create(@RequestBody request: ShortLinkCreateRequest): ResponseEntity<ShortLink> {
        request.isValid()
        val createdLink = shortLinkService.create(request.toDomainRequest())
        return ResponseEntity.ok(createdLink)
    }


    data class ShortLinkCreateRequest(
        val originalUrl: String
    ) {
        fun toDomainRequest(): ShortLinkService.CreateRequest {
            return ShortLinkService.CreateRequest(
                originalUrl
            )
        }

        fun isValid() {
            if (originalUrl.isBlank()) {
                throw IllegalArgumentException("Original URL cannot be blank")
            }
            if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
                throw IllegalArgumentException("Original URL must start with http:// or https://")
            }
        }
    }
}