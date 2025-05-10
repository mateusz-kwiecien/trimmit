package pl.mkwiecien.trimmit.config.shortLink

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.mkwiecien.trimmit.adapter.inbound.rest.ShortLinkController
import pl.mkwiecien.trimmit.adapter.outbound.database.ShortLinkInMemoryRepository
import pl.mkwiecien.trimmit.domain.shortLink.repository.ShortLinkRepository
import pl.mkwiecien.trimmit.domain.shortLink.service.ShortLinkService

@Configuration
class ShortLinkConfig {

    @Bean
    fun shortLinkRepository(): ShortLinkRepository {
        return ShortLinkInMemoryRepository()
    }

    @Bean
    fun shortLinkService(
        shortLinkRepository: ShortLinkRepository
    ): ShortLinkService {
        return ShortLinkService(
            shortLinkRepository
        )
    }

    @Bean
    fun shortLinkController(
        shortLinkService: ShortLinkService
    ): ShortLinkController {
        return ShortLinkController(
            shortLinkService
        )
    }
}