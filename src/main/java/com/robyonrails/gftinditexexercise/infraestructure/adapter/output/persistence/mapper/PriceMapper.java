package com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.mapper;

import com.robyonrails.gftinditexexercise.application.dto.PriceRequestDto;
import com.robyonrails.gftinditexexercise.application.dto.PriceResponseDto;
import com.robyonrails.gftinditexexercise.domain.model.Price;
import com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;

/**
 * Mapper para convertir entre entidades y DTOs
 *
 * @author Robert Lungu
 */
@Mapper(componentModel = "spring")
public interface PriceMapper {

    /**
     * Convierte una entidad JPA a un objeto de dominio.
     */
    Price toDomain(PriceEntity entity);

    /**
     * Convierte un objeto de dominio a una entidad JPA.
     */
    PriceEntity toEntity(Price domain);

    /**
     * Convierte un DTO de solicitud a un objeto de dominio.
     */
    Price toDomain(PriceRequestDto requestDto);

    /**
     * Convierte un objeto de dominio a un DTO de respuesta.
     */
    PriceResponseDto toResponseDto(Price price);
}