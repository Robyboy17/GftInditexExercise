package com.robyonrails.gftinditexexercise.domain.repository;

import com.robyonrails.gftinditexexercise.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Puerto de repositorio para la entidad Price
 *
 * @author Robert Lungu
 */
public interface PriceRepository {

    /**
     * Guarda un {@link Price}
     *
     * @param price Entidad precio
     * @return {@link Price} creado
     */
    Price save(Price price);

    /**
     * Búsqueda de precios aplicables
     *
     * @param productId Identificador del producto
     * @param brandId Identificador de la marca del producto
     * @param date Fecha
     * @return Lista de {@link Price} aplicables
     */
    List<Price> findApplicablePrices(Long productId, Long brandId, LocalDateTime date);

}
