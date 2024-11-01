package com.jpvendas.gestaovendas.servico;

import com.jpvendas.gestaovendas.DTO.venda.ItemVendaResponseDTO;
import com.jpvendas.gestaovendas.DTO.venda.VendasResponseDTO;
import com.jpvendas.gestaovendas.entidades.ItemVenda;
import com.jpvendas.gestaovendas.entidades.Venda;

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
}
