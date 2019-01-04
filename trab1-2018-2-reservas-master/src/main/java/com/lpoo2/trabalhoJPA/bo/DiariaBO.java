package com.lpoo2.trabalhoJPA.bo;

import java.util.Date;

import com.lpoo2.trabalhoJPA.dao.DiariaDAO;
import com.lpoo2.trabalhoJPA.dao.PessoaDAO;
import com.lpoo2.trabalhoJPA.pojo.Diaria;
import com.lpoo2.trabalhoJPA.pojo.DiariaAvulsa;
import com.lpoo2.trabalhoJPA.pojo.DiariaReservada;
import com.lpoo2.trabalhoJPA.pojo.Pessoa;
import com.lpoo2.trabalhoJPA.pojo.PessoaFisica;
import com.lpoo2.trabalhoJPA.pojo.Reserva;

public class DiariaBO {
	private DiariaDAO diariaDAO;

	public DiariaBO() {
		diariaDAO = new DiariaDAO();
	}
	
	public Reserva fazReserva(Pessoa p, Date data, Double valor) {
		Reserva r = new Reserva(data, valor);
		return r;
		
	}
	
	public DiariaReservada registraDiariaReservada(Reserva reserva) {
		DiariaReservada dr = new DiariaReservada(reserva.getData());
		diariaDAO.salva(dr);
		return dr;
	}
	
	public Diaria pagaDiaria(Pessoa pessoa) {
		DiariaAvulsa da = new DiariaAvulsa(new Date());
		diariaDAO.salva(da);
		return da;
	}
	
	public void hospedaPessoaFisica(Diaria diaria, PessoaFisica p) {
		diaria.addHospedes(p);
		diariaDAO.atualiza(diaria);
	}
	
}
