package com.jpvendas.gestaovendas.controlador;

import com.jpvendas.gestaovendas.DTO.cliente.ClienteRequestDTO;
import com.jpvendas.gestaovendas.DTO.cliente.ClienteResponseDTO;
import com.jpvendas.gestaovendas.entidades.Cliente;
import com.jpvendas.gestaovendas.servico.ClienteServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Cliente")
@RestController
@RequestMapping("/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteServico clienteServico;

    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public List<ClienteResponseDTO> listarClientes(){
        return clienteServico.listarClientes().stream().map(cliente -> ClienteResponseDTO.converterEntidadeParaDTO(cliente)).collect(Collectors.toList());
    }
    @Operation(summary = "Listar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> listarClientePorID(@PathVariable Long id){
        Optional<Cliente> cliente = clienteServico.listarClientePorID(id);
        return cliente.isPresent() ? ResponseEntity.ok(ClienteResponseDTO.converterEntidadeParaDTO(cliente.get())) : ResponseEntity.notFound().build();
    }
    @Operation(summary = "Salvar cliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvarCLiente(@Valid @RequestBody ClienteRequestDTO cliente){
        Cliente clienteSalvo = clienteServico.salvarCliente(cliente.converterDTOParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDTO.converterEntidadeParaDTO(clienteSalvo));
    }

    @Operation(summary = "Atualizar cliente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable Long id,@RequestBody @Valid ClienteRequestDTO cliente){
        Cliente clienteAtualizado = clienteServico.atualizarCliente(id, cliente.converterDTOParaEntidade(id));
        return ResponseEntity.ok(ClienteResponseDTO.converterEntidadeParaDTO(clienteAtualizado));
    }

    @Operation(summary = "Deletar cliente")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Long id){
        clienteServico.deletarCliente(id);
    }
}
