package br.edu.ifrs.canoas.jee.webapp.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.SituacaoQuarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.TipoDeQuarto;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarQuartoService;
import lombok.Data;


@ManagedBean
@RequestScoped
@Data
public class GerenciarQuartoMB {
	
	@Inject
	private GerenciarQuartoService gerenciarQuartoService;
	
	@Inject
	private Quarto quarto;
			
	private List<Quarto> quartos;
	
	private List<Quarto> quartosFiltrados;
	
	//private TipoDeQuarto tipo;
			
	public String salva() {
		if (!gerenciarQuartoService.salvaQuarto(quarto))
			return null;
		this.init();
		return limpa();
	}
	
	
	@PostConstruct
    public void init() {
		quarto = new Quarto();
		quartos = gerenciarQuartoService.busca(null);
    }
	
	public void exclui(Quarto quarto) {
		gerenciarQuartoService.exclui(quarto);
		this.init();
	}
	
	public void edita(Quarto quarto) {
		this.quarto = quarto;
	}

	public String limpa() {
		return "/Administracao/quarto.jsf?facesRedirect=true";
		
	}
	
	public TipoDeQuarto[] getTpQuartos(){
		   return TipoDeQuarto.values();
	}
	
	public SituacaoQuarto[] getSituacoes(){
		   return SituacaoQuarto.values();
	}
	 

}
