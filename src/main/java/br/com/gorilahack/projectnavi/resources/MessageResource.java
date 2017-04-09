package br.com.gorilahack.projectnavi.resources;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gorilahack.projectnavi.services.WatsonService;
import br.com.gorilahack.projectnavi.tos.MessageTO;
import br.com.gorilahack.projectnavi.tos.ResponseTO;

@RestController
public class MessageResource {

	@Autowired
	WatsonService service;

	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public ResponseTO getMessage(@RequestBody MessageTO to, HttpSession httpSession) {	
		return service.getWatsonAnswer(to.getSpeakText(), to.getEnderecoPaciente());
	}


}
