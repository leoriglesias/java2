package br.edu.ifrs.canoas.jee.webapp.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.edu.ifrs.canoas.jee.webapp.model.dao.UsuarioDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Usuario;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;


@Stateless
public class GerenciarUsuarioService {

	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private Logger log;

	public boolean salvaUsuario(Usuario usuario) {	// : need work

		log.info("Salvando " + usuario.getNome());

		 if (usuario.getId() == null) {
		 	usuarioDAO.insere(usuario);
		 	Mensagens.define(FacesMessage.SEVERITY_INFO, "Usuario.cadastro.sucesso", usuario.getNome());
		 	// return true;
		 } else {
		 	usuarioDAO.atualiza(usuario);
		 	Mensagens.define(FacesMessage.SEVERITY_INFO, "Usuario.atualizado.sucesso", usuario.getNome());
		 	// return true;
		 }

//		Mensagens.define(FacesMessage.SEVERITY_INFO, "Usuario.cadastro.sucesso", usuario.getEstado());

		return true;
	}
	
	/**
	 * Valida a senha do usuário. Testa o algoritmo de criptografia
	 * @param usuario
	 * @return
	 */
	private boolean validaSenha(Usuario usuario) {
		String senha = this.getSenha(usuario.getSenha());
		if (senha.length() > 8){
			//atualizar senha criptografada
			usuario.setSenha(senha);	
			return true;
		}
		return false;
	}

	/**
	 * retorna a quantidade de e-mails cadastrados no banco iguais ao informado.
	 * @param usuario
	 * @return int
	 */
	private int validaEmail(Usuario usuario) {
		if (usuario == null || StringUtils.isBlank(usuario.getEmail()))
			return -1;

		return usuarioDAO
				.buscaPorEmail(usuario.getEmail().trim().toLowerCase())
				.size();
	}

	public List<Usuario> busca(String criterio) {
		if (criterio != null && criterio.length() > 0) 
			return usuarioDAO.buscaPorCriterio(criterio);
		else
			return usuarioDAO.lista();
	}
	
	private String getSenha(String str) {
		String result = "";
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(str.getBytes(), 0, str.length());
			result = new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Usuario.senha.erro.criptografia", e.getMessage());
		}
		return result;
	}

	public void exclui(Usuario usuario) {
		usuarioDAO.exclui(usuario.getId());
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Usuario.excluido.sucesso",usuario.getNome());
		log.info("Excluido " + usuario.getNome() + " com id " + usuario.getId());
	}

	public Usuario get(Long id) {
		return usuarioDAO.busca(id);
	}

}