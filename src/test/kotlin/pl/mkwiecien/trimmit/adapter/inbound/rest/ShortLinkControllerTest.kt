package pl.mkwiecien.trimmit.adapter.inbound.rest

import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import pl.mkwiecien.trimmit.domain.shortLink.service.ShortLinkService

@SpringBootTest
@AutoConfigureMockMvc
@Import(GlobalExceptionHandler::class)
class ShortLinkControllerTest() {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var shortLinkService: ShortLinkService

    @Test
    fun `should create short link`() {
        // given
        val request = ShortLinkController.ShortLinkCreateRequest("https://example.com")

        // when
        val result = mockMvc.post("/api/shortLink") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }

        // then
        result.andExpect {
            status { isOk() }
            jsonPath("$.originalUrl") { value("https://example.com") }
            jsonPath("$.tag") { isNotEmpty() }
            jsonPath("$.created") { isNotEmpty() }
            jsonPath("$.visits") { value(0) }
        }
    }

    @Test
    fun `should return 400 when original URL is blank`() {
        // given
        val request = ShortLinkController.ShortLinkCreateRequest("")

        // when
        val result = mockMvc.post("/api/shortLink") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }

        // then
        result.andExpect {
            status { isBadRequest() }
            content { containsString("Original URL cannot be blank") }
        }
    }

    @Test
    fun `should redirect to original URL`() {
        // given
        val originalUrl = "https://example.com"
        val tag = shortLinkService.create(ShortLinkService.CreateRequest(originalUrl)).tag

        // when
        val result = mockMvc.get("/$tag")

        // then
        result.andExpect {
            status { is3xxRedirection() }
            header { string("Location", originalUrl) }
        }
    }
}
