package br.edu.ifrs.canoas.jee.webapp.model.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.List;
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

import br.edu.ifrs.canoas.jee.webapp.model.dao.UsuarioDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Usuario;
import static org.assertj.core.api.Assertions.*;

@RunWith(Arquillian.class)
public class PessoaJuridicaDAOTest {

	@Inject
	PessoaJuridicaDAO pessoaJuridicaDAO;
	
	@Inject
    Logger log;

	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(UsuarioDAO.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addPackages(true, "org.apache.commons")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	
	@Test
	public void testa_a_persistencia_pessoa_juridica () {	
		PessoaJuridica pessoa = new PessoaJuridica();
		pessoa.setEmail("email@email.com");
		pessoa.setNome("Pess Jur");
		pessoa.setTelefone("51999999991");
		pessoa.setCnpj("87.343.453/4534-66");
		pessoa.setRazaoSocial("Pess Jur");
		pessoa.setInscricaoEstadual("Pess Jur");
		pessoa.setInscricaoMunicipal("Pess Jur");
		pessoaJuridicaDAO.insere(pessoa);
		assertNotNull(pessoa.getId());
		log.info(pessoa.getNome() + " foi persistido com o id " + pessoa.getId());
	
	}
	
//	@Test
	public void busca_por_cnpj() {
		List<PessoaJuridica> p = pessoaJuridicaDAO.buscaPorCnpj("54.852.453/4534-54");
		assertThat(p).extracting("nome").contains("Apple LTDA");
		
	}
	@Test
	public void busca_por_criterio() {
		List<PessoaJuridica> p = pessoaJuridicaDAO.buscaPorCriterio("54.852.453/4534-54");
		assertThat(p).extracting("nome").contains("Apple LTDA");
		
	}

	

}