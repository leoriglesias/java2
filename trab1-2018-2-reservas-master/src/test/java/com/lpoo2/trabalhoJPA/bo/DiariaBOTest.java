package com.lpoo2.trabalhoJPA.bo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.lpoo2.trabalhoJPA.bo.DiariaBO;
import com.lpoo2.trabalhoJPA.dao.DiariaDAO;
import com.lpoo2.trabalhoJPA.dao.PessoaDAO;
import com.lpoo2.trabalhoJPA.pojo.Diaria;
import com.lpoo2.trabalhoJPA.pojo.Endereco;
import com.lpoo2.trabalhoJPA.pojo.Pessoa;
import com.lpoo2.trabalhoJPA.pojo.PessoaFisica;
import com.lpoo2.trabalhoJPA.pojo.Reserva;
import com.lpoo2.trabalhoJPA.pojo.Sexo;


public class DiariaBOTest {
	private DiariaBO diariaBO;
	private PessoaFisica pFis;
	private Pessoa pJur;
	private Endereco endereco;
	private DiariaDAO diariaDAO;
	private Date datahoje;
	private Reserva reserva;
	
	@Before
	public void setup() {
		diariaBO = new DiariaBO();
		endereco = new Endereco();
		diariaDAO = new DiariaDAO();
		datahoje = new Date();
		reserva = new Reserva();
	}
	@Test
	public void fazReservaTest() {
		assertThat(diariaBO.fazReserva(getPessoa(), datahoje , 100.0)).isInstanceOf(Reserva.class);
		
	}
	
	@Test
	public void registraDiariaReservadaTest() {
		Diaria d = diariaBO.registraDiariaReservada(reserva);
		assertThat( diariaDAO.buscaTodos() ).contains(d);
	}
	
	@Test 
	public void pagaDiariaTest() {
		Diaria d = diariaBO.pagaDiaria(getPessoa());
		assertThat( diariaDAO.buscaTodos() ).contains(d);
	}
	
	@Test 
	public void hospedaPessoaFisica() {
		PessoaFisica p = getPessoa();
		Diaria d = diariaBO.pagaDiaria(p);
		diariaBO.hospedaPessoaFisica(d, p);
		assertThat( diariaDAO.buscaTodos() ).contains(d);
		assertThat( diariaDAO.busca(d.getId() ).getHospedes()).contains(p);
		
	}
	public PessoaFisica getPessoa() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		PessoaFisica pessoa = new PessoaFisica();
		Endereco end = new Endereco();
		end.setLogradouro("asopdk");
		end.setUf("RS");
		end.setCep("9232323");
		pessoa.setEmail("fulano@fulano.com");
		pessoa.setNome("Fulano");
		pessoa.setCpf("88888888");
		pessoa.setRg("99999999");
		pessoa.setSexo(Sexo.F);
		pessoa.setDataNascimento(new Date());
		pessoa.setEndereco(end);
		pessoaDAO.salva(pessoa);
		return  pessoa;
	}
}
