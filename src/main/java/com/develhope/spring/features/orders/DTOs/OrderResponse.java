package com.develhope.spring.features.orders.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse { //DTO utilizzato per rappresentare la risposta di un'operazione di visualizzazione di un ordine.
    private Long id;
    private BigDecimal caution;
    private boolean payed;
    private String status;
    private OffsetDateTime orderDate;

}