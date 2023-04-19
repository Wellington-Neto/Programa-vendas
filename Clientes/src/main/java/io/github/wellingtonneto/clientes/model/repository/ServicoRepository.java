package io.github.wellingtonneto.clientes.model.repository;

import io.github.wellingtonneto.clientes.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
