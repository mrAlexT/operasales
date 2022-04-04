package com.example.operasales;

import com.example.operasales.domain.*;
import com.example.operasales.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;
import java.util.Collections;

@SpringBootApplication
public class OperasalesApplication {

    static final Logger logger = LoggerFactory.getLogger(OperasalesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OperasalesApplication.class, args);

//        ConfigurableApplicationContext ctx = SpringApplication.run(OperasalesApplication.class, args);
//        OperaService operaService = ctx.getBean(OperaService.class);
//        OperaEventService operaEventService = ctx.getBean(OperaEventService.class);
//        PlaceService placeService = ctx.getBean(PlaceService.class);
//        OrderService OrderService = ctx.getBean(OrderService.class);
//        CustomerService customerService = ctx.getBean(CustomerService.class);
//
//        OrderService.deleteAll();
//        placeService.deleteAll();
//        operaEventService.deleteAll();
//        operaService.deleteAll();
//        customerService.deleteAll();
//
//        // Регистрация покупателей
//        Customer customer1 = new Customer();
//        customer1.setName("Толстой Л.Н.");
//        customer1.setEmail("tolstoy@russia.ru");
//        customer1 = customerService.save(customer1);
//
//        Customer customer2 = new Customer();
//        customer2.setName("Петухов Л.Н.");
//        customer2.setEmail("petuhov@russia.ru");
//        customer2 = customerService.save(customer2);
//
//
//        Customer customer3 = new Customer();
//        customer3.setName("Чехов А.П.");
//        customer3.setEmail("chehov@russia.ru");
//        customer3 = customerService.save(customer3);
//
//        // добавляем оперы
//        Opera opera1 = operaService.save("отелло", "Пьеса по Шекспиру", 18);
//        Opera opera2 = operaService.save("Вишневый сад", "Пьеса по Чехову", 3);
//        Opera opera3 = operaService.save("Гамлет", "Пьеса по Шекспиру", 18);
//
//        // меняем оперу
//        Opera opera4 = operaService.getOperaByName("отелло");
//        opera4.setName("Отелло");
//        operaService.save(opera4);
//        operaService.deleteOpera(opera3);
//
//        // вывод всех опер
//        System.out.println("Список премьер:");
//        operaService.getOperas().forEach(System.out::println);
//
//        // добавляем премьеру 1
//        LocalDateTime date = LocalDateTime.now();
//        OperaEvent operaEvent = operaEventService.createOperaEvent(date, opera4, 25);
//
//        // добавляем премьеру 2
//        OperaEvent operaEvent2 = operaEventService.createOperaEvent(date, opera2, 25);
//        operaEvent2 = operaEventService.findByOpera(operaService.getOperaByName("Вишневый сад"));
//
//        // поиск
//        OperaEvent event1 = operaEventService.findByOpera(operaService.getOperaByName("Отелло"));
//
////         Покупка билетов
//        Place place;
//        Order order1 = new Order();
//        place = placeService.findByOperaEventAndNumber(event1, 1);
//        order1.setOperaEvent(event1);
//        order1.setCustomer(customer1);
//        order1.setPlaces(Collections.singletonList(place));
//        order1 = OrderService.createOrder(order1);
//
//        Order order2 = new Order();
//        place = placeService.findByOperaEventAndNumber(event1, 11);
//        order2.setOperaEvent(event1);
//        order2.setCustomer(customer1);
//        order2.setPlaces(Collections.singletonList(place));
//        order2 = OrderService.createOrder(order2);
//
//        Order order3 = new Order();
//        place = placeService.findByOperaEventAndNumber(event1, 12);
//        order3.setOperaEvent(event1);
//        order3.setCustomer(customer1);
//        order3.setPlaces(Collections.singletonList(place));
//        order3 = OrderService.createOrder(order3);
//
//
//        try {
//            Order order4 = new Order();
//            place = placeService.findByOperaEventAndNumber(event1, 26);
//            order4.setOperaEvent(event1);
//            order4.setCustomer(customer2);
//            order4.setPlaces(Collections.singletonList(place));
//            OrderService.createOrder(order4);
//        } catch (Exception err) {
//            logger.error(err.getMessage());
//        }
//
//        try {
//            Order order5 = new Order();
//            place = placeService.findByOperaEventAndNumber(event1, 11);
//            order5.setOperaEvent(event1);
//            order5.setCustomer(customer2);
//            order5.setPlaces(Collections.singletonList(place));
//            OrderService.createOrder(order5);
//        } catch (Exception err) {
//            logger.error(err.getMessage());
//        }
//
//        // Отмена покупки билета
//        OrderService.delete(order1);
//
//        // Афиша
//        System.out.println("Афиша:");
//        operaEventService.getOperaEvents().forEach(System.out::println);

    }

}
