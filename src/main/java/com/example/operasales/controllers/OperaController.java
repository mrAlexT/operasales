package com.example.operasales.controllers;

import com.example.operasales.domain.Opera;
import com.example.operasales.dto.ExceptionDto;
import com.example.operasales.services.OperaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/operas")
public class OperaController {
    OperaService operaService;

    @Autowired
    public OperaController(OperaService operaService) {
        this.operaService = operaService;
    }

    @GetMapping
    public Collection<Opera> getAll(){
        return operaService.getOperas();
    }

    @GetMapping("/{id}")
    public Opera getOpera(@PathVariable("id")String id){
        Opera opera = operaService.getOpera(id);
        return opera;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Opera createOpera(@RequestBody Opera opera){
        Opera result = operaService.save(opera);
        return result;
    }
}
