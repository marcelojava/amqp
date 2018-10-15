package br.com.spyke.amqp.amqptest.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class ReadComponent {

    private static final Logger log = LoggerFactory.getLogger(ReadComponent.class);

    @Autowired
    private ConnectionFactory connectionFactory;

    @Value("${queue.order.name}")
    private String orderQueue;

    @Value("${queue.order.other}")
    private String other;

    @Scheduled(cron = "0 0/02 * * * ?")
    public void lefrA() {
        log.info("INICIO DA LEITURA DA FILA A");
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        boolean existeMensagem = false;
        do {
            Message message = rabbitTemplate.receive(orderQueue);

            if (Objects.nonNull(message)) {
                try {
                    Pedido pedido =  new ObjectMapper().readValue(message.getBody(), Pedido.class);
                    existeMensagem = true;
                    log.info("Produto {}", pedido.getProduto());
                }catch (IOException io) {
                    log.error("Erro ao ler mensagem" , io);
                }
            }
        } while (existeMensagem);
        log.info("FINAL DA LEITURA DA FILA A");
    }


    @Scheduled(cron = "0 0/02 * * * ?")
    public void lerB() {
        log.info("INICIO DA LEITURA DA FILA B");
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        boolean existeMensagem = false;
        do {
            Message message = rabbitTemplate.receive(other);

            if (Objects.nonNull(message)) {
                try {
                    Aluno aluno =  new ObjectMapper().readValue(message.getBody(), Aluno.class);
                    existeMensagem = true;
                    log.info("Aluno {}" , aluno.getNome());
                }catch (IOException io) {
                    log.error("Erro ao ler mensagem" , io);
                }
            }
        } while (existeMensagem);
        log.info("FINAL DA LEITURA DA FILA B");
    }
}
