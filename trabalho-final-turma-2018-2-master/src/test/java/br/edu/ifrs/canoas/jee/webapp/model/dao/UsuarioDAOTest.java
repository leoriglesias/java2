package br.edu.ifrs.canoas.jee.webapp.model.dao;

import static org.junit.Assert.assertNotNull;

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
import static org.assertj.core.api.Assertions.*;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Municipio;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Estado;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Pais;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Usuario;

import br.edu.ifrs.canoas.jee.webapp.model.dao.MunicipioDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.EstadoDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PaisDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.UsuarioDAO;

@RunWith(Arquillian.class)
public class UsuarioDAOTest {

	@Inject
	UsuarioDAO usuarioDAO;
	
	@Inject
	MunicipioDAO municipioDAO;
	
	@Inject
	EstadoDAO estadoDAO;
	
	@Inject
	PaisDAO paisDAO;
	
	@Inject
	Usuario usuario;
	
	@Inject
    Logger log;
	
	@Inject
	Pais pais;
	
	@Inject
	Estado estado;
	
	@Inject
	Municipio municipio;

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
	public void testa_a_persistencia_do_pais () {
		pais = new Pais();
		pais.setNome("Brasil");
		paisDAO.insere(pais);
		assertNotNull(pais.getId());
		log.info(pais.getNome() + " foi persistido com o id " + pais.getId());
	}
	
	@Test
	public void testa_a_persistencia_do_estado () {
		estado = new Estado();
		estado.setNome("Rio Grande do Sul");
		estado.setPais(pais);
		estadoDAO.insere(estado);
		assertNotNull(estado.getId());
		log.info(estado.getNome() + " foi persistido com o id " + estado.getId());
	}
	
	@Test
	public void testa_a_persistencia_do_municipio () {
		municipio = new Municipio();
		municipio.setNome("Canoas");
		municipio.setEstado(estado);
		municipioDAO.insere(municipio);
		assertNotNull(municipio.getId());
		log.info(municipio.getNome() + " foi persistido com o id " + municipio.getId());
	}
	
	@Test
	public void testa_a_persistencia_do_usuario () {	
		usuario = new Usuario();
		usuario.setEmail("geraldogustavomateusmoura_@previeweventos.com.br");
		usuario.setSenha("xz6zK6q0f2");
		usuario.setNome("Geraldo");
		usuario.setSobrenome("Gustavo Mateus");
		usuario.setMunicipio(municipio);
		usuario.setLogradouro("Rua 27");
		usuarioDAO.insere(usuario);
		assertNotNull(usuario.getId());
		log.info(usuario.getNome() + " foi persistido com o id " + usuario.getId());
	}
	
	@Test
	public void busca_por_criterio() {
		List<Usuario> u = usuarioDAO.buscaPorCriterio("Lopes");
		assertThat(u).extracting("nome").contains("Giovani");
	}
	
	@Test
	public void busca_por_email() {
		List<Usuario> u = usuarioDAO.buscaPorEmail("stefanylauramelo_@ozsurfing.com.br");
		assertThat(u).extracting("nome").contains("Stefany");
		
	}

}