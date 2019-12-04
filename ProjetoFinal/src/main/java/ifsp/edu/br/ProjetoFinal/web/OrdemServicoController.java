package ifsp.edu.br.ProjetoFinal.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ifsp.edu.br.ProjetoFinal.data.OrdemServicoRepositorio;
import ifsp.edu.br.ProjetoFinal.modelo.Horario;
import ifsp.edu.br.ProjetoFinal.modelo.OrdemServico;
import ifsp.edu.br.ProjetoFinal.modelo.Plano;


@Controller
@RequestMapping("/solicitacao")
@SessionAttributes("solicitacao")
public class OrdemServicoController {
	@Autowired
	private OrdemServicoRepositorio ordemServicoRepositorio;

	@GetMapping("/atual")
	public String ordemServico(Model model) {
		OrdemServico mOrdemServico = new OrdemServico();
		mOrdemServico.setPlano(Plano.MONOFASICO);
		mOrdemServico.setHorario(Horario.MANHA);
		mOrdemServico.setDataContratacao(new Date());
		model.addAttribute("ordemServico", mOrdemServico); 
		return "solicitacao";
	}
	
	@ModelAttribute
	public void popularListaOSAtradas(Model model) {
		List<OrdemServico> ordensServico = new ArrayList<>(); 				
		ordemServicoRepositorio.findOsAtrasada().forEach(ordensServico::add);
	
		model.addAttribute("ordensServicoAtrasadas", ordensServico);
	}
	
	@ModelAttribute
	public void popularListaOSFechadaPrazo(Model model) { // Pensar num forma de colocar isso por instalador :(
		List<OrdemServico> ordensServico = new ArrayList<>(); 				
		ordemServicoRepositorio.findOsFechadaEmDia().forEach(ordensServico::add);
	
		model.addAttribute("ordensServicoFechadasNoPrazo", ordensServico);
	}

	@PostMapping
	public String processar(@Valid OrdemServico ordemServico, Errors errors, SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			return "home";
		}

		System.out.println("-----Ordem de servi�o----");
		System.out.println(ordemServico.getPlano());
		System.out.println(ordemServico.getEndereco().getLogradouro());
		System.out.println(ordemServico.getEndereco().getNumero());
		System.out.println(ordemServico.getEndereco().getBairro());
		System.out.println(ordemServico.getEndereco().getCep());

		ordemServicoRepositorio.save(ordemServico);
		sessionStatus.setComplete();

		return "redirect:/";
	}
}