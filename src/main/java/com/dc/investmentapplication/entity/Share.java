package com.dc.investmentapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shares")
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shareId;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private String companyName;

    private String market;

    @Column(nullable = false)
    private BigDecimal currentPrice;

    @Column(nullable = false)
    private String currency;

    private Long volume;
    private BigDecimal dayHigh;
    private BigDecimal dayLow;
    private BigDecimal yearHigh;
    private BigDecimal yearLow;
    private BigDecimal dividendYield;
    private BigDecimal peRatio;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
