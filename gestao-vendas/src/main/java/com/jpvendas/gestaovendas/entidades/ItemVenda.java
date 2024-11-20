package com.jpvendas.gestaovendas.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "item_venda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_vendido")
    private BigDecimal precoVendido;

    @ManyToOne //varios itens de venda para um produto
    @JoinColumn(name = "codigo_produto", referencedColumnName = "codigo")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "codigo_venda", referencedColumnName = "codigo")
    private Venda venda;

    public ItemVenda(Integer quantidade, BigDecimal precoVendido, Produto produto, Venda venda) {
        this.quantidade = quantidade;
        this.precoVendido = precoVendido;
        this.produto = produto;
        this.venda = venda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemVenda itemVenda = (ItemVenda) o;
        return Objects.equals(codigo, itemVenda.codigo) && Objects.equals(quantidade, itemVenda.quantidade) && Objects.equals(precoVendido, itemVenda.precoVendido) && Objects.equals(produto, itemVenda.produto) && Objects.equals(venda, itemVenda.venda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, quantidade, precoVendido, produto, venda);
    }
}
