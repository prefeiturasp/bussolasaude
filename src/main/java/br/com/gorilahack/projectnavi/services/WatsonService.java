package br.com.gorilahack.projectnavi.services;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import br.com.gorilahack.projectnavi.repository.CeInfoRepository;
import br.com.gorilahack.projectnavi.repository.WatsonRepository;
import br.com.gorilahack.projectnavi.tos.BodyBuscaSaudeEndPointTO;
import br.com.gorilahack.projectnavi.tos.BuscaSaudeDTO;
import br.com.gorilahack.projectnavi.tos.EnderecoEstabelecimentoTO;
import br.com.gorilahack.projectnavi.tos.ResponseTO;
import br.com.gorilahack.projectnavi.tos.WatsonSusAnswerTO;
import br.com.gorilahack.projectnavi.tos.WatsonSusProductTO;

@Service
public class WatsonService {
	public WatsonService() {
	}

	public ResponseTO getWatsonAnswer(String speakText, String enderecoPaciente) {
		ResponseTO responseTO = null;

		// Pega lista de resposta do watson
		List<String> listWatsonAnswer = new WatsonRepository().getAnswer(speakText);

		// Watson respondeu ?
		if (listWatsonAnswer.size() > 0) {
			// Pega resposta da linha do watson
			String watsonAnswer = listWatsonAnswer.get(0);
			System.out.println(watsonAnswer);

			WatsonSusAnswerTO watsonSusAnswerTO = tryToGetJsonSusAnswer(watsonAnswer);

			if (watsonSusAnswerTO != null && !watsonSusAnswerTO.getCodigo_esp().equals("")) {
				System.out.println("emergencia: " + watsonSusAnswerTO.getEmergencia() + "\n" + "agendados: "
						+ watsonSusAnswerTO.getAgendado() + "\n" + "codigo_esp: " + watsonSusAnswerTO.getCodigo_esp());

				List<EnderecoEstabelecimentoTO> enderecos = new CeInfoRepository()
						.getEstabelecimentos(watsonSusAnswerTO.getCodigo_esp());

				responseTO = new ResponseTO();
				responseTO.setAgendado(watsonSusAnswerTO.getAgendado());
				responseTO.setEmergencia(watsonSusAnswerTO.getEmergencia());
				responseTO.setOutput("");

				if (responseTO.getEmergencia().equals("N") && responseTO.getAgendado().equals("S")) {
					if (enderecoPaciente.equals("")) {
						responseTO.setOutput("Para serviços de agendamento é necessário informar o seu Endereço.");
					} else {
						BuscaSaudeDTO toD = getEnderecoFromBuscaSaudeEndPoint(enderecoPaciente);

						EnderecoEstabelecimentoTO to = new EnderecoEstabelecimentoTO();

						String end = toD.getD().get(0).getLogradouro() + ", " + toD.getD().get(0).getNumero() + ", "
								+ toD.getD().get(0).getBairro();

						to.setEndereco(end);
						to.setLatitude(toD.getD().get(0).getGeo().getLatitude());
						to.setLongitude(toD.getD().get(0).getGeo().getLongitude());
						responseTO.getEnderecos().add(to);
					}
				} else {
					for (int i = 0; i < enderecos.size(); i++) {
						EnderecoEstabelecimentoTO to = new EnderecoEstabelecimentoTO();
						to.setEndereco(enderecos.get(i).getEndereco());
						to.setLatitude(enderecos.get(i).getLatitude());
						to.setLongitude(enderecos.get(i).getLongitude());
						responseTO.getEnderecos().add(to);
					}
				}
			} else {
				WatsonSusProductTO watsonSusProductTO = tryToGetJsonProdut(watsonAnswer);
				if (watsonSusProductTO != null) {
					List<EnderecoEstabelecimentoTO> enderecos = new CeInfoRepository()
							.getEstabelecimentosComEstoque(watsonSusProductTO.getProd());

					responseTO = new ResponseTO();
					responseTO.setAgendado("N");
					responseTO.setEmergencia("N");
					responseTO.setOutput("");
					for (int i = 0; i < enderecos.size(); i++) {
						EnderecoEstabelecimentoTO to = new EnderecoEstabelecimentoTO();
						to.setEndereco(enderecos.get(i).getEndereco());
						to.setLatitude(enderecos.get(i).getLatitude());
						to.setLongitude(enderecos.get(i).getLongitude());
						responseTO.getEnderecos().add(to);
					}
				}
			}
			
		};

		if (responseTO == null) {
			responseTO = new ResponseTO();
			responseTO.setOutput("Não entendi, digite novamente por favor?");
		}

		return responseTO;
	}

	private BuscaSaudeDTO getEnderecoFromBuscaSaudeEndPoint(String enderecoPaciente) {
		EnderecoEstabelecimentoTO to = new EnderecoEstabelecimentoTO();

		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyC0_BXBFtq7vwQoJEgGpgAgZfhQjq1s39g");
		GeocodingResult[] results;

		try {
			results = GeocodingApi.geocode(context, enderecoPaciente).await();
			double lat = results[0].geometry.location.lat;
			double lng = results[0].geometry.location.lng;

			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

			URI uri = URI.create("http://buscasaude.prefeitura.sp.gov.br/Default.aspx/BuscarTodasUBSs");

			BodyBuscaSaudeEndPointTO buscaSaudeEndPoint = new BodyBuscaSaudeEndPointTO();
			buscaSaudeEndPoint.setTipoBusca("1");
			buscaSaudeEndPoint.setLatitude(Double.toString(lat));
			buscaSaudeEndPoint.setLongitude(Double.toString(lng));
			buscaSaudeEndPoint.setSubTipos(null);
			buscaSaudeEndPoint.setTextoBusca("enderecoPaciente");

			String returns = restTemplate.postForObject(uri, buscaSaudeEndPoint, String.class);

			System.out.println(returns);

			Gson gson = new Gson();
			BuscaSaudeDTO buscaSaudeDTO = gson.fromJson(returns, BuscaSaudeDTO.class);

			return buscaSaudeDTO;
		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private WatsonSusAnswerTO tryToGetJsonSusAnswer(String answerToJsonTest) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			WatsonSusAnswerTO watsonSusAnswerTO = mapper.readValue(answerToJsonTest, WatsonSusAnswerTO.class);
			return watsonSusAnswerTO;
		} catch (IOException e) {
			return null;
		}
	}
	
	private WatsonSusProductTO tryToGetJsonProdut(String answerToJsonTest) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			WatsonSusProductTO watsonSusProductTO = mapper.readValue(answerToJsonTest, WatsonSusProductTO.class);
			return watsonSusProductTO;
		} catch (IOException e) {
			return null;
		}
	}

}
