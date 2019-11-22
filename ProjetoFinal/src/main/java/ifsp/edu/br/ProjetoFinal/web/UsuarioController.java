package ifsp.edu.br.ProjetoFinal.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifsp.edu.br.ProjetoFinal.data.UsuarioRepositorio;
import ifsp.edu.br.ProjetoFinal.modelo.Papel;
import ifsp.edu.br.ProjetoFinal.modelo.Usuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepositorio repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public String registro() {
		return "usuario-form";
	}

	@PostMapping
	public String processarRegistro(Usuario usuario) {
		usuario.addPapel(new Papel("ROLE_USER"));
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		repo.save(usuario);
		return "redirect:/login";
	}

	@GetMapping("/autorizar")
	public String getAutorizar() {
		return "autorizar-usuario-form";
	}

	@ModelAttribute
	public void popularListaUsuarios(Model model) {
		List<Usuario> usuarios = new ArrayList<>();
		repo.findDesabilitados().forEach(usuarios::add);

		LstUsuarios lstUsers = new LstUsuarios();
		lstUsers.setUsuarios(usuarios);

		model.addAttribute("lstUsuarios", lstUsers);
	}

	@PostMapping("/autorizando")
	public String autorizarUsuarios(LstUsuarios usuarios) {
		usuarios.getUsuarios().forEach(repo::save);
		return "redirect:/home";
	}
}

