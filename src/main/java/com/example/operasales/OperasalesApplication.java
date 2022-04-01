package com.example.operasales;

import com.example.operasales.domain.Customer;
import com.example.operasales.domain.Opera;
import com.example.operasales.domain.OperaEvent;
import com.example.operasales.services.*;
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
        CustomerService customerService = ctx.getBean(CustomerService.class);

        orderEvent.deleteAll();
        placeService.deleteAll();
        operaEventService.deleteAll();
        operaService.deleteAll();
        customerService.deleteAll();

        // Регистрация покупателей
        Customer customer1 = customerService.save("Толстой Л.Н.", "tolstoy@russia.ru");
        Customer customer2 = customerService.save("Петухов Л.Н.", "petuhov@russia.ru");
        Customer customer3 = customerService.save("Чехов А.П.", "chehov@russia.ru");

        // добавляем оперы
        Opera opera1 = operaService.save("отелло", "Пьеса по Шекспиру", 18);
        Opera opera2 = operaService.save("Вишневый сад", "Пьеса по Чехову", 3);
        Opera opera3 = operaService.save("Гамлет", "Пьеса по Шекспиру", 18);

        // меняем оперу
        Opera opera4 = operaService.getOperaByName("отелло");
        opera4.setName("Отелло");
        operaService.save(opera4);
        operaService.deleteOpera(opera3);

        // вывод всех опер
        System.out.println("Список премьер:");
        operaService.getOperas().forEach(System.out::println);

        // добавляем премьеру 1
        LocalDateTime date = LocalDateTime.now();
        OperaEvent operaEvent = operaEventService.save(date, opera4, 25);

        // поиск
        OperaEvent event1 = operaEventService.findByOpera(operaService.getOperaByName("Отелло"));

        // Покупка билетов
        operaEventService.buyTicket(event1, 1, customer1);
        operaEventService.buyTicket(event1, 11, customer1);
        operaEventService.buyTicket(event1, 12, customer1);
        operaEventService.buyTicket(event1, 13, customer1);
        try {
            operaEventService.buyTicket(event1, 26, customer2);
        } catch (Exception err) {
            logger.error(err.getMessage());
        }
        try {
            operaEventService.buyTicket(event1, 11, customer2);
        } catch (Exception err) {
            logger.error(err.getMessage());
        }

        // Отмена покупки билета
        operaEventService.cancelBuyTicket(event1, 13, customer1);
        // Отмена покупки чужого билета
        try {
            operaEventService.cancelBuyTicket(event1, 1, customer2);
        } catch (Exception err) {
            logger.error(err.getMessage());
        }

        // добавляем премьеру 2
        operaEvent = operaEventService.save(date, opera2, 25);
        OperaEvent event2 = operaEventService.findByOpera(operaService.getOperaByName("Вишневый сад"));
        operaEventService.buyTicket(event2, 1, customer3);

        System.out.println("Афиша:");
        operaEventService.getOperaEvents().forEach(System.out::println);

    }

}
