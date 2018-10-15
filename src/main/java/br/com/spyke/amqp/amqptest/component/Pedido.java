package br.com.spyke.amqp.amqptest.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable{

    private String produto;
    private BigDecimal valor;
}
