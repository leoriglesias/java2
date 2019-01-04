package br.edu.ifrs.canoas.jee.webapp.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarPessoaFisicaService;
import lombok.Data;

@Named
@ManagedBean(name = "dtGerenciaPessoa")
@ViewScoped
@Data
public class GerenciarPessoaFisicaMB implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private GerenciarPessoaFisicaService gerenciarPessoaFisicaService;
	@Inject
	private PessoaFisica pessoaFisica;

	private List<PessoaFisica> pessoasFisicas;

	private List<PessoaFisica> pessoasFisicasFiltradas;

	public String salva() {

		gerenciarPessoaFisicaService.salvaPessoaFisica(pessoaFisica);
		this.init();
		return limpa();
	}

	@PostConstruct
	public void init() {
		pessoasFisicas = gerenciarPessoaFisicaService.busca(null);
	}

	public void exclui() {
		gerenciarPessoaFisicaService.exclui(pessoaFisica);
		this.init();
	}

	public void edita(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public String limpa() {
		pessoaFisica = new PessoaFisica();
		return "/Client/PessoaFisica.jsf?facesRedirect=true";
	}
}
