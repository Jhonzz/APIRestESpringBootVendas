package com.jpvendas.gestaovendas.controlador;

import com.jpvendas.gestaovendas.DTO.venda.ClienteVendaResponseDTO;
import com.jpvendas.gestaovendas.DTO.venda.VendaRequestDTO;
import com.jpvendas.gestaovendas.servico.VendaServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Venda")
@RestController
@RequestMapping("/venda")
public class VendaControlador {

    @Autowired
    private VendaServico vendaServico;

    @Operation(summary = "Listar vendas por ID Cliente")
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorIDCliente(@PathVariable Long idCliente){
        return ResponseEntity.ok(vendaServico.listarVendaPorCliente(idCliente));
    }

    @Operation(summary = "Listar venda por ID")
    @GetMapping("/{idVenda}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorID(@PathVariable Long idVenda){
        return ResponseEntity.ok(vendaServico.listarVendaPorCodigo(idVenda));
    }

    @Operation(summary = "Registrar venda")
    @PostMapping("/cliente/{idCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> salvar(@PathVariable Long idCliente, @RequestBody @Valid VendaRequestDTO vendaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaServico.salvar(idCliente, vendaDTO));
    }

    @Operation(summary = "Atualizar venda")
    @PutMapping("/{idVenda}/cliente/{idCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> atualizar(@PathVariable Long idVenda, @PathVariable Long idCliente, @RequestBody @Valid VendaRequestDTO vendaRequestDTO){
        return ResponseEntity.ok(vendaServico.atualizar(idVenda, idCliente, vendaRequestDTO));
    }

    @Operation(summary = "Deletar venda")
    @DeleteMapping("/{idVenda}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarVenda(@PathVariable Long idVenda){
        vendaServico.deletar(idVenda);
    }

}
