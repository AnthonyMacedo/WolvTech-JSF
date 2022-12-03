package com.wolvtech.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;
import com.wolvtech.model.entity.Endereco;
import com.wolvtech.model.entity.Funcionario;
import com.wolvtech.model.repository.IFuncionarioRepository;
import com.wolvtech.util.annotations.TransactionJpa;

@TransactionJpa
@ViewScoped
@Named(value = "funcionarioBean")
public class FuncionarioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Funcionario funcionario;

	private List<Funcionario> listaFuncionarios;

	@Inject
	transient private IFuncionarioRepository funcionarioDao;

	public FuncionarioBean() {
	}

	public String salvar() {

		try {
			
			funcionarioDao.gravar(funcionario);
			funcionario = new Funcionario();
			this.listaFuncionarios = null;
			msg("Funcionário cadastrado.");
			return "/pages/funncionario.xhtml?faces-redirect=true";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public String limpar() {
		funcionario = new Funcionario();
		return "/pages/funncionario.xhtml?faces-redirect=true";
	}

	public void remove() {
		funcionarioDao.remover(Funcionario.class, funcionario.getId());
	}

	public String preparaAlteracao() {
		this.funcionario = funcionarioDao. buscarPorId(Funcionario.class, funcionario.getId());
		return "/pages/funncionario.xhtml?faces-redirect=true";
	}

	public List<Funcionario> getListaFuncionarios() {
		if (this.listaFuncionarios == null) {
			this.listaFuncionarios = funcionarioDao.buscarTodos(Funcionario.class);
		}
		return listaFuncionarios;
	}

	public void pesquisaCep(AjaxBehaviorEvent event) {

		try {
			System.out.println(funcionario.getEndereco().getCep());
			URL url = new URL("https://viacep.com.br/ws/" + funcionario.getEndereco().getCep() + "/json/");
			System.out.println(url);
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			String cep = "";
			StringBuilder jsonCep = new StringBuilder();

			while ((cep = br.readLine()) != null) {
				jsonCep.append(cep);
			}

			Endereco gsonAux = new Gson().fromJson(jsonCep.toString(), Endereco.class);

			funcionario.getEndereco().setCep(gsonAux.getCep());
			funcionario.getEndereco().setLogradouro(gsonAux.getLogradouro());
			funcionario.getEndereco().setComplemento(gsonAux.getComplemento());
			funcionario.getEndereco().setBairro(gsonAux.getBairro());
			funcionario.getEndereco().setMunicipio(gsonAux.getLocalidade());
			funcionario.getEndereco().setUf(gsonAux.getUf());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void msg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public IFuncionarioRepository getfuncionarioDao() {
		return funcionarioDao;
	}

	public void setfuncionarioDao(IFuncionarioRepository funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
	}

}
