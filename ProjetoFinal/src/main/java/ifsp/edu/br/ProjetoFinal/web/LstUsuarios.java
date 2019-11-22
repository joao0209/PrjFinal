package ifsp.edu.br.ProjetoFinal.web;

import java.util.ArrayList;
import java.util.List;

import ifsp.edu.br.ProjetoFinal.modelo.Usuario;

public class LstUsuarios {
	List<Usuario> usuarios;

	public LstUsuarios() {
		usuarios = new ArrayList<Usuario>();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
