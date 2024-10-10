package br.univille.espacotuplas.service;

import java.util.List;
import br.univille.espacotuplas.entity.Cliente;

public interface ClienteService {
    List<Cliente> getAll();
    Cliente save(Cliente cliente);
    Cliente delete(long id);
    Cliente getById(long id);
}