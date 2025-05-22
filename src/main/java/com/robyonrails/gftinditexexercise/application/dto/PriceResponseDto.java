package com.robyonrails.gftinditexexercise.application.dto;

import com.robyonrails.gftinditexexercise.domain.model.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Dto de respuesta de la entidad {@link Price}
 *
 * @author Robert Lungu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7639623842573932842L;

    private Long productId;
    private Long brandId;
    private Long priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
    private String currency;
}