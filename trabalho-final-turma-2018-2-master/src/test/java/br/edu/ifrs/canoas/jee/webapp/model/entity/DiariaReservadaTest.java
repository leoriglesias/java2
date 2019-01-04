package br.edu.ifrs.canoas.jee.webapp.model.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;

public class DiariaReservadaTest {
	
	@Inject
	DiariaReservada diariaReservada;
	
	@Test
	public void testaDiariaReservadaEmBranco() {
		DiariaReservada diariaReservada = new DiariaReservada();
		assertThat(diariaReservada).isNotNull();
	}

	@Test
	public void testaDadosDiariaReservada() throws ParseException {
		DiariaReservada diariaReservada = new DiariaReservada();
		diariaReservada = criaDiariaReservada();
		assertThat(diariaReservada.getData()).isEqualTo(new Date(15/12/2018));
		assertThat(diariaReservada.getQuarto()).isEqualTo(criaQuarto());
		assertThat(diariaReservada.getReserva()).isEqualTo(criaReserva(criaPessoa()));
		assertThat(diariaReservada.getQtdDias()).isEqualTo(4);
		assertThat(diariaReservada.getId()).isNull();
	}


	private DiariaReservada criaDiariaReservada()
	{
		Reserva reserva = criaReserva(criaPessoa());
		Quarto quarto = criaQuarto();
		diariaReservada = new DiariaReservada();
		diariaReservada.setReserva(reserva);
		diariaReservada.setData(reserva.getData());
		diariaReservada.setQtdDias(4);
		diariaReservada.setQuarto(quarto);
		diariaReservada.setId(null);
		return diariaReservada;
	}
	
	public Reserva criaReserva(PessoaJuridica pj) {
		Reserva r = new Reserva();
		r.setData(new Date(15/12/2018));
		r.setValor(100D);
		r.setPessoa(pj);
		return r;
	}
	
	public Quarto criaQuarto() {
		Quarto q = new Quarto();
		q.setDescricao("quarto DR");
		q.setNumero("20");
		q.setSituacao(SituacaoQuarto.DISPONIVEL);
		q.setTipo(TipoDeQuarto.MASTER);
		return q;
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
