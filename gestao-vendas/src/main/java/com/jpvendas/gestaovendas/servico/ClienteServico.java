package com.jpvendas.gestaovendas.servico;

import com.jpvendas.gestaovendas.DTO.cliente.ClienteRequestDTO;
import com.jpvendas.gestaovendas.entidades.Cliente;
import com.jpvendas.gestaovendas.excecao.RegraNegocioException;
import com.jpvendas.gestaovendas.repositorio.ClienteRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServico {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Cliente> listarClientes(){
        return clienteRepositorio.findAll();
    }

    public Optional<Cliente> listarClientePorID(Long id){
        return clienteRepositorio.findById(id);
    }

    public Cliente salvarCliente(Cliente cliente){
        validarClienteJaExistente(cliente);
       return clienteRepositorio.save(cliente);
    }

    public Cliente atualizarCliente(Long id, Cliente cliente){
        Cliente clienteAtualizar = validarClienteExiste(id); //valida se o id informado na atualização existe no sistema
        validarClienteJaExistente(cliente); //valida se o nome do cliente informado ja existe no sistema
        BeanUtils.copyProperties(cliente, clienteAtualizar, "codigo");
        return clienteRepositorio.save(clienteAtualizar);
    }

    public void deletarCliente(Long id){
        clienteRepositorio.deleteById(id);
    }

    private Cliente validarClienteExiste(Long id){
        Optional<Cliente> clienteEncontrado = listarClientePorID(id);
        if (clienteEncontrado.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return clienteEncontrado.get();
    }

    private void validarClienteJaExistente(Cliente cliente){
        Cliente clienteEncontrado = clienteRepositorio.findByNome(cliente.getNome());
        if(clienteEncontrado != null && clienteEncontrado.getCodigo() != cliente.getCodigo()){
            throw new RegraNegocioException(String.format("Cliente %s ja cadastrado no sistema", clienteEncontrado.getNome().toUpperCase()));
        }
    }
}
