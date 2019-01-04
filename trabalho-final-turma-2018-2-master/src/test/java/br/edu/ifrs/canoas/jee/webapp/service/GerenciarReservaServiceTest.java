package br.edu.ifrs.canoas.jee.webapp.service;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Date;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import br.edu.ifrs.canoas.jee.webapp.model.dao.ReservaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.UsuarioDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaReservada;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Reserva;
import br.edu.ifrs.canoas.jee.webapp.model.entity.SituacaoQuarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.TipoDeQuarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Usuario;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

public class GerenciarReservaServiceTest {

	@Inject
	GerenciarReservaService gerenciarReservaService;

	@Inject
    Logger log;

	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(GerenciarReservaService.class, ReservaDAO.class, org.apache.commons.lang3.StringUtils.class, Mensagens.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(new File("src/main/webapp", "WEB-INF/faces-config.xml"))
                .addAsResource(new File("src/main/resources/ValidationMessages.properties"), "ValidationMessages.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                ;
    }

	public void salvaReserva() {
		Reserva reserva = criaReserva();
		gerenciarReservaService.salvaReserva(reserva);
		assertNotNull(reserva.getId());
		log.info("A reserva de data " + reserva.getData().toString() + " foi persistida com o id " + reserva.getId());
	}
	
	public void salvaReserva_DataInvalida() {
		Reserva reserva = criaReserva();
		reserva.setData(new Date(15/10/2015));
		gerenciarReservaService.salvaReserva(reserva);
		assertNull(reserva.getId());
		log.info("A reserva deve possuir uma data maior que o dia atual");
	}
	
	public void salvaReserva_ValorInvalido() {
		Reserva reserva = criaReserva();
		reserva.setValor(-20D);;
		gerenciarReservaService.salvaReserva(reserva);
		assertNull(reserva.getId());
		log.info("A reserva deve possuir um valor maior que zero");
	}
	
	public Reserva criaReserva() {
		PessoaJuridica pj = new PessoaJuridica();
		pj.setRazaoSocial("razaoSocial");
		pj.setCnpj("1234567891011");
		pj.setInscricaoEstadual("inscricaoEstadual");
		pj.setInscricaoMunicipal("inscricaoMunicipal");
		
		Reserva r = new Reserva();
		r.setData(new Date(15/12/2018));
		r.setValor(100D);
		r.setPessoa(pj);
		return r;
	}

}
