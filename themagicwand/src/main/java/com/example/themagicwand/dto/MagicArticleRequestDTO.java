package com.example.themagicwand.dto;

import com.example.themagicwand.enums.ArticleType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MagicArticleRequestDTO {

    @NotBlank(message = "Nombre del articulo es requerido")
    private String name;

    @NotNull(message = "Tipo de articulo es requerido")
    private ArticleType type;

    @NotNull(message = "Precio es requerido")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Id de proveedor es requerido")
    private Long providerId;
}
