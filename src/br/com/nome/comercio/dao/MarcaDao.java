package br.com.nome.comercio.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.nome.comercio.modelo.Marca;

public class MarcaDao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Marca> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Marca>(this.em, Marca.class);
	}

	public Marca buscaPorId(Integer marcaId) {
		return this.dao.buscaPorId(marcaId);
	}

	public void adiciona(Marca marca) {
		this.dao.adiciona(marca);
	}

	public void atualiza(Marca marca) {
		this.dao.atualiza(marca);
	}

	public void remove(Marca marca) {
		this.dao.remove(marca);
	}

	public List<Marca> listaTodos() {
		return this.dao.listaTodos();
	}

}