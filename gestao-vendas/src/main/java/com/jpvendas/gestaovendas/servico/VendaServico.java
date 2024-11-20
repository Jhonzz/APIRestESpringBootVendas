package com.jpvendas.gestaovendas.servico;

import com.jpvendas.gestaovendas.DTO.venda.ClienteVendaResponseDTO;
import com.jpvendas.gestaovendas.DTO.venda.ItemVendaRequestDTO;
import com.jpvendas.gestaovendas.DTO.venda.VendaRequestDTO;
import com.jpvendas.gestaovendas.DTO.venda.VendasResponseDTO;
import com.jpvendas.gestaovendas.entidades.Cliente;
import com.jpvendas.gestaovendas.entidades.ItemVenda;
import com.jpvendas.gestaovendas.entidades.Produto;
import com.jpvendas.gestaovendas.entidades.Venda;
import com.jpvendas.gestaovendas.excecao.RegraNegocioException;
import com.jpvendas.gestaovendas.repositorio.ItemVendaRepositorio;
import com.jpvendas.gestaovendas.repositorio.VendasRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaServico extends AbstractVendaServico {
    private VendasRepositorio vendasRepositorio;
    private ItemVendaRepositorio itemVendaRepositorio;
    private ClienteServico clienteServico;
    private ProdutoServico produtoServico;

    @Autowired
    public VendaServico(VendasRepositorio vendasRepositorio, ItemVendaRepositorio itemVendaRepositorio, ClienteServico clienteServico, ProdutoServico produtoServico) {
        this.vendasRepositorio = vendasRepositorio;
        this.itemVendaRepositorio = itemVendaRepositorio;
        this.clienteServico = clienteServico;
        this.produtoServico = produtoServico;
    }

    public ClienteVendaResponseDTO listarVendaPorCliente(Long codigoCliente) { //realiza busca de vendas baseado no ID informado
        Cliente cliente = validarClienteExiste(codigoCliente);
        List<VendasResponseDTO> vendaResponseDTOList = vendasRepositorio.findByClienteCodigo(codigoCliente).stream().
                map(venda -> converterVendaParaVendaDTO(venda, itemVendaRepositorio.findByVendaPorCodigo(venda.getCodigo()))).collect(Collectors.toList());

        return new ClienteVendaResponseDTO(cliente.getNome(), vendaResponseDTOList);
    }

    public ClienteVendaResponseDTO listarVendaPorCodigo(Long codigoVenda) {
        Venda venda = validarVendaExiste(codigoVenda);
        List<ItemVenda> itensVendaList = itemVendaRepositorio.findByVendaPorCodigo(venda.getCodigo());
        return retornaClienteVendaResponseDTO(venda, itensVendaList);
    }

    //Caso haja erro o sistema irá realizar um rollback
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ClienteVendaResponseDTO salvar(Long codigoCliente, VendaRequestDTO venda) {
        Cliente clienteEncontrado = validarClienteExiste(codigoCliente);
        validarProdutoExisteEAtualizarQuantidade(venda.getItemVendaRequestDTOList());
        Venda vendaSalva = salvarVenda(clienteEncontrado, venda);

        return retornaClienteVendaResponseDTO(vendaSalva, itemVendaRepositorio.findByVendaPorCodigo(vendaSalva.getCodigo()));
    }

    public ClienteVendaResponseDTO atualizar(Long codigoVenda, Long codigoCliente, VendaRequestDTO vendaRequestDTO){
        validarVendaExiste(codigoVenda);
        Cliente cliente = validarClienteExiste(codigoCliente);
        List<ItemVenda> itensVenda = itemVendaRepositorio.findByVendaPorCodigo(codigoVenda);
        validarProdutoExisteEDevolvelEstoque(itensVenda);
        validarProdutoExisteEAtualizarQuantidade(vendaRequestDTO.getItemVendaRequestDTOList());
        itemVendaRepositorio.deleteAll(itensVenda);
        Venda vendaAtualizada = atualizarVenda(codigoVenda, cliente, vendaRequestDTO);

        return retornaClienteVendaResponseDTO(vendaAtualizada, itemVendaRepositorio.findByVendaPorCodigo(codigoVenda));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deletar(Long codigoVenda){
        validarVendaExiste(codigoVenda);
        List<ItemVenda> itensVenda = itemVendaRepositorio.findByVendaPorCodigo(codigoVenda);
        validarProdutoExisteEDevolvelEstoque(itensVenda);
        itemVendaRepositorio.deleteAll(itensVenda);
        vendasRepositorio.deleteById(codigoVenda);
    }

    private void validarProdutoExisteEDevolvelEstoque(List<ItemVenda> itemVenda){
        itemVenda.forEach(item -> {
            Produto produto = produtoServico.validarProdutoExiste(item.getProduto().getCodigo());
            produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
            produtoServico.atualizarQuantidadeEmEstoque(produto);
        });
    }

    private Venda salvarVenda(Cliente cliente, VendaRequestDTO vendaDTO) {
        Venda vendaSalva = vendasRepositorio.save(new Venda(vendaDTO.getData(), cliente));
        vendaDTO.getItemVendaRequestDTOList().stream().map(itemvendaDto -> criandoItemVenda(itemvendaDto, vendaSalva))
                .forEach(itemVenda -> itemVendaRepositorio.save(itemVenda));
        return vendaSalva;
    }

    private Venda atualizarVenda(Long codigoVenda, Cliente cliente, VendaRequestDTO vendaDTO) {
        Venda vendaSalva = vendasRepositorio.save(new Venda(codigoVenda,vendaDTO.getData(), cliente));
        vendaDTO.getItemVendaRequestDTOList().stream().map(itemvendaDto -> criandoItemVenda(itemvendaDto, vendaSalva))
                .forEach(itemVenda -> itemVendaRepositorio.save(itemVenda));
        return vendaSalva;
    }

    //Ira validar se o produto existe e se há a quantidade necessaria em estoque, caso tenha o sistema ira diminuir a quantidade em estoque
    private void validarProdutoExisteEAtualizarQuantidade(List<ItemVendaRequestDTO> itemVendaRequestDTOList) {
        itemVendaRequestDTOList.forEach(item -> {
                    Produto produto = produtoServico.validarProdutoExiste(item.getCodigoProduto());
                    validarQuantidadeProdutoDisponivel(produto, item.getQuantidade());
                    produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
                    produtoServico.atualizarQuantidadeEmEstoque(produto);
                }
        );
    }

    //valida se ha a quantidade de produtos informados na venda
    private void validarQuantidadeProdutoDisponivel(Produto produto, Integer quantidadeProduto) {
        if (produto.getQuantidade() < quantidadeProduto) {
            throw new RegraNegocioException(String.format("Quantidade %s do produto %s não disponível em estoque", quantidadeProduto, produto.getDescricao()));
        }
    }

    private Venda validarVendaExiste(Long codigoVenda) {
        Optional<Venda> vendaEncontrada = vendasRepositorio.findById(codigoVenda);
        if (vendaEncontrada.isEmpty()) {
            throw new RegraNegocioException(String.format("Venda de codigo %s não encontrada no sistema", codigoVenda));
        }
        return vendaEncontrada.get();
    }


    private Cliente validarClienteExiste(Long codigoCliente) {
        Optional<Cliente> clienteEncontrado = clienteServico.listarClientePorID(codigoCliente);
        if (clienteEncontrado.isEmpty()) {
            throw new RegraNegocioException(String.format("Cliente de codigo %s não encontrado no sistema", codigoCliente));
        }
        return clienteEncontrado.get();
    }

}







