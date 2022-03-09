package com.example.operasales;

import com.example.operasales.domain.Opera;
import com.example.operasales.domain.OperaEvent;
import com.example.operasales.services.OperaEventPlaceService;
import com.example.operasales.services.OperaEventService;
import com.example.operasales.services.OperaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class OperasalesApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(OperasalesApplication.class, args);
        OperaService operaService = ctx.getBean(OperaService.class);
        OperaEventService operaEventService = ctx.getBean(OperaEventService.class);
        OperaEventPlaceService placeService = ctx.getBean(OperaEventPlaceService.class);

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
        operaEventService.setBookEvent(event1, 1);
        operaEventService.setBookEvent(event1, 11);
        operaEventService.setBookEvent(event1, 12);
        operaEventService.cancelBookEvent(event1, 12);
        // operaEventService.cancelBookEvent(event1, 26); // RuntimeException: Количество мест не более 25
        // operaEventService.setBookEvent(event1, 11); // RuntimeException: Место уже занято: 11

        // добавляем премьеру 2
        operaEvent = operaEventService.createOperaEvent(date, opera2, 25);

        OperaEvent event2 = operaEventService.findByOpera(operaService.getOperaByName("Вишневый сад"));
        ;
        operaEventService.setBookEvent(event2, 1);

        System.out.println("Афиша:");
        operaEventService.getOperaEvents().forEach(System.out::println);

    }

}
