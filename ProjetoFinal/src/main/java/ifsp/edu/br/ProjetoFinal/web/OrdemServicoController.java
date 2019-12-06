package ifsp.edu.br.ProjetoFinal.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import ifsp.edu.br.ProjetoFinal.data.UsuarioRepositorio;
import ifsp.edu.br.ProjetoFinal.modelo.Horario;
import ifsp.edu.br.ProjetoFinal.modelo.OrdemServico;
import ifsp.edu.br.ProjetoFinal.modelo.Plano;
import ifsp.edu.br.ProjetoFinal.modelo.Usuario;


@Controller
@RequestMapping("/solicitacao")
public class OrdemServicoController {
	@Autowired
	private OrdemServicoRepositorio ordemServicoRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@GetMapping
	public String ordemServico(Model model) {
		OrdemServico mOrdemServico = new OrdemServico();
		
		mOrdemServico.setPlano(Plano.MONOFASICO);
		mOrdemServico.setHorario(Horario.MANHA);
		mOrdemServico.setDataContratacao(new Date());
		
		model.addAttribute("ordemServico", mOrdemServico); 
		model.addAttribute("usuariosList",usuarioRepositorio.findAllByRole("CLIENTE"));
		
		return "solicitacao";
	}
	
	@GetMapping("/painel")
	public String ordemServicoOrdens(Model model) {
		
		return "painel";
	}
	
	@ModelAttribute
	public void popularListaOSAtradas(Model model) {
		List<OrdemServico> ordensServico = ordemServicoRepositorio.findOsAtrasada();
	
		model.addAttribute("ordensServicoAtrasadas", ordensServico);
	}
	
	@ModelAttribute
	public void popularListaOSFechadasEmDia(Model model) {
		List<OrdemServico> ordensServico = ordemServicoRepositorio.findOsFechadaEmDia();
	
		model.addAttribute("ordensServicoFechadasEmDia", ordensServico);
	}
	
	@ModelAttribute
	public void popularListaUsuarioOSFechadaPrazo(Model model) { // Pensar num forma de colocar isso por instalador :(
		List<Usuario> instaladoresAtradados = ordemServicoRepositorio.findInstaladorComOSAtrasada();
		
		model.addAttribute("instaladoresAtradados", instaladoresAtradados);
	}

	@PostMapping
	public String processar(@Valid OrdemServico ordemServico, Errors errors, SessionStatus sessionStatus) {
		
		System.out.println("-----Ordem de servi�o----"); 
		System.out.println(ordemServico.getPlano()); 
		System.out.println(ordemServico.getEndereco().getLogradouro());
		System.out.println(ordemServico.getEndereco().getNumero());
		System.out.println(ordemServico.getEndereco().getBairro());
		System.out.println(ordemServico.getEndereco().getCep());
		ordemServico.setCliente(usuarioRepositorio.findById(ordemServico.getClienteIdHolder()).get());
		ordemServicoRepositorio.save(ordemServico);
		sessionStatus.setComplete();

		return "redirect:/";
	}
	
 
}