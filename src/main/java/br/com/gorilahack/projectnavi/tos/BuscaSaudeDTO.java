package br.com.gorilahack.projectnavi.tos;

import java.util.List;

public class BuscaSaudeDTO {
	private List<BuscaSaudeItensDTO> d;
	
	public BuscaSaudeDTO() {
	}

	public List<BuscaSaudeItensDTO> getD() {
		return d;
	}

	public void setD(List<BuscaSaudeItensDTO> d) {
		this.d = d;
	}
	
}
