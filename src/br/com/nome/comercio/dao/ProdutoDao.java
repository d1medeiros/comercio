package br.com.nome.comercio.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.nome.comercio.modelo.Produto;

public class ProdutoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Produto> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Produto>(this.em, Produto.class);
	}

	public Produto buscaPorId(Integer produtoId) {
		return this.dao.buscaPorId(produtoId);
	}

	public void adiciona(Produto produto) {
		this.dao.adiciona(produto);
	}

	public void atualiza(Produto produto) {
		this.dao.atualiza(produto);
	}

	public void remove(Produto produto) {
		this.dao.remove(produto);
	}

	public List<Produto> listaTodos() {
		return this.dao.listaTodos();
	}

}