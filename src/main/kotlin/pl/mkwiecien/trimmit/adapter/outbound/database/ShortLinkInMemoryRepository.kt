package pl.mkwiecien.trimmit.adapter.outbound.database

import pl.mkwiecien.trimmit.domain.shortLink.model.ShortLink
import pl.mkwiecien.trimmit.domain.shortLink.repository.ShortLinkRepository

class ShortLinkInMemoryRepository(
    private val inMemoryHashMap: HashMap<String, ShortLink> = HashMap()
): ShortLinkRepository {

    override fun save(shortLink: ShortLink): ShortLink {
        inMemoryHashMap.put(shortLink.tag, shortLink)
        return shortLink
    }

    override fun findByTag(tag: String): ShortLink? {
        return inMemoryHashMap.get(tag)
    }

    override fun update(shortLink: ShortLink): ShortLink {
        TODO("Not yet implemented")
    }
}