package com.jpvendas.gestaovendas.servico;

import com.jpvendas.gestaovendas.DTO.venda.ClienteVendaResponseDTO;
import com.jpvendas.gestaovendas.DTO.venda.ItemVendaResponseDTO;
import com.jpvendas.gestaovendas.DTO.venda.VendasResponseDTO;
import com.jpvendas.gestaovendas.entidades.Cliente;
import com.jpvendas.gestaovendas.entidades.ItemVenda;
import com.jpvendas.gestaovendas.entidades.Venda;
import com.jpvendas.gestaovendas.excecao.RegraNegocioException;
import com.jpvendas.gestaovendas.repositorio.ItemVendaRepositorio;
import com.jpvendas.gestaovendas.repositorio.VendasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaServico {
    private VendasRepositorio vendasRepositorio;
    private ItemVendaRepositorio itemVendaRepositorio;
    private ClienteServico clienteServico;

    @Autowired
    public VendaServico(VendasRepositorio vendasRepositorio, ItemVendaRepositorio itemVendaRepositorio, ClienteServico clienteServico) {
        this.vendasRepositorio = vendasRepositorio;
        this.itemVendaRepositorio = itemVendaRepositorio;
        this.clienteServico = clienteServico;
    }

    public ClienteVendaResponseDTO listarVendaPorCliente(Long codigoCliente){ //realiza busca de vendas baseado no ID informado
        Cliente cliente = validarClienteExiste(codigoCliente);
        List<VendasResponseDTO> vendaResponseDTOList = vendasRepositorio.findByClienteCodigo(codigoCliente).stream().
                map(produto -> converterVendaParaVendaDTO(produto)).collect(Collectors.toList());

       return new ClienteVendaResponseDTO(cliente.getNome(), vendaResponseDTOList);
    }

    private Cliente validarClienteExiste(Long codigoCliente) {
        Optional<Cliente> clienteEncontrado = clienteServico.listarClientePorID(codigoCliente);
        if (clienteEncontrado.isEmpty()){
            throw new RegraNegocioException(String.format("Cliente de codigo %s não encontrado no sistema", codigoCliente));
        }
        return clienteEncontrado.get();
    }

    private VendasResponseDTO converterVendaParaVendaDTO(Venda venda){
        List<ItemVendaResponseDTO> itensVendasResponseDTO = itemVendaRepositorio.findByVendaCodigo(venda.getCodigo()).stream()
                .map(itemVenda -> converterItemVendaParaDTO(itemVenda)).collect(Collectors.toList());
               return new VendasResponseDTO(venda.getCodigo(), venda.getData(), itensVendasResponseDTO);
    }

    private ItemVendaResponseDTO converterItemVendaParaDTO(ItemVenda itemVenda){
        return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(), itemVenda.getProduto().getCodigo(),
                itemVenda.getProduto().getDescricao());
    }

}







