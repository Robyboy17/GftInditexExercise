package com.robyonrails.gftinditexexercise.infraestructure.adapter.input.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la marca 1 (ZARA)")
    void test1_shouldReturnPrice1WhenRequestingAt10AMOnJune14() throws Exception {
        // Arrange
        String testDate = "2020-06-14T10:00:00";

        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/prices/applicable")
                        .param("productId", PRODUCT_ID.toString())
                        .param("brandId", BRAND_ID.toString())
                        .param("date", testDate))
                .andDo(print());

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(PRODUCT_ID))
                .andExpect(jsonPath("$.brandId").value(BRAND_ID))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la marca 1 (ZARA)")
    void test2_shouldReturnPrice2WhenRequestingAt16PMOnJune14() throws Exception {
        // Arrange
        String testDate = "2020-06-14T16:00:00";

        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/prices/applicable")
                        .param("productId", PRODUCT_ID.toString())
                        .param("brandId", BRAND_ID.toString())
                        .param("date", testDate))
                .andDo(print());

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(PRODUCT_ID))
                .andExpect(jsonPath("$.brandId").value(BRAND_ID))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la marca 1 (ZARA)")
    void test3_shouldReturnPrice1WhenRequestingAt21PMOnJune14() throws Exception {
        // Arrange
        String testDate = "2020-06-14T21:00:00";

        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/prices/applicable")
                        .param("productId", PRODUCT_ID.toString())
                        .param("brandId", BRAND_ID.toString())
                        .param("date", testDate))
                .andDo(print());

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(PRODUCT_ID))
                .andExpect(jsonPath("$.brandId").value(BRAND_ID))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la marca 1 (ZARA)")
    void test4_shouldReturnPrice3WhenRequestingAt10AMOnJune15() throws Exception {
        // Arrange
        String testDate = "2020-06-15T10:00:00";

        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/prices/applicable")
                        .param("productId", PRODUCT_ID.toString())
                        .param("brandId", BRAND_ID.toString())
                        .param("date", testDate))
                .andDo(print());

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(PRODUCT_ID))
                .andExpect(jsonPath("$.brandId").value(BRAND_ID))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la marca 1 (ZARA)")
    void test5_shouldReturnPrice4WhenRequestingAt21PMOnJune16() throws Exception {
        // Arrange
        String testDate = "2020-06-16T21:00:00";

        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/prices/applicable")
                        .param("productId", PRODUCT_ID.toString())
                        .param("brandId", BRAND_ID.toString())
                        .param("date", testDate))
                .andDo(print());

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(PRODUCT_ID))
                .andExpect(jsonPath("$.brandId").value(BRAND_ID))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Debería devolver 404 cuando no hay precios aplicables")
    void shouldReturn404WhenNoPriceIsApplicable() throws Exception {
        // Arrange
        String testDate = "2019-06-14T10:00:00";

        // Act & Assert
        mockMvc.perform(get("/api/v1/prices/applicable")
                        .param("productId", PRODUCT_ID.toString())
                        .param("brandId", BRAND_ID.toString())
                        .param("date", testDate))
                .andExpect(status().isNotFound());
    }
}