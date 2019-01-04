package br.edu.ifrs.canoas.jee.webapp.service;

import static org.assertj.core.api.Assertions.*;
import java.io.File;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaFisicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@RunWith(Arquillian.class)
public class GerenciarPessoaFisicaServiceTest {
	
	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(GerenciarPessoaFisicaService.class, PessoaFisicaDAO.class, org.apache.commons.lang3.StringUtils.class, Mensagens.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(new File("src/main/webapp", "WEB-INF/faces-config.xml"))
                .addAsResource(new File("src/main/resources/ValidationMessages.properties"), "ValidationMessages.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                ;
    }
	
	@Test
	public void testSalvaPessoaFisica() {
		PessoaFisica pf = new PessoaFisica();
		pf = criaPessoaFisica();
		/*GerenciarPessoaFisicaService gerenciarPessoaFisicaService = new GerenciarPessoaFisicaService();
		gerenciarPessoaFisicaService.salvaPessoaFisica(pf);
		assertThat(pf).isNotNull();*/
	}
	private PessoaFisica criaPessoaFisica()
	{
		PessoaFisica pf = new PessoaFisica();
	
		pf.setRg("01234567989");
		pf.setNome("Pessoa Fisica");
		pf.setCpf("000.000.000-58");
		pf.setEmail("email@email.com");
		pf.setTelefone("(51)91447-1941");
		pf.setId(null);
		return pf;
	}
	
}
