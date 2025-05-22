package com.robyonrails.gftinditexexercise.application.port.input;

import com.robyonrails.gftinditexexercise.application.dto.PriceResponseDto;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Caso de uso para obtener precios
 *
 * @author Robert Lungu
 */
public interface GetPriceUseCase {

    /**
     * Obtiene el precio aplicable
     *
     * @param productId Identificador del producto
     * @param brandId Identificador de la marca
     * @param date Fecha
     * @return {@link PriceResponseDto} con el precio aplicable
     */
    Optional<PriceResponseDto> getApplicablePrice(Long productId, Long brandId, LocalDateTime date);

}
