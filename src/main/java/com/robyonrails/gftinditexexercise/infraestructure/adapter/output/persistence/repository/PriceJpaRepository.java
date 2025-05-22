package com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.repository;

import com.robyonrails.gftinditexexercise.infraestructure.adapter.output.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio JPA para la entidad {@link PriceEntity}
 *
 * @author Robert Lungu
 */
@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Busca los precios aplicables para un producto y marca en una fecha determinada
     * ordenados por prioridad (de mayor a menor)
     */
    @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId " +
            "AND p.brandId = :brandId " +
            "AND :date BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    List<PriceEntity> findApplicablePrices(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("date") LocalDateTime date
    );

}
