package com.playkids.billingservice.http

import com.playkids.billingservice.api.rest.BaseTestController
import com.playkids.billingservice.billing.application.BillingService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.net.URI

@WebMvcTest(BaseTestController::class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
@DisplayName("Base Controller Test")
class BaseTestControllerTest {

    lateinit var mockMvc: MockMvc

    lateinit var billingService: BillingService

    @Test
    fun `Get All People By Page And Return Success`() {
        val resultActions: ResultActions = mockMvc.perform(
            get(URI(TEST_ENDPOINT))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        )

        resultActions.andDo(print())
            .andExpect(status().isOk)
    }

    @Test
    fun `Get Hello world And Return Not found`() {
        val resultActions: ResultActions = mockMvc.perform(
            get(URI("$TEST_ENDPOINT/999"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        )

        resultActions.andDo(print())
            .andExpect(status().isNotFound)
    }

    companion object {
        private const val TEST_ENDPOINT = "/test/v1"
    }

}
