package com.example.operasales;

import com.example.operasales.domain.Opera;
import com.example.operasales.domain.OperaEvent;
import com.example.operasales.services.OperaEventPlaceService;
import com.example.operasales.services.OperaEventService;
import com.example.operasales.services.OperaService;
import com.example.operasales.services.OrderEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;

@SpringBootApplication
public class OperasalesApplication {

    static final Logger logger = LoggerFactory.getLogger(OperasalesApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(OperasalesApplication.class, args);
        OperaService operaService = ctx.getBean(OperaService.class);
        OperaEventService operaEventService = ctx.getBean(OperaEventService.class);
        OperaEventPlaceService placeService = ctx.getBean(OperaEventPlaceService.class);
        OrderEventService orderEvent = ctx.getBean(OrderEventService.class);

        orderEvent.deleteAll();
        placeService.deleteAll();
        operaEventService.deleteAll();
        operaService.deleteAll();


        // добавляем оперы
        Opera opera1 = operaService.createOpera("отелло", "Пьеса по Шекспиру", 18);
        Opera opera2 = operaService.createOpera("Вишневый сад", "Пьеса по Чехову", 3);
        Opera opera3 = operaService.createOpera("Гамлет", "Пьеса по Шекспиру", 18);

        // меняем оперу
        Opera opera4 = operaService.getOperaByName("отелло");
        opera4.setName("Отелло");
        operaService.createOpera(opera4);
        operaService.deleteOpera(opera3);

        // вывод всех опер
        System.out.println("Список премьер:");
        operaService.getOperas().forEach(System.out::println);

        // добавляем премьеру 1
        LocalDateTime date = LocalDateTime.now();
        OperaEvent operaEvent = operaEventService.createOperaEvent(date, opera4, 25);

        // поиск
        OperaEvent event1 = operaEventService.findByOpera(operaService.getOperaByName("Отелло"));

        // Покупка билетов
        operaEventService.buyTicket(event1, 1, "Толстой Л.Н.");
        operaEventService.buyTicket(event1, 11, "Толстой Л.Н.");
        operaEventService.buyTicket(event1, 12, "Толстой Л.Н.");
        operaEventService.buyTicket(event1, 13, "Толстой Л.Н.");
        try {
            operaEventService.buyTicket(event1, 26, "Петухов Л.Н.");
        } catch (Exception err) {
            logger.error(err.getMessage());
        }
        try {
            operaEventService.buyTicket(event1, 11, "Петухов Л.Н.");
        } catch (Exception err) {
            logger.error(err.getMessage());
        }

        // Отмена покупки билета
        operaEventService.cancelBuyTicket(event1, 13, "Толстой Л.Н.");
        // Отмена покупки чужого билета
        try {
            operaEventService.cancelBuyTicket(event1, 1, "Толстой-Лживый Л.Н.");
        } catch (Exception err) {
            logger.error(err.getMessage());
        }

        // добавляем премьеру 2
        operaEvent = operaEventService.createOperaEvent(date, opera2, 25);
        OperaEvent event2 = operaEventService.findByOpera(operaService.getOperaByName("Вишневый сад"));
        operaEventService.buyTicket(event2, 1, "Чехов А.П.");

        System.out.println("Афиша:");
        operaEventService.getOperaEvents().forEach(System.out::println);

    }

}
