package br.com.casadocodigo.loja.conf;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Transactional
public class HomeController {
	@RequestMapping("/home")
	public String index() {
		// aqui ainda vamos carregar os produtos.
		System.out.println("Carregando os produtos");
		return "hellow-world";
	}
}