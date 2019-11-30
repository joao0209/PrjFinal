package ifsp.edu.br.ProjetoFinal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping(path = "/notHasInstaller", produces = "application/json")
	public Iterable<OrdemServico> getAllByNotHasInstaller() {
		return repo.findAllByNotHasInstalador(); // todos que nao possuem instalador
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public Iterable<OrdemServico> getAllByHasInstaller(@PathVariable("id") long id) {
		return repo.findAllByInstaladorId(id); // todos do instalador que possui o id
	}
	
	@PutMapping("/{id}")
	public OrdemServico putOrdemServico(@PathVariable("id") long id, @RequestBody OrdemServico os) {
		os.setId(id);
		return repo.save(os);
	}
}
