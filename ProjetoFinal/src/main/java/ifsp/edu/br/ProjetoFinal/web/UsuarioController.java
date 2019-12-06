package ifsp.edu.br.ProjetoFinal.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import ifsp.edu.br.ProjetoFinal.data.UsuarioRepositorio;
import ifsp.edu.br.ProjetoFinal.modelo.Papel;
import ifsp.edu.br.ProjetoFinal.modelo.Usuario;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@GetMapping
	public String usuario(Model model) {
		Usuario mUsuario = new Usuario();
		mUsuario.setHabilitado(true);
		mUsuario.setTipoHolder("CLIENTE");
		model.addAttribute("usuario", mUsuario); 
 		
		return "usuario-form";
	}

	@PostMapping 
	public String processar(@Valid Usuario usuario, Errors errors, SessionStatus sessionStatus) {
		Papel papel;

		if(usuario.getTipoHolder().equals("INSTALADOR")) {
			papel = new Papel("INSTALADOR");
			usuario.addPapel(papel);
		}else {
			papel = new Papel("CLIENTE"); 
			usuario.addPapel(papel);
		} 
		 
		System.out.println("-----Usuario----");
		System.out.println(usuario.getNome());
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getUsername());
		System.out.println(usuario.getPassword());
		System.out.println("Papeis:");
		
		for(Papel p: usuario.getPapeis()) {
			System.out.println(p.getPapel());			
		}
		System.out.println(usuario.getHabilitado()); 

		usuarioRepositorio.save(usuario);
		
		sessionStatus.setComplete();

		return "redirect:/";
	}
}