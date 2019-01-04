package br.edu.ifrs.canoas.jee.webapp.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.jee.webapp.model.dao.DiariaAvulsaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.DiariaReservadaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaFisicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaJuridicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaAvulsa;
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaReservada;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@Stateless
public class GerenciarDiariaService {
	@Inject
	private DiariaAvulsaDAO diariaAvulsaDAO;

	@Inject
	private DiariaReservadaDAO diariaReservadaDAO;

	@Inject
	private PessoaFisicaDAO pessoaFisicaDAO;

	@Inject
	private PessoaJuridicaDAO pessoaJuridicaDAO;

	public Boolean salvaDiaria(DiariaAvulsa diariaAvulsa) {
		if(diariaAvulsa.getQtdDias() <= 0) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "diaria.qtdDias.invalida");
			return false;
		}
		
		if (diariaAvulsa.getId() == null) {
			diariaAvulsaDAO.insere(diariaAvulsa);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "diaria.cadastra.sucesso");
			return true;
		}else {
			diariaAvulsaDAO.atualiza(diariaAvulsa);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "diaria.atualizada.sucesso");
			return true;
		}
	}

	public Boolean salvaDiariaReservada(DiariaReservada diariaReservada) {
		if(diariaReservada.getQtdDias() <= 0) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "diaria.qtdDias.invalida");
			return false;
		}else {
			if (diariaReservada.getId() == null) {
				diariaReservadaDAO.insere(diariaReservada);
				return true;
			}else {
				diariaReservadaDAO.atualiza(diariaReservada);
				return true;
			}	
		}
	}

	public List<DiariaAvulsa> busca(String criterio) {
		if (criterio != null && criterio.length() > 0) {
			return diariaAvulsaDAO.buscaPorCriterio(criterio);
		}else {
			return diariaAvulsaDAO.buscaDiariasAvulsas();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DiariaReservada> buscaDiariaReservada(String criterio) {
		if (criterio != null && criterio.length() > 0)
			return diariaReservadaDAO.buscaPorCriterio(criterio);
		else
			return diariaReservadaDAO.lista();
	}

	public void exclui(DiariaAvulsa diariaAvulsa) {
		diariaAvulsaDAO.exclui(diariaAvulsa.getId());
		Mensagens.define(FacesMessage.SEVERITY_INFO, "diaria.exclui.sucesso");
	}

	public void excluiDiariaReservada(DiariaReservada diariareservada) {
		diariaReservadaDAO.exclui(diariareservada.getId());
	}

	public List<String> getTipoCliente() {
		return Arrays.asList("Pessoa Física", "Pessoa Jurídica");
	}

	@SuppressWarnings("unchecked")
	public List<PessoaFisica> getPF(){
		return pessoaFisicaDAO.lista();
	}

	@SuppressWarnings("unchecked")
	public List<PessoaJuridica> getPJ(){
		return pessoaJuridicaDAO.lista();
	}

	public List<Quarto> getQuartos(Long id){
		return diariaAvulsaDAO.buscaQuartos(id);
	}
	
}
