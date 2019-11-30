package ifsp.edu.br.ProjetoFinal.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ifsp.edu.br.ProjetoFinal.modelo.OrdemServico;

public interface OrdemServicoRepositorio extends CrudRepository<OrdemServico, Long>{
	List<OrdemServico> findAllByInstaladorId(long id);
}
