package br.uff.model;

import java.util.HashMap;

public class ShoppingCart {
	
	private HashMap<Integer,Produto> produtos;
	
	public ShoppingCart() {
		this.produtos = new HashMap<Integer,Produto>();
	}
	public HashMap<Integer,Produto> getProdutos(){
		return this.produtos;
	}
	public void setProdutos(HashMap<Integer,Produto> p) {
		this.produtos = p;
	}
	public void addProduto(Produto p) {
		this.produtos.put(p.getId(), p);
	}
	public void removeProduto(int id) {
		this.produtos.remove(id);
	}
}
