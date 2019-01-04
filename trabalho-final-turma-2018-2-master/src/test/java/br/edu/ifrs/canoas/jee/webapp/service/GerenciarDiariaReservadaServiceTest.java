package br.edu.ifrs.canoas.jee.webapp.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Date;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;

import br.edu.ifrs.canoas.jee.webapp.model.dao.DiariaReservadaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaReservada;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Reserva;
import br.edu.ifrs.canoas.jee.webapp.model.entity.SituacaoQuarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.TipoDeQuarto;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

public class GerenciarDiariaReservadaServiceTest {

	@Inject
	GerenciarDiariaService gerenciarDiariaService;
	
	@Inject
    Logger log;
	
	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(GerenciarDiariaService.class, DiariaReservadaDAO.class, org.apache.commons.lang3.StringUtils.class, Mensagens.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(new File("src/main/webapp", "WEB-INF/faces-config.xml"))
                .addAsResource(new File("src/main/resources/ValidationMessages.properties"), "ValidationMessages.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

   @Test
   public void salva_DiariaReservada() {
	   DiariaReservada diariaReservada = criaDiariaReservada();
	   assertTrue(gerenciarDiariaService.salvaDiariaReservada(diariaReservada));
	   assertNotNull(diariaReservada.getId());
	   log.info(diariaReservada.getQuarto() + " foi persistido com o id " + diariaReservada.getId());
	   
	   DiariaReservada diariaReservada2 = criaDiariaReservada();
	   diariaReservada2.setQtdDias(-2);
	   assertNull(diariaReservada2.getId());
	   assertFalse(gerenciarDiariaService.salvaDiariaReservada(diariaReservada));
	   log.info("não permite salvar diaria reservada com qtde de dias negativa.");
   }
	
	private DiariaReservada criaDiariaReservada() {
		DiariaReservada diaria = new DiariaReservada();
		Quarto quarto = criaQuarto();
		diaria.setData(new Date(19/12/2018));
		diaria.setReserva(criaReserva(criaPessoa()));
		diaria.setQtdDias(4);
		diaria.setQuarto(quarto);	
		return diaria;
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
