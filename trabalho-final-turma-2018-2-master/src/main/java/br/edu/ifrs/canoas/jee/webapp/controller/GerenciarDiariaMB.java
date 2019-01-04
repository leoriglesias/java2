package br.edu.ifrs.canoas.jee.webapp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaAvulsa;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Pessoa;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarDiariaService;
import lombok.Data;

//Toda classe com escopo maior que request deve implementar serializable
@Named
@ViewScoped
@Data
public class GerenciarDiariaMB implements Serializable{

	private static final long serialVersionUID = -2700152367303211581L;
	
	@Inject
    private GerenciarDiariaService gerenciarDiariaService;
	private List<DiariaAvulsa> diariasFiltradas;
	private DiariaAvulsa diariaAvulsa;
	private List<String> tipoClientes;	
	private String tipoCliente;
	private Pessoa pessoa;
	private Quarto quarto;
	
	private List<PessoaJuridica> PJ;
	private List<PessoaFisica> PF;
	
	private List<Quarto> quartos;
	private List<DiariaAvulsa> diarias;
	
	public void	onChangeTipoCliente() {
		if(tipoCliente.intern() == "Pessoa Física") {
			diariaAvulsa.setPessoa(new PessoaFisica());
		}else if(tipoCliente.intern() == "Pessoa Jurídica") {
			diariaAvulsa.setPessoa(new PessoaJuridica());
		}
    }
	
	public String isPFouPJ(DiariaAvulsa diariaAvulsa){
		return diariaAvulsa.getPessoa() instanceof PessoaFisica
		? "Pessoa Física" : "Pessoa Jurídica";
	}
		
	
	@PostConstruct
	public void init() {
		diariaAvulsa = new DiariaAvulsa();
		diariaAvulsa.setQuarto(new Quarto());
		
		diarias = gerenciarDiariaService.busca(null);	
		tipoClientes = gerenciarDiariaService.getTipoCliente();
		
		PF = gerenciarDiariaService.getPF();
		PJ = gerenciarDiariaService.getPJ();
		
		quartos = gerenciarDiariaService.getQuartos(null);
	}
	
	public String salva() {
		if(!gerenciarDiariaService.salvaDiaria(diariaAvulsa)) {
			return null;
		}
		this.init();
		return limpa();
	}
	
	public void edita(DiariaAvulsa diariaAvulsa) {	
		this.diariaAvulsa = diariaAvulsa;
		this.tipoCliente = isPFouPJ(diariaAvulsa); 
		this.quartos = gerenciarDiariaService.getQuartos(diariaAvulsa.getQuarto().getId());
	}
	
	public void exclui(DiariaAvulsa diaria) {
		gerenciarDiariaService.exclui(diaria);
		this.init();
	}
	
	public String limpa() {
		return "/public/diaria.jsf?facesRedirect=true";
	}
}
