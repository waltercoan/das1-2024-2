package br.univille.espacotuplas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.univille.espacotuplas.entity.Cliente;
import br.univille.espacotuplas.repository.ClienteRepository;
import br.univille.espacotuplas.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private StringRedisTemplate template;

    @Override
    public List<Cliente> getAll() {
        return repository.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        repository.save(cliente);

        

        return cliente;
    }

    @Override
    public Cliente delete(long id) {
        var retorno = repository.findById(id);
        if(retorno.isPresent()){
            repository.deleteById(id);
            return retorno.get();
        }
        return null;
    }

    @Override
    public Cliente getById(long id) {
        ValueOperations<String, String> operacaoredis
                = template.opsForValue();
        //String.format("%d")
        //operacaoredis.get(operacaoredis)
        var retorno = repository.findById(id);
        if(retorno.isPresent())
            return retorno.get();
        return null;
    }

    private String clienteToJson(Cliente cliente){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(cliente);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
    private Cliente jsonToCliente(String cliente){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(cliente, Cliente.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    
}