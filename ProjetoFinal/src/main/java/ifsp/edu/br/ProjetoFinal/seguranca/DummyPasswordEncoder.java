package ifsp.edu.br.ProjetoFinal.seguranca;

import org.springframework.security.crypto.password.PasswordEncoder;

public class DummyPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		StringBuilder sb = new StringBuilder();
		for (int i = rawPassword.length() - 1; i >=0; i--) {
			sb.append(rawPassword.charAt(i));
		}
		return sb.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(encode(rawPassword));
	}

}
