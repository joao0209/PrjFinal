package ifsp.edu.br.ProjetoFinal.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ifsp.edu.br.ProjetoFinal.data.OrdemServicoRepositorio;
import ifsp.edu.br.ProjetoFinal.modelo.OrdemServico;


@Controller
@RequestMapping("/solicitacao")
@SessionAttributes("solicitacao")
public class OrdemServicoController {
	@Autowired
	private OrdemServicoRepositorio ordemServicoRepositorio;

	@GetMapping("/atual")
	public String ordemServico() {
		return "solicitacao";
	}

	@PostMapping
	public String processar(@Valid OrdemServico ordemServico, Errors errors, SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			return "home";
		}

		ordemServicoRepositorio.save(ordemServico);
		sessionStatus.setComplete();

		return "redirect:/";
	}
}