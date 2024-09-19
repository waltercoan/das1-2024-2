package br.univille.microkernel_kernel.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.microkernel_kernel.service.DefaultService;


@RestController
@RequestMapping("/")
public class HomeAPIController {

    @Autowired
    private Map<String, DefaultService> services;

    @GetMapping
    public ResponseEntity<String> index(){
        String servicesList = null;
        if(services != null){
            servicesList = Arrays.toString(services.keySet().toArray());
            for(var serviceName : services.keySet()){
                DefaultService oneservice = services.get(serviceName);
                oneservice.doWork(null);
            }
        }

        return new ResponseEntity<String>(servicesList, HttpStatus.OK);
    }
}
