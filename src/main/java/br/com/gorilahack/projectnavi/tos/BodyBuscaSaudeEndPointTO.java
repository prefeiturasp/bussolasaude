package br.com.gorilahack.projectnavi.tos;

public class BodyBuscaSaudeEndPointTO {
	private String tipoBusca;
	private String latitude;
	private String longitude;
	private String subTipos;
	private String textoBusca;
	
	
	public BodyBuscaSaudeEndPointTO() {
		
	}


	public String getTipoBusca() {
		return tipoBusca;
	}


	public void setTipoBusca(String tipoBusca) {
		this.tipoBusca = tipoBusca;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getSubTipos() {
		return subTipos;
	}


	public void setSubTipos(String subTipos) {
		this.subTipos = subTipos;
	}


	public String getTextoBusca() {
		return textoBusca;
	}


	public void setTextoBusca(String textoBusca) {
		this.textoBusca = textoBusca;
	}
	
	
}
