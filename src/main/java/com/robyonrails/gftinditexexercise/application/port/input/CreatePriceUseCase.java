package com.robyonrails.gftinditexexercise.application.port.input;


import com.robyonrails.gftinditexexercise.application.dto.PriceRequestDto;
import com.robyonrails.gftinditexexercise.application.dto.PriceResponseDto;

/**
 * Caso de uso para crear precios
 *
 * @author Robert Lungu
 */
public interface CreatePriceUseCase {

    /**
     * Crea un nuevo precio
     *
     * @param requestDto DTO con los datos del precio a crear
     * @return DTO con los datos del precio creado
     */
    PriceResponseDto createPrice(PriceRequestDto requestDto);

}