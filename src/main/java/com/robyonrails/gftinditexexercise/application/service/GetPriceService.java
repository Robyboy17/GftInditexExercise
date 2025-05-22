package com.robyonrails.gftinditexexercise.application.service;

import com.robyonrails.gftinditexexercise.application.dto.PriceResponseDto;
import com.robyonrails.gftinditexexercise.application.port.input.GetPriceUseCase;
import com.robyonrails.gftinditexexercise.domain.model.Price;
import com.robyonrails.gftinditexexercise.domain.repository.PriceRepository;
import com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del caso de uso {@link GetPriceUseCase}
 *
 * @author Robert Lungu
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GetPriceService implements GetPriceUseCase {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PriceResponseDto> getApplicablePrice(Long productId, Long brandId, LocalDateTime date) {
        log.info("Buscando precio aplicable: productId={}, brandId={}, date={}",
                productId, brandId, date);

        List<Price> applicablePrices = priceRepository.findApplicablePrices(productId, brandId, date);

        if (applicablePrices.isEmpty()) {
            log.warn("No se encontraron precios aplicables para productId={}, brandId={}, date={}",
                    productId, brandId, date);
            return Optional.empty();
        }

        log.debug("Evaluando {} precios aplicables para seleccionar el de mayor prioridad",
                applicablePrices.size());

        Optional<Price> selectedPrice = applicablePrices.stream()
                .max(Comparator.comparing(Price::getPriority));

        return selectedPrice.map(price -> {
            log.info("Precio seleccionado: ID={}, prioridad={}, precio={} {}",
                    price.getId(), price.getPriority(), price.getPrice(), price.getCurrency());
            return priceMapper.toResponseDto(price);
        });
    }

}
