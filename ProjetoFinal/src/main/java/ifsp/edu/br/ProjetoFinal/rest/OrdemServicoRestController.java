package ifsp.edu.br.ProjetoFinal.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ifsp.edu.br.ProjetoFinal.data.OrdemServicoRepositorio;
import ifsp.edu.br.ProjetoFinal.data.UsuarioRepositorio;
import ifsp.edu.br.ProjetoFinal.modelo.OrdemServico;

@RestController
@RequestMapping("/api/os")
public class OrdemServicoRestController {
	@Autowired
	private OrdemServicoRepositorio repo;
	
	@Autowired
	private UsuarioRepositorio userRepo;
	
	@GetMapping(produces = "application/json")
	public Iterable<OrdemServico> getAll() {
		return repo.findAll();
	}
	
	@GetMapping(path = "/notHasInstaller", produces = "application/json")
	public Iterable<OrdemServico> getAllByNotHasInstaller() {
		return repo.findAllByNotHasInstalador(); // todos que nao possuem instalador
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public Iterable<OrdemServico> getAllByHasInstaller(@PathVariable("id") String id) {
		return repo.findAllByInstaladorId(Long.valueOf(id)); // todos do instalador que possui o id
	}
	
	@PutMapping("/assumir/{id}")
	public OrdemServico putOrdemServico(@PathVariable("id") long id, @RequestBody OrdemServico os) {		
		os.setInstalador(userRepo.findById(id).get());
		return repo.save(os);
	}
	
	@PutMapping("/finalizar")
	public OrdemServico putOrdemServicoFinalizada(@RequestBody OrdemServico os, @RequestParam String dataInicioIntalacao, @RequestParam String dataFinalInstalacao) {		
		System.out.println("on Finalizar inicio "+dataInicioIntalacao+ " fim "+dataFinalInstalacao);
		try {
			os.setDataInicioIntalacao(new SimpleDateFormat("yyyy-MM-dd").parse(dataInicioIntalacao));
			os.setDataFinalInstalacao(new SimpleDateFormat("yyyy-MM-dd").parse(dataFinalInstalacao));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repo.save(os);
	}
}
