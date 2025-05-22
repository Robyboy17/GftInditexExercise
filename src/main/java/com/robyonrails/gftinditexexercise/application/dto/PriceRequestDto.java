package com.robyonrails.gftinditexexercise.application.dto;

import com.robyonrails.gftinditexexercise.domain.model.Price;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Dto con los datos de la request para crear o actualizar un {@link Price}
 *
 * @author Robert Lungu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PriceRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7639623842573932842L;

    @NotNull(message = "El ID de marca no puede ser nulo")
    @Positive(message = "El ID de marca debe ser un número positivo")
    private Long brandId;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    private LocalDateTime startDate;

    @NotNull(message = "La fecha de fin no puede ser nula")
    private LocalDateTime endDate;

    @NotNull(message = "El ID de tarifa no puede ser nulo")
    @Positive(message = "El ID de tarifa debe ser un número positivo")
    private Long priceList;

    @NotNull(message = "El ID de producto no puede ser nulo")
    @Positive(message = "El ID de producto debe ser un número positivo")
    private Long productId;

    @NotNull(message = "La prioridad no puede ser nula")
    @Min(value = 0, message = "La prioridad debe ser mayor o igual a 0")
    private Integer priority;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener máximo 10 dígitos enteros y 2 decimales")
    private BigDecimal price;

    @NotBlank(message = "La moneda no puede estar vacía")
    @Size(min = 3, max = 3, message = "El código de moneda debe tener exactamente 3 caracteres")
    @Pattern(regexp = "^[A-Z]{3}$", message = "La moneda debe ser un código ISO válido (3 letras mayúsculas)")
    private String currency;

    @AssertTrue(message = "La fecha de inicio debe ser anterior a la fecha de fin")
    private boolean isStartDateBeforeEndDate() {
        if (startDate == null || endDate == null) {
            return true;
        }
        return startDate.isBefore(endDate);
    }
}
