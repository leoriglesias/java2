package br.edu.ifrs.canoas.jee.webapp.model.dao;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaReservada;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Reserva;
import br.edu.ifrs.canoas.jee.webapp.model.entity.SituacaoQuarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.TipoDeQuarto;

@RunWith(Arquillian.class)
public class DiariaReservadaDAOTest {

	@Inject
	DiariaReservadaDAO diariaReservadaDAO;
	
	@Inject
	DiariaReservada diariaReservada;
	
	@Inject
    Logger log;

	@Deployment
    public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(DiariaReservadaDAO.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addPackages(true, "org.apache.commons")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void buscaDiariaReservadaPorCriterios() {
		Reserva reserva = criaReserva(criaPessoa());
		Quarto quarto = criaQuarto();
		diariaReservada = new DiariaReservada();
		diariaReservada.setReserva(reserva);
		diariaReservada.setData(reserva.getData());
		diariaReservada.setQtdDias(4);
		diariaReservada.setQuarto(quarto);
		diariaReservadaDAO.insere(diariaReservada);
		assertNotNull(diariaReservadaDAO.buscaPorCriterio(String.valueOf(diariaReservada.getQtdDias())).get(0));
		assertNotNull(diariaReservadaDAO.buscaPorCriterio(String.valueOf(diariaReservada.getData())).get(0));
		assertNotNull(diariaReservada.getId());
		log.info(diariaReservada.getData().toString() + " foi persistido com o id " + diariaReservada.getId());
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
