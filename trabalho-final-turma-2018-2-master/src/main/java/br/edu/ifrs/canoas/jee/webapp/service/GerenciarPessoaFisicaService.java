package br.edu.ifrs.canoas.jee.webapp.service;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import br.edu.ifrs.canoas.jee.webapp.model.dao.DiariaAvulsaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaFisicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.ReservaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@Stateless
public class GerenciarPessoaFisicaService {

	@Inject
	private PessoaFisicaDAO pessoaFisicaDAO;

	@Inject
	private DiariaAvulsaDAO diariaAvulsaDAO;

	@Inject
	private ReservaDAO reservaDAO;


	public boolean salvaPessoaFisica(PessoaFisica pessoaFisica) {


		if(pessoaFisicaDAO.g(pessoaFisica.getCpf()).size() > 0 && pessoaFisica.getId() == null ) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "Pessoa.cpf.erro", pessoaFisica.getCpf());
			return false;
		}

		if (validaDataDeNascimento(pessoaFisica) == false) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "Cliente.idade.erro");
			return false;
		}

		if (pessoaFisica.getId() == null) {
			pessoaFisicaDAO.insere(pessoaFisica);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Pessoa.cadastro.sucesso", pessoaFisica.getNome());
			return true;
		} else {
			pessoaFisicaDAO.atualiza(pessoaFisica);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Pessoa.atualizado.sucesso", pessoaFisica.getNome());
			return true;
		}

	}

	public List<PessoaFisica> busca(String criterio) {
		if (criterio != null && criterio.length() > 0) {
			return pessoaFisicaDAO.buscaPorCriterio(criterio);
		} else {
			return pessoaFisicaDAO.lista();
		}
	}

	public boolean exclui(PessoaFisica pessoaFisica) {

		if(reservaDAO.buscaPessoaPorID(pessoaFisica.getId()).size() > 0 || diariaAvulsaDAO.buscaPessoaPorID(pessoaFisica.getId()).size() > 0) {
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Pessoa.excluir.reserva.erro", pessoaFisica.getNome());
			return false;
		}

		if (pessoaFisica.getId() != null) {
			pessoaFisicaDAO.exclui(pessoaFisica.getId());
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Pessoa.exclui.sucesso", pessoaFisica.getNome());
		} else {
			pessoaFisicaDAO.insere(pessoaFisica);
		}

		return true;
	}

	public boolean validaDataDeNascimento(PessoaFisica pessoaFisica) {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(pessoaFisica.getDataNascimento());
		Calendar hoje = Calendar.getInstance();

		int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

		if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
			idade--;
		} else {
			if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH)
					&& hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
				idade--;
			}
		}

		return (idade >= 18 ? true : false);
	}

}
