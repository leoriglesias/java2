package com.lpoo2.trabalhoJPA.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lpoo2.trabalhoJPA.pojo.Diaria;
import com.lpoo2.trabalhoJPA.pojo.DiariaAvulsa;
import com.lpoo2.trabalhoJPA.pojo.Pessoa;
import com.lpoo2.trabalhoJPA.pojo.PessoaFisica;
import com.lpoo2.trabalhoJPA.pojo.PessoaJuridica;

public class PessoaDAOTest {
	PessoaDAO pessoaDAO;
	Pessoa pessoa;
	
	@Before
	public void setup() {
		pessoaDAO = new PessoaDAO();
		pessoa = new PessoaFisica();
	}
	
	@Test
	public void testSalva() {	
		pessoaDAO.salva(pessoa);	
		assertThat(pessoa.getId()).isNotNull();
	}
	
	@Test
	public void testAtualiza() {
		pessoaDAO.salva(pessoa);
		
		pessoa.setNome("Augusto");
		pessoaDAO.atualiza(pessoa);
;		Pessoa p = pessoaDAO.busca(pessoa.getId());
		assertThat(p.getNome()).isEqualTo("Augusto")
							   .isNotEmpty()
							   .isNotNull();
		assertThat(p.getNome().length()).isEqualTo(7);					  
	}

	@Test
	public void testRemove() {
		pessoaDAO.salva(pessoa);
		Pessoa p = pessoaDAO.busca(pessoa.getId());
		assertThat(p.getId()).isNotNull();
		pessoaDAO.remove(p.getId());
		Pessoa removida = pessoaDAO.busca(pessoa.getId());
		assertThat(removida).isNull();
;
	}

	@Test
	public void testBusca() {
		pessoaDAO.salva(pessoa);
		Pessoa p = pessoaDAO.busca(pessoa.getId());
		assertThat(p).isEqualTo(pessoa);
	}

	@Test
	public void testBuscaTodos() {
		pessoaDAO.salva(pessoa);
		Pessoa pj = new PessoaJuridica();
		pessoaDAO.salva(pj);
		List<Pessoa> lista = pessoaDAO.buscaTodos();
		assertThat(lista).contains(pj, pessoa);
	}

	@Test
	public void testBuscaPorEmail() {
		pessoa.setEmail("email@gmail.com");
		pessoaDAO.salva(pessoa);
		List <Pessoa> p = pessoaDAO.buscaPorEmail("email@gmail.com");
		assertThat(p).hasSize(1)
					 .isNotNull();
	}
	
	@Test
	public void testBuscaPorTelefone() {
		pessoa.setTelefone("99998888");
		pessoaDAO.salva(pessoa);
		List <Pessoa> p = pessoaDAO.buscaPorTelefone("99998888");
		assertThat(p).hasSize(1)
					 .isNotNull();
	}

}
