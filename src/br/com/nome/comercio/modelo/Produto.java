package br.com.nome.comercio.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private double preco;
	@Temporal(TemporalType.DATE)
	private Calendar dataCadastro = Calendar.getInstance();
	@Temporal(TemporalType.DATE)
	private Calendar dataVenda = Calendar.getInstance();
	@ManyToMany
	private List<Marca> marcas = new ArrayList<Marca>();
	

	public Produto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataVenda() {
		return dataVenda;
	}
	
	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}
	
	public void adicionaMarca(Marca marca) {
		this.marcas.add(marca);
	}

	public void removeMarca(Marca marca) {
		this.marcas.remove(marca);
	}

}