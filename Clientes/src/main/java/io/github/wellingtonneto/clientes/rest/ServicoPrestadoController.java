package io.github.wellingtonneto.clientes.rest;

import io.github.wellingtonneto.clientes.model.entity.ServicoPrestado;
import io.github.wellingtonneto.clientes.model.repository.ClienteRepository;
import io.github.wellingtonneto.clientes.model.repository.ServicoPrestadoRepository;
import io.github.wellingtonneto.clientes.rest.dto.ServicoPrestadoDTO;
import io.github.wellingtonneto.clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/servicos-prestados")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;

    private final ServicoPrestadoRepository repository;

    private final BigDecimalConverter bigDecimalConverter;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO servicoPrestadoDTO){
        LocalDate data = LocalDate.parse(servicoPrestadoDTO.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(servicoPrestadoDTO.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(clienteRepository.findById(servicoPrestadoDTO.getIdCliente()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe o Cliente cadastrado.")));
        servicoPrestado.setValor(bigDecimalConverter.converter(servicoPrestadoDTO.getPreco()));

        return repository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> obterTodos(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ServicoPrestado acharPorId(@PathVariable Long id){
        return  repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado."));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        repository.findById(id)
                .map( servicoPrestado -> {
                    repository.delete(servicoPrestado);
                    return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid ServicoPrestado servicoPrestadoAtualizado){
        repository.findById(id)
                .map( servicoPrestado -> {
                    servicoPrestadoAtualizado.setId(servicoPrestado.getId());
                   return repository.save(servicoPrestadoAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado."));

    }

}
