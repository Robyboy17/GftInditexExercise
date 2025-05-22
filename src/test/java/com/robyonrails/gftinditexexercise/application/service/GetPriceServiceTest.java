package com.robyonrails.gftinditexexercise.application.service;

import com.robyonrails.gftinditexexercise.application.dto.PriceResponseDto;
import com.robyonrails.gftinditexexercise.domain.model.Price;
import com.robyonrails.gftinditexexercise.domain.repository.PriceRepository;
import com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.mapper.PriceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper mapper;

    @InjectMocks
    private GetPriceService getPriceService;

    private Price price1, price2, price3, price4;
    private PriceResponseDto dto1, dto2, dto3, dto4;
    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;

    @BeforeEach
    void setUp() {
        // Setup test data
        price1 = createPrice(1L, BRAND_ID, PRODUCT_ID,
                LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"),
                1L, 0, new BigDecimal("35.50"), "EUR");

        price2 = createPrice(2L, BRAND_ID, PRODUCT_ID,
                LocalDateTime.parse("2020-06-14T15:00:00"),
                LocalDateTime.parse("2020-06-14T18:30:00"),
                2L, 1, new BigDecimal("25.45"), "EUR");

        price3 = createPrice(3L, BRAND_ID, PRODUCT_ID,
                LocalDateTime.parse("2020-06-15T00:00:00"),
                LocalDateTime.parse("2020-06-15T11:00:00"),
                3L, 1, new BigDecimal("30.50"), "EUR");

        price4 = createPrice(4L, BRAND_ID, PRODUCT_ID,
                LocalDateTime.parse("2020-06-15T16:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"),
                4L, 1, new BigDecimal("38.95"), "EUR");

        // Setup response DTOs
        dto1 = createResponseDto(PRODUCT_ID, BRAND_ID, 1L,
                LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"),
                new BigDecimal("35.50"), "EUR");

        dto2 = createResponseDto(PRODUCT_ID, BRAND_ID, 2L,
                LocalDateTime.parse("2020-06-14T15:00:00"),
                LocalDateTime.parse("2020-06-14T18:30:00"),
                new BigDecimal("25.45"), "EUR");

        dto3 = createResponseDto(PRODUCT_ID, BRAND_ID, 3L,
                LocalDateTime.parse("2020-06-15T00:00:00"),
                LocalDateTime.parse("2020-06-15T11:00:00"),
                new BigDecimal("30.50"), "EUR");

        dto4 = createResponseDto(PRODUCT_ID, BRAND_ID, 4L,
                LocalDateTime.parse("2020-06-15T16:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"),
                new BigDecimal("38.95"), "EUR");
    }

    @Test
    @DisplayName("Debería seleccionar tarifa 1 a las 10:00 del día 14")
    void shouldSelectPrice1At10AMOnJune14() {
        // Arrange
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        when(priceRepository.findApplicablePrices(PRODUCT_ID, BRAND_ID, date)).thenReturn(List.of(price1));
        when(mapper.toResponseDto(price1)).thenReturn(dto1);

        // Act
        Optional<PriceResponseDto> result = getPriceService.getApplicablePrice(PRODUCT_ID, BRAND_ID, date);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getPriceList()).isOne();
        assertThat(result.get().getPrice()).isEqualByComparingTo(new BigDecimal("35.50"));
    }

    @Test
    @DisplayName("Debería seleccionar tarifa 2 a las 16:00 del día 14 (mayor prioridad)")
    void shouldSelectPrice2At16PMOnJune14DueToHigherPriority() {
        // Arrange
        LocalDateTime date = LocalDateTime.parse("2020-06-14T16:00:00");
        when(priceRepository.findApplicablePrices(PRODUCT_ID, BRAND_ID, date)).thenReturn(Arrays.asList(price1, price2));
        when(mapper.toResponseDto(price2)).thenReturn(dto2);

        // Act
        Optional<PriceResponseDto> result = getPriceService.getApplicablePrice(PRODUCT_ID, BRAND_ID, date);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getPriceList()).isEqualTo(2L);
        assertThat(result.get().getPrice()).isEqualByComparingTo(new BigDecimal("25.45"));
    }

    @Test
    @DisplayName("Debería seleccionar tarifa 1 a las 21:00 del día 14")
    void shouldSelectPrice1At21PMOnJune14() {
        // Arrange
        LocalDateTime date = LocalDateTime.parse("2020-06-14T21:00:00");
        when(priceRepository.findApplicablePrices(PRODUCT_ID, BRAND_ID, date)).thenReturn(List.of(price1));
        when(mapper.toResponseDto(price1)).thenReturn(dto1);

        // Act
        Optional<PriceResponseDto> result = getPriceService.getApplicablePrice(PRODUCT_ID, BRAND_ID, date);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getPriceList()).isOne();
        assertThat(result.get().getPrice()).isEqualByComparingTo(new BigDecimal("35.50"));
    }

    @Test
    @DisplayName("Debería seleccionar tarifa 3 a las 10:00 del día 15 (mayor prioridad)")
    void shouldSelectPrice3At10AMOnJune15DueToHigherPriority() {
        // Arrange
        LocalDateTime date = LocalDateTime.parse("2020-06-15T10:00:00");
        when(priceRepository.findApplicablePrices(PRODUCT_ID, BRAND_ID, date)).thenReturn(Arrays.asList(price1, price3));
        when(mapper.toResponseDto(price3)).thenReturn(dto3);

        // Act
        Optional<PriceResponseDto> result = getPriceService.getApplicablePrice(PRODUCT_ID, BRAND_ID, date);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getPriceList()).isEqualTo(3L);
        assertThat(result.get().getPrice()).isEqualByComparingTo(new BigDecimal("30.50"));
    }

    @Test
    @DisplayName("Debería seleccionar tarifa 4 a las 21:00 del día 16 (mayor prioridad)")
    void shouldSelectPrice4At21PMOnJune16DueToHigherPriority() {
        // Arrange
        LocalDateTime date = LocalDateTime.parse("2020-06-16T21:00:00");
        when(priceRepository.findApplicablePrices(PRODUCT_ID, BRAND_ID, date)).thenReturn(Arrays.asList(price1, price4));
        when(mapper.toResponseDto(price4)).thenReturn(dto4);

        // Act
        Optional<PriceResponseDto> result = getPriceService.getApplicablePrice(PRODUCT_ID, BRAND_ID, date);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getPriceList()).isEqualTo(4L);
        assertThat(result.get().getPrice()).isEqualByComparingTo(new BigDecimal("38.95"));
    }

    @Test
    @DisplayName("Debería retornar empty cuando no hay precios aplicables")
    void shouldReturnEmptyWhenNoPriceIsApplicable() {
        // Arrange
        LocalDateTime date = LocalDateTime.parse("2019-06-14T10:00:00");
        when(priceRepository.findApplicablePrices(PRODUCT_ID, BRAND_ID, date)).thenReturn(Collections.emptyList());

        // Act
        Optional<PriceResponseDto> result = getPriceService.getApplicablePrice(PRODUCT_ID, BRAND_ID, date);

        // Assert
        assertThat(result).isEmpty();
    }

    private Price createPrice(Long id, Long brandId, Long productId,
                              LocalDateTime startDate, LocalDateTime endDate,
                              Long priceList, Integer priority, BigDecimal price, String currency) {
        return Price.builder()
                .id(id)
                .brandId(brandId)
                .productId(productId)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(priceList)
                .priority(priority)
                .price(price)
                .currency(currency)
                .build();
    }

    private PriceResponseDto createResponseDto(Long productId, Long brandId, Long priceList,
                                               LocalDateTime startDate, LocalDateTime endDate,
                                               BigDecimal price, String currency) {
        return PriceResponseDto.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(priceList)
                .startDate(startDate)
                .endDate(endDate)
                .price(price)
                .currency(currency)
                .build();
    }
}
