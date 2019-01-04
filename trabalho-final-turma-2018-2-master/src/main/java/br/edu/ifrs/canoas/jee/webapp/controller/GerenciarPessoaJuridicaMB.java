package br.edu.ifrs.canoas.jee.webapp.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarPessoaJuridicaService;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;
import lombok.Data;

@Data
@ManagedBean
@ViewScoped
public class GerenciarPessoaJuridicaMB {

	@Inject
	private GerenciarPessoaJuridicaService gerenciarPessoaJuridicaService;
	@Inject
	private PessoaJuridica pessoaJuridica;

	private List<PessoaJuridica> pessoasJuridicas;

	public String salva() {

		try {
			gerenciarPessoaJuridicaService.salvaPessoaJuridica(pessoaJuridica);
		} catch (Exception e) {
			e.printStackTrace();
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "PessoaJuridica.cnpj.unique", pessoaJuridica.getCnpj());
		}

		this.init();
		return limpa();
	}

	@PostConstruct
	public void init() {
		pessoasJuridicas = gerenciarPessoaJuridicaService.busca(null);
	}

	public void exclui() {
		gerenciarPessoaJuridicaService.exclui(pessoaJuridica);
		this.init();
	}

	public void edita(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public String limpa() {
		this.pessoaJuridica = new PessoaJuridica();
		return "/Client/PessoaJuridica.jsf?facesRedirect=true";
	}

}
