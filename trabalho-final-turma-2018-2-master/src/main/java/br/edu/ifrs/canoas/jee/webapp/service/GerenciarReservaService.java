package br.edu.ifrs.canoas.jee.webapp.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaFisicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaJuridicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.QuartoDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.ReservaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Reserva;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@Stateless
public class GerenciarReservaService {
	@Inject
	private ReservaDAO reservaDAO;
	@Inject
	private PessoaFisicaDAO pessoaFisicaDAO;
	@Inject
	private PessoaJuridicaDAO pessoaJuridicaDAO;
	@Inject
	private QuartoDAO quartoDAO;
	
	public void salvaReserva(Reserva reserva) {
	if(!validaData(reserva.getData()) ) {
		Mensagens.define(FacesMessage.SEVERITY_ERROR, "Reserva.data.erro");
	}else if(!validaValor(reserva.getValor()) ){
		Mensagens.define(FacesMessage.SEVERITY_ERROR, "Reserva.valor.erro");
	}else {
			if (reserva.getId() == null)
				reservaDAO.insere(reserva);
			else 
				reservaDAO.atualiza(reserva);
		}
	} 
	
	@SuppressWarnings("unchecked")
	public List<Reserva> busca(String criterio) {
		if(criterio !=null && criterio.length() > 0)
			return reservaDAO.buscaPorCriterio(criterio);
		else
			return reservaDAO.lista();
	}
	
	public void exclui(Reserva reserva) {
		reservaDAO.exclui(reserva.getId());
	}
	
	public List<String> getTipoCliente() {
		List<String> list = new ArrayList<String>();
		list.add("Pessoa Física");
		list.add("Pessoa Jurídica");

		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<PessoaFisica> pegaCpfPf(){ 
		return pessoaFisicaDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	public List<PessoaJuridica> pegaCnpjPj(){ 
		return pessoaJuridicaDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	public List<Quarto> pegaQuartos(){
		return quartoDAO.lista();
	}
	
	public boolean validaData(Date data) {
		Date dataAtual = new Date();
		if( data.after(dataAtual) || (data.getTime() == dataAtual.getTime()) )
			return true;
		else
			return false;
	}
	
	public boolean validaValor(Double valor) {
		if(valor < 0)
			return false;
		else
			return true;
	}
}
