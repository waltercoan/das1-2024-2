package br.univille.microkernel_pluggin.service;
import java.util.HashMap;
import org.springframework.http.HttpStatus;

public interface DefaultService {
    
    public HttpStatus doWork(HashMap<String, String> params);
}
