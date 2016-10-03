package br.com.nome.comercio.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import br.com.nome.comercio.modelo.Marca;
import br.com.nome.comercio.modelo.Produto;
import br.com.nome.comercio.modelo.Usuario;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Marca m1 = geraMarca("Marca Um");
		em.persist(m1);
		Marca m2 = geraMarca("Marca Dois");
		em.persist(m2);
		Marca m3 = geraMarca("Marca Tres");
		em.persist(m3);
		Marca m4 = geraMarca("Marca Quatro");
		em.persist(m4);

		Produto p1 = geraProduto("Produto 1",
				"01/01/1891", 16.90, m1);
		Produto p2 = geraProduto("Produto 2",
				"01/01/1891", 16.90, m2);
		Produto p3 = geraProduto("Produto 3",
				"01/01/1891", 16.90, m3);

		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		
		Usuario u = new Usuario();
		u.setEmail("diego@email.com");
		u.setSenha("1234");
		
		em.persist(u);

		em.getTransaction().commit();
		em.close();

	}

	private static Marca geraMarca(String nome) {
		Marca marca = new Marca();
		marca.setNome(nome);
		return marca;
	}

	private static Produto geraProduto(String nome, String data,
			double preco, Marca marca) {
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setDataCadastro(parseData(data));
		produto.setPreco(preco);
		produto.adicionaMarca(marca);
		return produto;
	}

	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
