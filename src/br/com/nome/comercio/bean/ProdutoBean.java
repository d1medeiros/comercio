package br.com.nome.comercio.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.nome.comercio.dao.MarcaDao;
import br.com.nome.comercio.dao.ProdutoDao;
import br.com.nome.comercio.modelo.Marca;
import br.com.nome.comercio.modelo.Produto;
import br.com.nome.comercio.tx.Transacional;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Produto produto = new Produto();
	private Integer marcaId;
	private List<Produto> produtos;
	@Inject
	private ProdutoDao produtoDao;
	@Inject
	private MarcaDao marcaDao;
	@Inject
	private FacesContext context;
	

	public void setMarcaId(Integer marcaId) {
		this.marcaId = marcaId;
	}

	public Integer getMarcaId() {
		return marcaId;
	}

	public Produto getProduto() {
		return produto;
	}

	public List<Produto> getProdutos() {
		if (this.produtos == null) {
			this.produtos = this.produtoDao.listaTodos();
		}
		return produtos;
	}

	public List<Marca> getMarcas() {
		return this.marcaDao.listaTodos();
	}

	public List<Marca> getMarcasDoProduto() {
		return this.produto.getMarcas();
	}

	public void carregarProdutoPelaId() {
		this.produto = this.produtoDao.buscaPorId(this.produto.getId());
	}

	public void gravarMarca() {
		Marca marca = this.marcaDao.buscaPorId(this.marcaId);
		this.produto.adicionaMarca(marca);
		System.out.println("Escrito por: " + marca.getNome());
	}
	
	@Transacional
	public void gravar() {
		System.out.println("Gravando produto " + this.produto.getNome());
		if (produto.getMarcas().isEmpty()) {
			context.addMessage("marca",
					new FacesMessage("Produto deve ter pelo menos um Marca."));
			return;
		}
		if (this.produto.getId() == null) {
			this.produtoDao.adiciona(this.produto);
			this.produtos = this.produtoDao.listaTodos();
		} else {
			this.produtoDao.atualiza(this.produto);
		}
		this.produto = new Produto();
	}

	@Transacional
	public void remover(Produto produto) {
		System.out.println("Removendo produto");
		this.produtoDao.remove(produto);
		this.produtos = this.produtoDao.listaTodos();
	}

	public void removerMarcaDoProduto(Marca marca) {
		this.produto.removeMarca(marca);
	}

	public void carregar(Produto produto) {
		System.out.println("Carregando produto");
		this.produto = this.produtoDao.buscaPorId(produto.getId());
	}

	public String formMarca() {
		System.out.println("Chamanda do formulário do Marca.");
		return "marca?faces-redirect=true";
	}

}
