package com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.adapter;

import com.robyonrails.gftinditexexercise.domain.model.Price;
import com.robyonrails.gftinditexexercise.domain.repository.PriceRepository;
import com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.entity.PriceEntity;
import com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.mapper.PriceMapper;
import com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.repository.PriceJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Adaptador que implementa el puerto PriceRepository del dominio
 *
 * @author Robert Lungu
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceJpaRepository jpaRepository;
    private final PriceMapper priceMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Price save(Price price) {
        log.debug("Guardando precio en BD: productId={}, brandId={}, priceList={}",
                price.getProductId(), price.getBrandId(), price.getPriceList());

        PriceEntity entity = priceMapper.toEntity(price);
        PriceEntity savedEntity = jpaRepository.save(entity);

        log.info("Precio guardado exitosamente con ID: {}", savedEntity.getId());

        return priceMapper.toDomain(savedEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Price> findApplicablePrices(Long productId, Long brandId, LocalDateTime date) {
        log.debug("Buscando precios aplicables: productId={}, brandId={}, date={}",
                productId, brandId, date);

        List<PriceEntity> entities = jpaRepository.findApplicablePrices(productId, brandId, date);
        List<Price> prices = entities.stream()
                .map(priceMapper::toDomain)
                .toList();

        log.info("Encontrados {} precios aplicables para productId={}, brandId={}",
                prices.size(), productId, brandId);

        return prices;
    }

}
