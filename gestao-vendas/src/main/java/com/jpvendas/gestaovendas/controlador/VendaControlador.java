package com.jpvendas.gestaovendas.controlador;

import com.jpvendas.gestaovendas.DTO.venda.ClienteVendaResponseDTO;
import com.jpvendas.gestaovendas.servico.VendaServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
