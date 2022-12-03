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
import com.wolvtech.util.annotations.TransactionJpa;
import com.wolvtech.util.messages.FacesMessages;

import org.primefaces.event.SelectEvent;

@TransactionJpa
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

	public void preparaNovoApartamento() {
		apartamento = new Apartamento();
	}

	public void salvar() {

		try {

			if (validarApartamento() == false && apartamento.getId() == null && validarQuartoCadastrado() == false) {
				apartamentoDao.gravar(apartamento);
				msg.info("Cadastro salvo com sucesso.");
				apartamento = new Apartamento();
			}

			if (validarApartamento() == true) {
				apartamentoDao.gravar(apartamento);
				msg.info("Cadastro alterado.");
				apartamento = new Apartamento();
			} else {
				msg.info("Apartamento já existe.");
			}

		} catch (jakarta.validation.ConstraintViolationException e) {
			e.printStackTrace();
			msg.error("Falha no cadastro.");
		}
	}

	public String remover() {

		try {
			apartamentoDao.remover(Apartamento.class, apartamento.getId());
			return "/pages/lista-apartamentos.xhtml?faces-redirect=true";
		} catch (Exception e) {
			msg.warning("Apartamento removido.");
			return "/pages/lista-apartamentos.xhtml?faces-redirect=true";
		}

	}

	public String preparaAlteracao() {
		this.apartamento = apartamentoDao.buscarPorId(Apartamento.class, apartamento.getId());
		return "/pages/apartamento";
	}
	public String preparaAlteracao2() {
		apartamentoDao.buscarPorId(Apartamento.class, getApartamentoSelecionado().getId());
		System.out.println("Capturou ID");
		return "/pages/apartamento?faces-redirect=true&id=" + getApartamentoSelecionado().getId();
	}

	public void limpar() {
		apartamento = new Apartamento();
	}

	public void pesquisar() {
		listaApartamentos = apartamentoDao.pesquisar(termoPesquisa);

		if (listaApartamentos.isEmpty()) {
			msg.info("Nenhum registro encontrado.");
		}
	}

	public boolean validarApartamento() {

		if (apartamentoDao.verificaSeTemId(apartamento.getId()) != null) {
			System.out.println("Achou apartamento");
			return true;
		}
		System.out.println("Retornou falso apartamento");
		return false;
	}

	public boolean validarQuartoCadastrado() {
		if (apartamentoDao.validarQuartoCadastrado(apartamento.getNumQuarto()) == true) {
			return true;
		}
		return false;
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
