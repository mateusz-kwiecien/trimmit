package pl.mkwiecien.trimmit.adapter.inbound.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mkwiecien.trimmit.domain.shortLink.model.ShortLink
import pl.mkwiecien.trimmit.domain.shortLink.service.ShortLinkService

@RestController
@RequestMapping("api/shortLink")
class ShortLinkController(
    private val shortLinkService: ShortLinkService
) {

    @PostMapping
    fun create(@RequestBody request: ShortLinkCreateRequest): ResponseEntity<ShortLink> {
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
    }
}