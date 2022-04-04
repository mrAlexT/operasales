package com.example.operasales.controllers;

import com.example.operasales.domain.OperaEvent;
import com.example.operasales.services.OperaEventService;
import com.example.operasales.services.OperaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/admin/api/events")
public class OperaEventAdminController {
    OperaEventService operaEventService;
    OperaService operaService;

    @Autowired
    public OperaEventAdminController(OperaEventService operaEventService, OperaService operaService) {
        this.operaEventService = operaEventService;
        this.operaService = operaService;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public OperaEvent createOperaEvent(@RequestBody OperaEvent operaEvent) {
        OperaEvent result = operaEventService.createOperaEvent(operaEvent);
        return result;
    }

    @DeleteMapping
    public void deleteOperaEvent(@RequestBody OperaEvent operaEvent) {
        operaEventService.delete(operaEvent);
    }
}
