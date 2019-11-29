package ifsp.edu.br.ProjetoFinal.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity(name = "instalador_ordem_servico")
public class InstaladorOrdemServico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private Date dataAtribuicao;

	private boolean iniciado;

	private boolean finalizado;

	@ManyToOne
	private Usuario instalador;

	@PrePersist
	private void tratarData() {
		dataAtribuicao = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataAtribuicao() {
		return dataAtribuicao;
	}

	public void setDataAtribuicao(Date dataAtribuicao) {
		this.dataAtribuicao = dataAtribuicao;
	}

	public boolean isIniciado() {
		return iniciado;
	}

	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Usuario getInstalador() {
		return instalador;
	}

	public void setInstalador(Usuario instalador) {
		this.instalador = instalador;
	}

}
