package com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.repository;

import com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.entity.PriceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PriceJpaRepositoryTest {

    @Autowired
    private PriceJpaRepository priceJpaRepository;

    @Test
    @DisplayName("Debería encontrar 1 precio aplicable para el 14 a las 10:00")
    void shouldFindOneApplicablePriceAt10AMOnJune14() {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");

        // Act
        List<PriceEntity> result = priceJpaRepository.findApplicablePrices(productId, brandId, date);

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getPriceList()).isOne();
    }

    @Test
    @DisplayName("Debería encontrar 2 precios aplicables para el 14 a las 16:00")
    void shouldFindTwoApplicablePricesAt16PMOnJune14() {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.parse("2020-06-14T16:00:00");

        // Act
        List<PriceEntity> result = priceJpaRepository.findApplicablePrices(productId, brandId, date);

        // Assert
        assertThat(result).hasSize(2);
        // Los precios deben venir ordenados por prioridad descendente
        assertThat(result.get(0).getPriceList()).isEqualTo(2L);
        assertThat(result.get(0).getPriority()).isOne();
        assertThat(result.get(1).getPriceList()).isOne();
        assertThat(result.get(1).getPriority()).isZero();
    }

    @Test
    @DisplayName("Debería encontrar 1 precio aplicable para el 14 a las 21:00")
    void shouldFindOneApplicablePriceAt21PMOnJune14() {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.parse("2020-06-14T21:00:00");

        // Act
        List<PriceEntity> result = priceJpaRepository.findApplicablePrices(productId, brandId, date);

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getPriceList()).isOne();
    }

    @Test
    @DisplayName("Debería encontrar 2 precios aplicables para el 15 a las 10:00")
    void shouldFindTwoApplicablePricesAt10AMOnJune15() {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.parse("2020-06-15T10:00:00");

        // Act
        List<PriceEntity> result = priceJpaRepository.findApplicablePrices(productId, brandId, date);

        // Assert
        assertThat(result).hasSize(2);
        // Los precios deben venir ordenados por prioridad descendente
        assertThat(result.get(0).getPriceList()).isEqualTo(3L);
        assertThat(result.get(0).getPriority()).isOne();
        assertThat(result.get(1).getPriceList()).isOne();
        assertThat(result.get(1).getPriority()).isZero();
    }

    @Test
    @DisplayName("Debería encontrar 2 precios aplicables para el 16 a las 21:00")
    void shouldFindTwoApplicablePricesAt21PMOnJune16() {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.parse("2020-06-16T21:00:00");

        // Act
        List<PriceEntity> result = priceJpaRepository.findApplicablePrices(productId, brandId, date);

        // Assert
        assertThat(result).hasSize(2);
        // Los precios deben venir ordenados por prioridad descendente
        assertThat(result.get(0).getPriceList()).isEqualTo(4L);
        assertThat(result.get(0).getPriority()).isOne();
        assertThat(result.get(1).getPriceList()).isOne();
        assertThat(result.get(1).getPriority()).isZero();
    }

    @Test
    @DisplayName("Debería retornar lista vacía cuando no hay precios aplicables")
    void shouldReturnEmptyListWhenNoPriceIsApplicable() {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.parse("2019-06-14T10:00:00"); // Fecha anterior a cualquier precio

        // Act
        List<PriceEntity> result = priceJpaRepository.findApplicablePrices(productId, brandId, date);

        // Assert
        assertThat(result).isEmpty();
    }
}