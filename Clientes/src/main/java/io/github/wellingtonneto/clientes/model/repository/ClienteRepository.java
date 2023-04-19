package io.github.wellingtonneto.clientes.model.repository;

import io.github.wellingtonneto.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
