package br.edu.ifrs.canoas.jee.webapp.model.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;

import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaReservada;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Reserva;

public class ReservaTest {

	Reserva reserva;

	@Test
	public void testaReservaEmBranco() {
		reserva = new Reserva();
		assertThat(reserva).isNotNull();
	}
	
	@Test
	public void testaDadosReserva() {
		Reserva reserva1 = criaReserva(criaPessoa());
		assertThat(reserva1.getData()).isEqualTo(new Date(15/12/2018));
		assertThat(reserva1.getValor()).isEqualTo(100D);
		assertThat(reserva1.getPessoa()).isEqualTo(criaPessoa());
		assertThat(reserva1.getId()).isNull();
	}
	
	
	public Reserva criaReserva(PessoaJuridica pj) {
		Reserva r = new Reserva();
		r.setData(new Date(15/12/2018));
		r.setValor(100D);
		r.setPessoa(pj);
		r.setId(null);
		return r;
	}
	
	public PessoaJuridica criaPessoa() {
		PessoaJuridica pj = new PessoaJuridica();
		pj.setRazaoSocial("razaoSocial");
		pj.setCnpj("1234567891011");
		pj.setInscricaoEstadual("inscricaoEstadual");
		pj.setInscricaoMunicipal("inscricaoMunicipal");
		return pj;
	}
}
