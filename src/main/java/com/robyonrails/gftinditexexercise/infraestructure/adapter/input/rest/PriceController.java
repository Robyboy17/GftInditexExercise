package com.robyonrails.gftinditexexercise.infraestructure.adapter.input.rest;

import com.robyonrails.gftinditexexercise.application.dto.PriceRequestDto;
import com.robyonrails.gftinditexexercise.application.dto.PriceResponseDto;
import com.robyonrails.gftinditexexercise.application.port.input.CreatePriceUseCase;
import com.robyonrails.gftinditexexercise.application.port.input.GetPriceUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controlador REST para la gestión de precios
 *
 * @author Robert Lungu
 */
@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceController {

    private final GetPriceUseCase getPriceUseCase;
    private final CreatePriceUseCase createPriceUseCase;

    /**
     * Obtiene el precio aplicable para un producto y marca en una fecha determinada.
     *
     * @param productId Identificador del producto en la request
     * @param brandId Identificador de la marca en la request
     * @param date La fecha para la consulta en la request
     * @return ResponseEntity con el precio aplicable en la response
     */
    @GetMapping("/applicable")
    public ResponseEntity<PriceResponseDto> getApplicablePrice(
            @RequestParam @Min(0) Long productId,
            @RequestParam @Min(0) Long brandId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        return getPriceUseCase.getApplicablePrice(productId, brandId, date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo precio.
     *
     * @param requestDto DTO con los datos del precio a crear
     * @return El precio creado
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PriceResponseDto createPrice(@Valid @RequestBody PriceRequestDto requestDto) {
        return createPriceUseCase.createPrice(requestDto);
    }
}
