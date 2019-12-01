package ifsp.edu.br.ProjetoFinal.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ifsp.edu.br.ProjetoFinal.modelo.OrdemServico;


public interface OrdemServicoRepositorio extends CrudRepository<OrdemServico, Long>{
	List<OrdemServico> findAllByInstaladorId(long id);
	
	@Query("SELECT o FROM OrdemServico o WHERE o.instalador.id = null")
	List<OrdemServico> findAllByNotHasInstalador();
	
	@Query("SELECT o FROM OrdemServico o WHERE o.dataContratacao+5 > o.dataFinalInstalacao") //Validar Querys
	List<OrdemServico> findOsAtrasada();
	
	@Query("SELECT o FROM OrdemServico o WHERE o.dataContratacao+5 <= o.dataFinalInstalacao") //Validar Querys // Pensar num forma de colocar isso por instalador :(
	List<OrdemServico> findOsFechadaEmDia();
}
