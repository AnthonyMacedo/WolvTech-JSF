package com.wolvtech.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wolvtech.model.entity.Apartamento;
import com.wolvtech.model.enums.StatusApartamento;
import com.wolvtech.model.repository.IApartamentoRepository;
import com.wolvtech.utils.annotations.TransactionJpa;
import com.wolvtech.utils.messages.FacesMessages;

import org.primefaces.event.SelectEvent;

import jakarta.persistence.PersistenceException;

@RequestScoped
@Named(value = "apartamentoBean")
public class ApartamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Apartamento apartamento;

	private Apartamento apartamentoSelecionado;

	@Inject
	private FacesMessages msg;

	private List<Apartamento> listaApartamentos;

	@Inject
	transient private IApartamentoRepository apartamentoDao;

	private List<StatusApartamento> enumStatus = Arrays.asList(StatusApartamento.values());

	private String termoPesquisa;

	public String preparaNovoApartamento() {
		apartamento = new Apartamento();
		return "/pages/apartamento.xhtml?faces-redirect=true";
	}


	@TransactionJpa
	public void salvar() {

		try {
			Apartamento apt = validarIdApartamento(apartamento.getNumQuarto());

			if (apartamento.getId() == null && !validarQuartoCadastrado(apartamento.getNumQuarto())) {
				
				apartamentoDao.gravar(apartamento);
				msg.info("Apartamento salvo com sucesso.");
				apartamento = new Apartamento();
			} 
			
			else if(apt != null && apt.getNumQuarto().contentEquals(apartamento.getNumQuarto()) 
					&& apt.getId() == apartamento.getId()) {
				System.out.println("validou apt");
				apartamentoDao.gravar(apartamento);
				msg.info("Apartamento alterado com sucesso.");
				apartamento = new Apartamento();
			}else {
				msg.warning("Apartamento já existe.");
			}

		} catch (jakarta.validation.ConstraintViolationException e) {
			e.getMessage();
			msg.error("Falha no cadastro.");
		} catch (PersistenceException e) {
			e.getMessage();
		} catch (NullPointerException e) {
			e.getMessage();
		}

	}

	@TransactionJpa
	public String remover() {

		try {
			apartamentoDao.remover(Apartamento.class, apartamento.getId());
			return "/pages/lista-apartamentos.xhtml?faces-redirect=true";
		} catch (Exception e) {
			msg.warning("Apartamento removido.");
			return "/pages/lista-apartamentos.xhtml?faces-redirect=true";
		}
	}

	@TransactionJpa
	public String preparaAlteracao() {
		this.apartamento = apartamentoDao.buscarPorId(Apartamento.class, apartamento.getId());
		return "/pages/apartamento";
	}

	public String preparaAlteracao2() {
		apartamentoDao.buscarPorId(Apartamento.class, getApartamentoSelecionado().getId());
		System.out.println("Capturou ID");
		return "/pages/apartamento?faces-redirect=true&id=" + getApartamentoSelecionado().getId();
	}

	@TransactionJpa
	public void pesquisar() {
		listaApartamentos = apartamentoDao.pesquisar(termoPesquisa);

		if (listaApartamentos.isEmpty()) {
			msg.warning("Nenhum registro encontrado.");
		}
	}

	@TransactionJpa
	public Apartamento validarIdApartamento(String numQuarto) {

		try {
			Apartamento apartamento = apartamentoDao.verificaSeTemId(numQuarto);

			if (!apartamento.equals(null)) {
				return apartamento;
			} else {
				return null;
			}
			
		} catch (PersistenceException e) {
			e.getStackTrace();
			System.out.println("Falha ao localizar apartamento");
		}
		return null;
	}

	@TransactionJpa
	public boolean validarQuartoCadastrado(String numQuarto) {

		try {

			boolean aptCadastrado = apartamentoDao.validarQuartoCadastrado(numQuarto);

			if (aptCadastrado == true) {
				return true;
			} else
				return false;
		} catch (PersistenceException e) {
			e.getMessage();
			return false;
		}
	}
	
	public boolean seApartamentoSelecionado() {
		return apartamento != null && apartamento.getId() != null;
	}

	public List<StatusApartamento> getStatusApartamento() {
		return enumStatus;
	}

	public StatusApartamento[] getDescricao() {
		return StatusApartamento.values();
	}

	public List<StatusApartamento> getEnumStatus() {
		return enumStatus;
	}

	public void setEnumStatus(List<StatusApartamento> enumStatus) {
		this.enumStatus = enumStatus;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	public Apartamento getApartamentoSelecionado() {
		return apartamentoSelecionado;
	}

	public void setApartamentoSelecionado(Apartamento apartamentoSelecionado) {
		this.apartamentoSelecionado = apartamentoSelecionado;
	}

	public void onRowSelectApartamento(SelectEvent<Apartamento> event) {
		apartamentoSelecionado = event.getObject();
		setApartamento(apartamentoSelecionado);
		System.out.println(apartamentoSelecionado.getId() + " " + apartamentoSelecionado.getNumQuarto());
	}

	public List<Apartamento> getListaApartamentos() {
		if (this.listaApartamentos == null) {
			this.listaApartamentos = apartamentoDao.buscarTodos(Apartamento.class);
		}
		return listaApartamentos;
	}

	public void setListaApartamentos(List<Apartamento> listaApartamentos) {
		this.listaApartamentos = listaApartamentos;
	}

	public IApartamentoRepository getApartamentoDao() {
		return apartamentoDao;
	}

	public void setApartamentoDao(IApartamentoRepository apartamentoDao) {
		this.apartamentoDao = apartamentoDao;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

}
