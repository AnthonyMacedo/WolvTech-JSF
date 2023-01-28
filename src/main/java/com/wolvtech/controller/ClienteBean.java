package com.wolvtech.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;
import com.wolvtech.model.entity.Cliente;
import com.wolvtech.model.entity.Endereco;
import com.wolvtech.model.repository.IClienteRepository;
import com.wolvtech.utils.annotations.TransactionJpa;
import com.wolvtech.utils.messages.FacesMessages;

import org.primefaces.event.SelectEvent;

@RequestScoped
@Named(value = "clienteBean")
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cliente cliente;

	@Inject
	private FacesMessages msg;

	private Cliente clienteSelecionado;

	private List<Cliente> listaClientes;

	@Inject
	transient private IClienteRepository iClienteDao;

	private String termoPesquisa;

	@TransactionJpa
	public void salvar() {

		try {
				iClienteDao.gravar(cliente);
				cliente = new Cliente();
				this.listaClientes = null;
				msg.info("Cliente salvo com sucesso.");

		} catch (Exception e) {
			e.getMessage();
			System.out.println(e);

			msg.error("Falha no cadastrado.");
		}
	}

	public String limpar() {
		cliente = new Cliente();
		return "/pages/cliente.xhtml?faces-redirect=true";
	}

	@TransactionJpa
	public String remove() {
		try {
			iClienteDao.remover(Cliente.class, cliente.getId());
			msg.warning("Registro removido.");
			return "/pages/lista-clientes.xhtml?faces-redirect=true";
		} catch (Exception e) {
			e.getStackTrace();
			msg.error("Falha ao remover registro.");
			System.out.println("Erro ao remover cliente.");
			return "/pages/lista-clientes.xhtml?faces-redirect=true";
		}

	}

	@TransactionJpa
	public String preparaAlteracao() {
		this.cliente = iClienteDao.buscarPorId(Cliente.class, cliente.getId());
		return "/pages/cliente.xhtml";
	}

	@TransactionJpa
	public List<Cliente> getListaClientes() {
		if (this.listaClientes == null) {
			this.listaClientes = iClienteDao.buscarTodos(Cliente.class);
		}
		return listaClientes;
	}

	public void pesquisar() {
		listaClientes = iClienteDao.pesquisar(termoPesquisa);

		if (listaClientes.isEmpty()) {
			msg.info("Nenhum registro encontrado.");
		}
	}

	/*
	 * @PostConstruct public void carregarPessoas() { listaClientes =
	 * iClienteDao.getAll(Cliente.class); }
	 */

	public void pesquisaCep(AjaxBehaviorEvent event) {

		try {
			URL url = new URL("https://viacep.com.br/ws/" + cliente.getEndereco().getCep() + "/json/");
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

			System.out.println(gsonAux.getCep() + gsonAux.getLogradouro() + gsonAux.getLocalidade());

			cliente.getEndereco().setCep(gsonAux.getCep());
			cliente.getEndereco().setLogradouro(gsonAux.getLogradouro());
			cliente.getEndereco().setComplemento(gsonAux.getComplemento());
			cliente.getEndereco().setBairro(gsonAux.getBairro());
			cliente.getEndereco().setMunicipio(gsonAux.getLocalidade());
			cliente.getEndereco().setUf(gsonAux.getUf());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent<Cliente> event) {
		clienteSelecionado = event.getObject();
		System.out.println(clienteSelecionado.getNome());
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public IClienteRepository getiClienteDao() {
		return iClienteDao;
	}

	public void setiClienteDao(IClienteRepository iClienteDao) {
		this.iClienteDao = iClienteDao;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

}
