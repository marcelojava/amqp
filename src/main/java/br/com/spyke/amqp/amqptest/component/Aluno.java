package br.com.spyke.amqp.amqptest.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno implements Serializable{

    private String nome;
    private String endereco;
}
