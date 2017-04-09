package br.com.gorilahack.projectnavi.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

@Repository
public class WatsonRepository {

	public WatsonRepository() {
	}

	private MessageResponse getResponse(String speakText, String userName, String passWord, String workspaceid) {
		ConversationService conversationService = new ConversationService("2017-02-03");
		conversationService.setUsernameAndPassword(userName, passWord);

		MessageRequest newMessage = null;
		newMessage = new MessageRequest.Builder().inputText(speakText).build();
			
		return conversationService.message(workspaceid, newMessage).execute();
	}

	
	
	public List<String> getAnswer(String speakText) {
		// Strings default para logar no ChatPrincipal
		String userNameDefault = "48232335-6bb8-457d-b47e-ccd4c3d0a326";
		String passwordDefault = "Sx25MHY2iqM1";
		String workspaceidDefault = "8d824d01-0efe-4c28-b642-3edcf017469a";

		
		// Lista de retorno do ChatPrincipal
		List<String> listReturn = new ArrayList<String>();

		MessageResponse response = getResponse(speakText, userNameDefault, passwordDefault, workspaceidDefault);

		System.out.println(response);

		try {
			JsonNode arrNode = new ObjectMapper().readTree(response.toString()).get("output").get("text");
			if (arrNode.isArray()) {
				for (final JsonNode objNode : arrNode) {
					listReturn.add(objNode.toString());
				}
			}

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return listReturn;
	}
}
