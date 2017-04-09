package br.com.gorilahack.projectnavi.tos;

import java.util.ArrayList;
import java.util.List;

public class ResponseTO {
	private String emergencia;
	private String agendado;
	private String output;
	private List<EnderecoEstabelecimentoTO> enderecos = new ArrayList<EnderecoEstabelecimentoTO>();
	
	public ResponseTO() {
	}
	
	public String getEmergencia() {
		return emergencia;
	}
	
	public void setEmergencia(String emergencia) {
		this.emergencia = emergencia;
	}
	
	
	public String getAgendado() {
		return agendado;
	}

	public void setAgendado(String agendado) {
		this.agendado = agendado;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public List<EnderecoEstabelecimentoTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoEstabelecimentoTO> enderecos) {
		this.enderecos = enderecos;
	}

}
