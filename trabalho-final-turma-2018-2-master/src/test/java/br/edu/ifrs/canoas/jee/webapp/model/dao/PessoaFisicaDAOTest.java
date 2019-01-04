package br.edu.ifrs.canoas.jee.webapp.model.dao;

import static org.junit.Assert.*;
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
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;

@RunWith(Arquillian.class)
public class PessoaFisicaDAOTest {
	@Inject
	PessoaFisicaDAO pessoaFisicaDAO;
	@Inject
    Logger log;

	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PessoaFisicaDAO.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addPackages(true, "org.apache.commons")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	@Test
	public void testa_a_persistencia_pessoa_fisica_em_branco() {
		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setNome("Pessoa Fisica");
		pessoaFisica.setEmail("email@email.com");
		pessoaFisica.setTelefone("(51)91447-1941");
		pessoaFisica.setRg("0132456798");
		pessoaFisica.setCpf("000.000.000-58");
		pessoaFisicaDAO.insere(pessoaFisica);
		assertNotNull(pessoaFisica.getId());
		log.info(pessoaFisica.getNome() + " foi persistido com o id " + pessoaFisica.getId());
	}
}
