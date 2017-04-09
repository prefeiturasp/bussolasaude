package br.com.gorilahack.projectnavi.tos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public class WatsonSusProductTO {
	@JsonProperty(value = "prod")
	private String prod;
	
	
	public WatsonSusProductTO() {
		setProd("");		
	}

	
	public WatsonSusProductTO(String json) {
		Gson gson = new Gson();
		WatsonSusProductTO watsonSusProductTO = gson.fromJson(json, WatsonSusProductTO.class);
		setProd(watsonSusProductTO.getProd());
	}


	public String getProd() {
		return prod;
	}


	public void setProd(String prod) {
		this.prod = prod;
	}
	
}
