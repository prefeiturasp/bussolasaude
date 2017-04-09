package br.com.gorilahack.projectnavi.tos;

public class MessageTO {
	private String speakText;
	private String enderecoPaciente;
	
	public MessageTO(){
	}

	public String getSpeakText() {
		return speakText;
	}
	
	public void setSpeakText(String speakText) {
		this.speakText = speakText;
	}

	public String getEnderecoPaciente() {
		return enderecoPaciente;
	}

	public void setEnderecoPaciente(String enderecoPaciente) {
		this.enderecoPaciente = enderecoPaciente;
	}
}
