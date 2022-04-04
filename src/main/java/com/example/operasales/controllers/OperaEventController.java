package com.example.operasales.controllers;

import com.example.operasales.domain.OperaEvent;
import com.example.operasales.services.OperaEventService;
import com.example.operasales.services.OperaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/events")
public class OperaEventController {

    OperaEventService operaEventService;
    OperaService operaService;

    @Autowired
    public OperaEventController(OperaEventService operaEventService, OperaService operaService) {
        this.operaEventService = operaEventService;
        this.operaService = operaService;
    }


    @GetMapping
    public Collection<OperaEvent> getEvents() {
        Collection<OperaEvent> operaEvents = operaEventService.getOperaEvents();
        return operaEvents;
    }

    @GetMapping("/{id}")
    public OperaEvent getEvent(@PathVariable("id") String id) {
        OperaEvent result = operaEventService.findById(id);
        return result;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public OperaEvent createOperaEvent(@RequestBody OperaEvent operaEvent){
        OperaEvent result = operaEventService.createOperaEvent(operaEvent);
        return result;
    }
}
