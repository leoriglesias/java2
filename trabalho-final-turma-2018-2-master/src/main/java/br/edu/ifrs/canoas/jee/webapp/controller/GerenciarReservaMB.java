package br.edu.ifrs.canoas.jee.webapp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaAvulsa;
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaReservada;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Pessoa;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Reserva;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarDiariaService;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarReservaService;
import lombok.Data;

@Named
@ViewScoped
@Data
public class GerenciarReservaMB implements Serializable{

	private static final long serialVersionUID = 98809443764225833L;
	
	@Inject
    private GerenciarReservaService gerenciarReservaService;
	@Inject
    private GerenciarDiariaService gerenciarDiariaService;
	private DiariaReservada diariaReservada;
	private Reserva reserva;
	private Quarto quarto;
	private Pessoa pessoa;
	private Date dataAtual;
	private String tipoCliente;
	private List<DiariaReservada> diariasReservadas;
	private List<DiariaReservada> diariasReservadasFiltradas;
	private List<String> tipoClientes;
	private List<PessoaFisica> cpfs;
	private List<PessoaJuridica> cnpjs;
	private List<Quarto> quartos;
	
	public void	onChangeTipoCliente() {
		if(tipoCliente.intern() == "Pessoa Física") {
			diariaReservada.getReserva().setPessoa(new PessoaFisica());
		}else if(tipoCliente.intern() == "Pessoa Jurídica") {
			diariaReservada.getReserva().setPessoa(new PessoaJuridica());
		}
    }
	
	public String isPFouPJ(DiariaReservada diariaReservada){
		return diariaReservada.getReserva().getPessoa() instanceof PessoaFisica
		? "Pessoa Física" : "Pessoa Jurídica";
	}
	
	@PostConstruct
    public void init() {
		reserva = new Reserva();
		diariaReservada = new DiariaReservada();
		diariaReservada.setReserva(new Reserva());
		diariaReservada.setQuarto(new Quarto());
		
		diariasReservadas = gerenciarDiariaService.buscaDiariaReservada(null);
		tipoClientes = gerenciarReservaService.getTipoCliente();
		
		cpfs = gerenciarReservaService.pegaCpfPf();
		cnpjs = gerenciarReservaService.pegaCnpjPj();
		quartos = gerenciarReservaService.pegaQuartos();
		
		dataAtual = new Date();
    }
	

	public String salva() {
		diariaReservada.setData(diariaReservada.getReserva().getData());
		gerenciarReservaService.salvaReserva(diariaReservada.getReserva());
		gerenciarDiariaService.salvaDiariaReservada(diariaReservada);
		this.init();
		return limpa();
	}

	public void edita(DiariaReservada dr) {
		this.diariaReservada = dr;
		this.reserva = dr.getReserva();
		this.tipoCliente = isPFouPJ(diariaReservada);
	}

	public void exclui(DiariaReservada dr) {
//		gerenciarReservaService.exclui(dr.getReserva());
		gerenciarDiariaService.excluiDiariaReservada(dr);
		this.init();
	}

	public String limpa() {
		return "/public/reserva.jsf?facesRedirect=true";
	}
}
