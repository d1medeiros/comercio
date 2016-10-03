package br.com.nome.comercio.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.nome.comercio.dao.MarcaDao;
import br.com.nome.comercio.modelo.Marca;
import br.com.nome.comercio.tx.Transacional;

@Named
@ViewScoped
public class MarcaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Marca marca = new Marca();

	private Integer marcaId;

	@Inject
	private MarcaDao dao;

	public Integer getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Integer marcaId) {
		this.marcaId = marcaId;
	}

	public void carregarMarcaPelaId() {
		this.marca = this.dao.buscaPorId(marcaId);
	}

	@Transacional
	public String gravar() {
		System.out.println("Gravando marca " + this.marca.getNome());

		if (this.marca.getId() == null) {
			this.dao.adiciona(this.marca);
		} else {
			this.dao.atualiza(this.marca);
		}

		this.marca = new Marca();

		return "produto?faces-redirect=true";
	}

	@Transacional
	public void remover(Marca marca) {
		System.out.println("Removendo marca " + marca.getNome());
		this.dao.remove(marca);
	}

	public List<Marca> getMarcas() {
		return this.dao.listaTodos();
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
}
