package com.robyonrails.gftinditexexercise.infraestructure.config;

import com.robyonrails.gftinditexexercise.domain.repository.PriceRepository;
import com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.adapter.PriceRepositoryAdapter;
import com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.mapper.PriceMapper;
import com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.repository.PriceJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de beans para la inyección de dependencias.
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public PriceRepository priceRepository(PriceJpaRepository jpaRepository, PriceMapper priceMapper) {
        return new PriceRepositoryAdapter(jpaRepository, priceMapper);
    }

}
