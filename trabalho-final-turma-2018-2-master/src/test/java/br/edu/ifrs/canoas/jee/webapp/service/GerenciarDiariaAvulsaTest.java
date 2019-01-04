package br.edu.ifrs.canoas.jee.webapp.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
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

import br.edu.ifrs.canoas.jee.webapp.model.dao.DiariaAvulsaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaAvulsa;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@RunWith(Arquillian.class)
public class GerenciarDiariaAvulsaTest {
	
	@Inject
	GerenciarDiariaService gerenciarDiariaService;
	
	@Inject
    Logger log;
	
	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(GerenciarDiariaService.class, DiariaAvulsaDAO.class, org.apache.commons.lang3.StringUtils.class, Mensagens.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(new File("src/main/webapp", "WEB-INF/faces-config.xml"))
                .addAsResource(new File("src/main/resources/ValidationMessages.properties"), "ValidationMessages.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                ;
	}

   @Test
   public void salva_usuario() {
	   DiariaAvulsa diaria = criaDiariaAvulsa();
	   assertTrue(gerenciarDiariaService.salvaDiaria(diaria));
	   assertNotNull(diaria.getId());
	   log.info(diaria.getQuarto() + " foi persistido com o id " + diaria.getId());
	   
	   diaria = criaDiariaAvulsa();
	   assertFalse(gerenciarDiariaService.salvaDiaria(diaria));
	   log.info("não permite criar usuario com mesmo quarto");
   }
	
	private DiariaAvulsa criaDiariaAvulsa() {
		DiariaAvulsa diaria = new DiariaAvulsa();
		PessoaFisica pessoa = new PessoaFisica();
		Quarto quarto = new Quarto();
		diaria.setData(new Date(19/12/2018));
		diaria.setPessoa(pessoa);
		diaria.setQtdDias(4);
		diaria.setQuarto(quarto);	
		return diaria;
	}
}
