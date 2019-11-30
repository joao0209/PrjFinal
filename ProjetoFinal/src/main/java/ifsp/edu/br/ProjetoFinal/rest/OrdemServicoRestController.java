package ifsp.edu.br.ProjetoFinal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifsp.edu.br.ProjetoFinal.data.OrdemServicoRepositorio;
import ifsp.edu.br.ProjetoFinal.modelo.OrdemServico;

@RestController
@RequestMapping("/api/os")
public class OrdemServicoRestController {
	@Autowired
	private OrdemServicoRepositorio repo;
	
	@GetMapping(produces = "application/json")
	public Iterable<OrdemServico> getAll() {
		return repo.findAll();
	}
	
	@GetMapping(path = "/notInitialized", produces = "application/json")
	public Iterable<OrdemServico> getAllByNotInitialized() {
		return repo.findAllByInstaladorId(0); // todos não iniciados
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public Iterable<OrdemServico> getAllByInitialized(@PathVariable("id") long id) {
		return repo.findAllByInstaladorId(id); // todos do instalador
	}
}
