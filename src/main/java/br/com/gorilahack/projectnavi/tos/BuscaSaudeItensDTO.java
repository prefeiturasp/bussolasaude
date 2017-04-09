package br.com.gorilahack.projectnavi.tos;

public class BuscaSaudeItensDTO {
	private String Cnes;
	private String CodSQCN;
	private String Nome;
	private String Servico;
	private String Logradouro;
	private String Numero;
	private String Cep;
	private String Bairro;
	private String Horario;
	private BuscaSaudeItemDGeoTO Geo;    
	private String Tipo;
	private String Subtipo;
	private String UBSReferencia;
	private String NomeTipo;
	private String NomeSubTipo;
	private String Telefone1;
	private String Telefone2;
	private String ExibirUBSReferencia;
	private String InformacoesConcatenadas;
	
	public BuscaSaudeItensDTO() {
	}

	public String getCnes() {
		return Cnes;
	}

	public void setCnes(String cnes) {
		Cnes = cnes;
	}

	public String getCodSQCN() {
		return CodSQCN;
	}

	public void setCodSQCN(String codSQCN) {
		CodSQCN = codSQCN;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getServico() {
		return Servico;
	}

	public void setServico(String servico) {
		Servico = servico;
	}

	public String getLogradouro() {
		return Logradouro;
	}

	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}

	public String getNumero() {
		return Numero;
	}

	public void setNumero(String numero) {
		Numero = numero;
	}

	public String getCep() {
		return Cep;
	}

	public void setCep(String cep) {
		Cep = cep;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getHorario() {
		return Horario;
	}

	public void setHorario(String horario) {
		Horario = horario;
	}

	public BuscaSaudeItemDGeoTO getGeo() {
		return Geo;
	}

	public void setGeo(BuscaSaudeItemDGeoTO geo) {
		Geo = geo;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getSubtipo() {
		return Subtipo;
	}

	public void setSubtipo(String subtipo) {
		Subtipo = subtipo;
	}

	public String getUBSReferencia() {
		return UBSReferencia;
	}

	public void setUBSReferencia(String uBSReferencia) {
		UBSReferencia = uBSReferencia;
	}

	public String getNomeTipo() {
		return NomeTipo;
	}

	public void setNomeTipo(String nomeTipo) {
		NomeTipo = nomeTipo;
	}

	public String getNomeSubTipo() {
		return NomeSubTipo;
	}

	public void setNomeSubTipo(String nomeSubTipo) {
		NomeSubTipo = nomeSubTipo;
	}

	public String getTelefone1() {
		return Telefone1;
	}

	public void setTelefone1(String telefone1) {
		Telefone1 = telefone1;
	}

	public String getTelefone2() {
		return Telefone2;
	}

	public void setTelefone2(String telefone2) {
		Telefone2 = telefone2;
	}

	public String getExibirUBSReferencia() {
		return ExibirUBSReferencia;
	}

	public void setExibirUBSReferencia(String exibirUBSReferencia) {
		ExibirUBSReferencia = exibirUBSReferencia;
	}

	public String getInformacoesConcatenadas() {
		return InformacoesConcatenadas;
	}

	public void setInformacoesConcatenadas(String informacoesConcatenadas) {
		InformacoesConcatenadas = informacoesConcatenadas;
	}
}
