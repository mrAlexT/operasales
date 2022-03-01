package com.example.operasales;

import com.example.operasales.domain.Opera;
import com.example.operasales.domain.OperaEvent;
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

        // добавляем оперы
        Opera opera = operaService.createOpera("Отелло", "Пьеса по Шекспиру", 18);
        Opera opera2 = operaService.createOpera("Вишневый сад", "Пьеса по Чехову", 3);
        Opera opera3 = operaService.createOpera("Гамлет", "Пьеса по Шекспиру", 18);

        // меняем оперу
        opera.setName("Отелло");
        operaService.createOpera(opera);
        operaService.deleteOpera(opera3);

        // вывод всех опер
        System.out.println("Список премьер:");
        operaService.getOperas().forEach(System.out::println);

        // добавляем премьеру 1
        LocalDateTime date = LocalDateTime.now();
        OperaEvent operaEvent = operaEventService.createOperaEvent(date, opera, 25);
        operaEventService.setBookEvent(operaEvent, 1);
        operaEventService.setBookEvent(operaEvent, 11);
        operaEventService.setBookEvent(operaEvent, 12);
        operaEventService.cancelBookEvent(operaEvent, 12);

        // добавляем премьеру 2
        operaEventService.createOperaEvent(date, opera2, 25);

        System.out.println("Афиша:");
        operaEventService.getOperaEvents().forEach(System.out::println);

        System.out.println("Кол-во свободных мест для премьеры " + operaEvent);
        System.out.println(Arrays.toString(operaEvent.getSeats()));
    }

}
