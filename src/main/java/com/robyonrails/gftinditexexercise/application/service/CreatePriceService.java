package com.robyonrails.gftinditexexercise.application.service;

import com.robyonrails.gftinditexexercise.application.dto.PriceRequestDto;
import com.robyonrails.gftinditexexercise.application.dto.PriceResponseDto;
import com.robyonrails.gftinditexexercise.application.port.input.CreatePriceUseCase;
import com.robyonrails.gftinditexexercise.domain.model.Price;
import com.robyonrails.gftinditexexercise.domain.repository.PriceRepository;
import com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del caso de uso {@link CreatePriceUseCase}
 *
 * @author Robert Lungu
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CreatePriceService implements CreatePriceUseCase {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public PriceResponseDto createPrice(PriceRequestDto requestDto) {
        log.info("Iniciando creación de precio: productId={}, brandId={}, priceList={}",
                requestDto.getProductId(), requestDto.getBrandId(), requestDto.getPriceList());

        try {
            Price price = priceMapper.toDomain(requestDto);
            Price savedPrice = priceRepository.save(price);
            PriceResponseDto response = priceMapper.toResponseDto(savedPrice);

            log.info("Precio creado exitosamente con ID: {}, precio: {} {}",
                    savedPrice.getId(), savedPrice.getPrice(), savedPrice.getCurrency());

            return response;

        } catch (Exception e) {
            log.error("Error al crear precio para productId={}, brandId={}: {}",
                    requestDto.getProductId(), requestDto.getBrandId(), e.getMessage(), e);
            throw e;
        }
    }
}
