package ifsp.edu.br.ProjetoFinal.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ifsp.edu.br.ProjetoFinal.modelo.OrdemServico;


public interface OrdemServicoRepositorio extends CrudRepository<OrdemServico, Long>{
	List<OrdemServico> findAllByInstaladorId(long id);
	
	@Query("SELECT o FROM ordem_servico o WHERE o.data_contratacao+5 > o.data_final_instalacao") //Validar Querys
	List<OrdemServico> findOsAtrasada();
	
	@Query("SELECT o FROM ordem_servico o WHERE o.data_contratacao+5 <= o.data_final_instalacao") //Validar Querys // Pensar num forma de colocar isso por instalador :(
	List<OrdemServico> findOsFechadaEmDia();
}
