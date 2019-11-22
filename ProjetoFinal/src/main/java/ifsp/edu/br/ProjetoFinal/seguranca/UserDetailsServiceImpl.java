package ifsp.edu.br.ProjetoFinal.seguranca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ifsp.edu.br.ProjetoFinal.data.UsuarioRepositorio;
import ifsp.edu.br.ProjetoFinal.modelo.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger logger = 
			LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UsuarioRepositorio repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repo.findByUsername(username);
		
		logger.debug("Usuário: " + username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException(
					"Username " + username + " não encontrado.");
		}

		if (usuario.getAuthorities().size() == 0) {
			throw new UsernameNotFoundException(
					"Username " + username + ": sem papéis (roles) cadastrados.");
		}
				
		logger.debug(String.format("Usuário: %s, senha: %s, roles: %d", 
				usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities().size()));
		
		return usuario;
	}

}
