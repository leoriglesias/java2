package br.edu.ifrs.canoas.jee.webapp.model.entity;
import static org.assertj.core.api.Assertions.*;
import java.text.ParseException;
import org.junit.Test;

public class PessoaFisicaTeste {
	
	@Test
	public void testaPessoaFisicaEmBranco() {
		PessoaFisica pf = new PessoaFisica();
		assertThat(pf).isNotNull();
	}
	@Test
	public void testaDadosPessoaFisica() throws ParseException {
		PessoaFisica pf = new PessoaFisica();
		pf = criaPessoaFisica();
		assertThat(pf.getNome()).isEqualTo("Cristina Vilas Boas");
		assertThat(pf.getRg()).isEqualTo("01234567989");
		assertThat(pf.getCpf()).isEqualTo("000.000.000-58");
		assertThat(pf.getEmail()).isEqualTo("email@email.com");
		assertThat(pf.getTelefone()).isEqualTo("(51)91447-1941");
		assertThat(pf.getId()).isNull();
	}
	
	
	private PessoaFisica criaPessoaFisica()
	{
		PessoaFisica pf = new PessoaFisica();
	
		pf.setRg("01234567989");
		pf.setNome("Cristina Vilas Boas");
		pf.setCpf("000.000.000-58");
		pf.setEmail("email@email.com");
		pf.setTelefone("(51)91447-1941");
		pf.setId(null);
		return pf;
	}
}
