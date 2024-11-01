package com.jpvendas.gestaovendas.servico;

import com.jpvendas.gestaovendas.DTO.venda.ClienteVendaResponseDTO;
import com.jpvendas.gestaovendas.DTO.venda.VendasResponseDTO;
import com.jpvendas.gestaovendas.entidades.Cliente;
import com.jpvendas.gestaovendas.entidades.ItemVenda;
import com.jpvendas.gestaovendas.entidades.Venda;
import com.jpvendas.gestaovendas.excecao.RegraNegocioException;
import com.jpvendas.gestaovendas.repositorio.ItemVendaRepositorio;
import com.jpvendas.gestaovendas.repositorio.VendasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaServico extends AbstractVendaServico{
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
                map(venda -> converterVendaParaVendaDTO(venda, itemVendaRepositorio.findByVendaCodigo(venda.getCodigo()))).collect(Collectors.toList());

       return new ClienteVendaResponseDTO(cliente.getNome(), vendaResponseDTOList);
    }

    public ClienteVendaResponseDTO listarVendaPorCodigo(Long codigoVenda){
        Venda venda = validarVendaExiste(codigoVenda);
        List<ItemVenda> itensVendaList = itemVendaRepositorio.findByVendaCodigo(venda.getCodigo());
        return new ClienteVendaResponseDTO(venda.getCliente().getNome(), Arrays.asList(converterVendaParaVendaDTO(venda, itensVendaList)));
        //como o objeto pede por uma lista então usei arrays.aslist para que vire uma lista mesmo passando uma venda
    }

    private Venda validarVendaExiste(Long codigoVenda) {
        Optional<Venda> vendaEncontrada = vendasRepositorio.findById(codigoVenda);
        if (vendaEncontrada.isEmpty()){
            throw new RegraNegocioException(String.format("Venda de codigo %s não encontrada no sistema", codigoVenda));
        }
        return vendaEncontrada.get();
    }


    private Cliente validarClienteExiste(Long codigoCliente) {
        Optional<Cliente> clienteEncontrado = clienteServico.listarClientePorID(codigoCliente);
        if (clienteEncontrado.isEmpty()){
            throw new RegraNegocioException(String.format("Cliente de codigo %s não encontrado no sistema", codigoCliente));
        }
        return clienteEncontrado.get();
    }


}







