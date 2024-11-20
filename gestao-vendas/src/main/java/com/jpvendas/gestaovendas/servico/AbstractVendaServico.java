package com.jpvendas.gestaovendas.servico;

import com.jpvendas.gestaovendas.DTO.venda.ClienteVendaResponseDTO;
import com.jpvendas.gestaovendas.DTO.venda.ItemVendaRequestDTO;
import com.jpvendas.gestaovendas.DTO.venda.ItemVendaResponseDTO;
import com.jpvendas.gestaovendas.DTO.venda.VendasResponseDTO;
import com.jpvendas.gestaovendas.entidades.ItemVenda;
import com.jpvendas.gestaovendas.entidades.Produto;
import com.jpvendas.gestaovendas.entidades.Venda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractVendaServico {

    protected VendasResponseDTO converterVendaParaVendaDTO(Venda venda, List<ItemVenda> itensVendaList){
        List<ItemVendaResponseDTO> itensVendasResponseDTO = itensVendaList.stream()
                .map(itemVenda -> converterItemVendaParaDTO(itemVenda)).collect(Collectors.toList());
        return new VendasResponseDTO(venda.getCodigo(), venda.getData(), itensVendasResponseDTO);
    }

    protected ItemVendaResponseDTO converterItemVendaParaDTO(ItemVenda itemVenda){
        return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(), itemVenda.getProduto().getCodigo(),
                itemVenda.getProduto().getDescricao());
    }

    protected ClienteVendaResponseDTO retornaClienteVendaResponseDTO(Venda venda, List<ItemVenda> itensVendaList){
        return new ClienteVendaResponseDTO(venda.getCliente().getNome(),
                Arrays.asList(converterVendaParaVendaDTO(venda, itensVendaList)));
        //como o objeto pede por uma lista então usei arrays.aslist para que vire uma lista mesmo passando uma venda
    }

    protected ItemVenda criandoItemVenda(ItemVendaRequestDTO itemVendaDTO, Venda vendaDTO){
        return new ItemVenda(itemVendaDTO.getQuantidade(), itemVendaDTO.getPrecoVendido(),
                new Produto(itemVendaDTO.getCodigoProduto()), vendaDTO);
    }
}
