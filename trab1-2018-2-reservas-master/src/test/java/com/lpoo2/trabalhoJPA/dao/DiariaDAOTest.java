package com.lpoo2.trabalhoJPA.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lpoo2.trabalhoJPA.pojo.Diaria;
import com.lpoo2.trabalhoJPA.pojo.DiariaAvulsa;
import com.lpoo2.trabalhoJPA.pojo.Pessoa;
import com.lpoo2.trabalhoJPA.pojo.PessoaFisica;


public class DiariaDAOTest {
	DiariaDAO diariaDAO = new DiariaDAO();
	Diaria diariaAvulsa;
	
	@Before
	public void setup() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date data = sdf.parse("18/10/2018");
		diariaAvulsa= new DiariaAvulsa(data, getPessoa());
	}
	
	@Test
	public void testSalvaNovaDiariaAvulsa() {

		// salva no banco
		diariaDAO.salva(diariaAvulsa);
		// verifica se salvou
		assertThat(diariaAvulsa.getId()).isNotNull();

	}
	@Test
	public void testAtualizaDiariaAvulsa() throws ParseException {
		diariaDAO.salva(diariaAvulsa);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date newDate = sdf.parse("01/01/2001");
		diariaAvulsa.setData(newDate);
		diariaDAO.atualiza(diariaAvulsa);
		Diaria d = diariaDAO.busca(diariaAvulsa.getId());
		// problema ao comparar data
		assertThat(d.getData().getYear()).isEqualTo(newDate.getYear());
		assertThat(d.getData().getDay()).isEqualTo(newDate.getDay());
		
	}
	
	@Test
	public void testRemoveDiariaAvulsa() {
		diariaDAO.salva(diariaAvulsa);
		Diaria d = diariaDAO.busca(diariaAvulsa.getId());
		assertThat(d.getId()).isNotNull();
		diariaDAO.remove(d.getId());
		Diaria dr = diariaDAO.busca(diariaAvulsa.getId());
		assertThat(dr).isNull();
		
		
	}	
	@Test
	public void testBuscaDiariaAvulsa(){
		diariaDAO.salva(diariaAvulsa);
		Diaria d = diariaDAO.busca(diariaAvulsa.getId());
		assertThat(d).isEqualTo(diariaAvulsa);
	}	
	
	@Test
	public void testBuscaTodosDiariaAvulsa() {
		diariaDAO.salva(diariaAvulsa);
		DiariaAvulsa da = new DiariaAvulsa(new Date(), getPessoa());
		diariaDAO.salva(da);
		List<Diaria> lista = diariaDAO.buscaTodos();
		assertThat(lista).contains(da, diariaAvulsa);	
		
	}
	
	@Test
	public void testBuscaPorDataDiariaAvulsa() {
		Date date = new Date();
		DiariaAvulsa da = new DiariaAvulsa(date, getPessoa());
		diariaDAO.salva(da);
		List<Diaria> lista = diariaDAO.buscaPorData(date);
		assertThat(lista).contains(da);	
		
	}

	public Pessoa getPessoa() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		PessoaFisica pessoa = new PessoaFisica();
		pessoaDAO.salva(pessoa);
		return pessoa;
	}
	/*
	@Test
	public void testaBuscaTodosUsuarios() {
		// Cria usuario
		Usuario u1 = new Usuario();
		Usuario u2 = new Usuario();
		Usuario u3 = new Usuario("email", "senha", "endereco");
		usuarioDAO.salva(u1);
		usuarioDAO.salva(u2);
		usuarioDAO.salva(u3);
		List<Usuario> usuarios = usuarioDAO.busca();
		// Deve ter no mínimo 3 usuários no banco
		assertThat(usuarios).size().isGreaterThan(3);
	}

	@Test
	public void testaUsuarioPorEmail() {

		// Cria usuario
		usuarioDAO.salva(new Usuario("email@do.usuario", "senha",
				"endereco"));
		Usuario usuarioDoBD = usuarioDAO.buscaPorEmail("EMAIL@DO.USUARIO").get(0);
		assertThat(usuarioDoBD.getEmail()).isEqualTo("email@do.usuario");
		assertThat(usuarioDoBD.getId()).isNotNull();

	}
	@Test
	public void testaAtualizaUsuario() {

		Usuario usuario = new Usuario("emailDeAtualizacao", "senha", "endereco");
		// Cria usuario
		usuarioDAO.salva(usuario);
		assertThat(usuario.getId()).isNotNull();
		assertThat(usuario.getEmail()).as("emailDeAtualizacao");
		usuario.setEmail("agora_mudou_o_email");
		usuarioDAO.atualiza(usuario);
		Usuario novoUsuarioRecuperadoDoBanco = usuarioDAO.busca(usuario.getId());
		assertThat(novoUsuarioRecuperadoDoBanco.getEmail()).isEqualTo("agora_mudou_o_email");
	}

	@Test
	public void testaRemoveUsuario() {
		Usuario usuario = new Usuario("emailDeExclusao", "senha", "endereco");
		usuarioDAO.salva(usuario);
		// verifica se salvou com sucesso
		assertThat(usuario.getId()).isNotNull();
		// remove
		usuarioDAO.remove(usuario.getId());
		// remove
		assertThat(usuarioDAO.busca(usuario.getId())).isNull();
		// VERIFICA SE REMOVEU COM SUCESSO
	}
*/

}
