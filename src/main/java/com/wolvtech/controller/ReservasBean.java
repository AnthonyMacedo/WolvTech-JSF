package com.wolvtech.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wolvtech.model.entity.Apartamento;
import com.wolvtech.model.entity.Cliente;
import com.wolvtech.model.entity.Reservas;
import com.wolvtech.model.repository.IReservasRepository;
import com.wolvtech.util.annotations.TransactionJpa;

import org.primefaces.event.SelectEvent;

@TransactionJpa
@ViewScoped
@Named(value = "reservasBean")
public class ReservasBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Reservas reserva;

	private Cliente clienteSelecionado;

	private Apartamento apartamentoSelecionado;

	private List<Reservas> listaReservas;

	@Inject
	transient private IReservasRepository reservasDao;

	public ReservasBean() {
	}

	public String reservarQuarto() {
		
		if (validaData(reserva.getDataInicial(), reserva.getDataFinal())) {

			reservasDao.gravar(reserva);
			reserva = new Reservas();
			new Cliente();
			msg("Apartamento reservado.");

			return "/pages/reservas.xhtml?faces-redirect=true";
		} else {
			return "";
		}

	}

	public String limpar() {
		reserva = new Reservas();
		return "/pages/reservas.xhtml?faces-redirect=true";
	}

	public List<Reservas> getListaReservas() {
		if (listaReservas == null) {
			listaReservas = reservasDao.buscarTodos(Reservas.class);
		}
		return listaReservas;
	}

	public boolean validaData(Date dataInicial, Date dataFinal) {

		Calendar calendar = Calendar.getInstance();

		Date dataAtual = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		String dtInicial = sdf.format(dataInicial);
		String dtFinal = sdf.format(dataFinal);
		String dtAtual = sdf.format(dataAtual);

		if (dtInicial.contentEquals(dtAtual) && dataFinal.after(calendar.getTime())
				|| dataInicial.after(calendar.getTime()) && dataFinal.after(calendar.getTime())
				|| dtInicial.contentEquals(dtAtual) && !dataFinal.before(calendar.getTime())) {
			return true;
		} else {

			/*
			 * "compareTo" se retornar 0 as datas sao iguais, se a data sendo comparada for anterior a
			 * data passada como argumento retorna valor negativo, se for posterior retorna
			 * positivo.
			 */
			
			int aux = dtInicial.compareTo(dtAtual);
			int aux2 = dtFinal.compareTo(dtAtual);
			
			if( aux < 0 ) {
				msg("Data inicial não pode ser anterior a data atual.");
			}
			if (aux2 < 0) {
				msg("Data final não pode ser anterior a data atual.");
			}
			if (aux2 == 0) {
				msg("Data final deve ser superior a data atual.");
			}
			
			return false;
		}
	}

	public Reservas getReserva() {
		return reserva;
	}

	public void setReserva(Reservas reserva) {
		this.reserva = reserva;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Apartamento getApartamentoSelecionado() {
		return apartamentoSelecionado;
	}

	public void setApartamentoSelecionado(Apartamento apartamentoSelecionado) {
		this.apartamentoSelecionado = apartamentoSelecionado;
	}

	public void setListaReservas(List<Reservas> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public void onRowSelectCliente(SelectEvent<Cliente> event) {
		clienteSelecionado = event.getObject();
		reserva.setCliente(clienteSelecionado);
	}

	public void onRowSelectApartamento(SelectEvent<Apartamento> event) {
		apartamentoSelecionado = event.getObject();
		reserva.setApartamento(apartamentoSelecionado);
	}

	public void msg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
	}

	public IReservasRepository getreservasDao() {
		return reservasDao;
	}

	public void setreservasDao(IReservasRepository reservasDao) {
		this.reservasDao = reservasDao;
	}

}
