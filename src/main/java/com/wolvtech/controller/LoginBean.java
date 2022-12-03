package com.wolvtech.controller;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.wolvtech.model.entity.Funcionario;
import com.wolvtech.model.repository.IFuncionarioRepository;
import com.wolvtech.util.annotations.TransactionJpa;

@TransactionJpa
@SessionScoped
@Named(value = "loginBean")
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String usuario;

	private String senha;
	
	private Date data;

	private String nomeFuncionario;

	@Inject
	transient private IFuncionarioRepository funcionarioDao;

	public LoginBean() {
		data = new Date();
	}

	public void autentica() {

		try {
			Funcionario funcionarioUser = funcionarioDao.consultarUsuario(usuario, senha);

			if (funcionarioUser != null && usuario.contentEquals(funcionarioUser.getUsuario())
					&& senha.contentEquals(funcionarioUser.getSenha())) {

				nomeFuncionario = funcionarioUser.getNome();
				
				// adicionar o usuário na sessão usuarioLogado
				FacesContext context = FacesContext.getCurrentInstance();
				ExternalContext externalContext = context.getExternalContext();
				externalContext.getSessionMap().put("usuarioLogado", funcionarioUser.getUsuario());
				
				externalContext.redirect("/reservja/index.xhtml");

			} else {
				usuario = null;
				senha = null;
				msg("Usuário ou senha inválidos.");
			}

		} catch (Exception e) {	
			e.getMessage();
			System.out.println(e);
		}
	}

	public String limpar() {
		usuario = null;
		senha = null;
		return "/login.xhtml?faces-redirect=true";
	}

	public String logout() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("usuarioLogado");

		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();

		httpServletRequest.getSession().invalidate();
		nomeFuncionario = null;
		usuario = null;
		senha = null;		
		return "/login.xhtml?faces-redirect=true";
	}

	public void msg(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "Erro no login."));
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public Date getData() {	
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
