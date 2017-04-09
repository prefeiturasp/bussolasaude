package br.com.gorilahack.projectnavi.tos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public class WatsonSusAnswerTO {
	@JsonProperty(value = "emergencia")
	private String emergencia;
	@JsonProperty(value = "agendado")
	private String agendado;
	@JsonProperty(value = "codigo_esp")
	private String codigo_esp;
	
	
	public WatsonSusAnswerTO() {
		setEmergencia("");
		setAgendado("");
		setCodigo_esp("");
	}
	
	
	public WatsonSusAnswerTO(String json) {
		Gson gson = new Gson();
		WatsonSusAnswerTO watsonSusAnswerTO = gson.fromJson(json, WatsonSusAnswerTO.class);
		setEmergencia(watsonSusAnswerTO.getEmergencia());
		setAgendado(watsonSusAnswerTO.getAgendado());
		setCodigo_esp(watsonSusAnswerTO.getCodigo_esp());
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

	public String getCodigo_esp() {
		return codigo_esp;
	}

	public void setCodigo_esp(String codigo_esp) {
		this.codigo_esp = codigo_esp;
	}
}
