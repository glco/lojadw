package br.uff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "compra")
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	@ManyToOne
	@JoinColumn(name="idcliente", foreignKey=@ForeignKey(name="fk_cliente_id"))
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name="idproduto", foreignKey=@ForeignKey(name="fk_produto_id"))
	private Produto produto;
	
	public Compra() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto prod)  {
		this.produto = prod;
	};
}
