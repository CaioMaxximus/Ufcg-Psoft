package psoft.lab2.lab2.services;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import psoft.lab2.lab2.entities.Usuario;

@Service
public class JWTServices {
	private ServicesUsuario usuariosService;

	public JWTServices(ServicesUsuario usuariosService) {
		super();
		this.usuariosService = usuariosService;
	}

	public boolean usuarioExiste(String authorizationHeader) throws ServletException {
		String subject = getSujeitoDoToken(authorizationHeader);

		return usuariosService.exist(subject);
	}
	
	public boolean usuarioTemPermissao(String authorizationHeader, String email) throws ServletException {
		String subject = getSujeitoDoToken(authorizationHeader);
		Optional<Usuario> user = usuariosService.getUsuario(subject);
		
		return usuariosService.login(user.get());
	}

	public String getSujeitoDoToken(String authorizationHeader) throws ServletException {
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou mal formatado!");
		}

		// Extraindo apenas o token do cabecalho.
		String token = authorizationHeader.substring(psoft.lab2.lab2.filtros.FiltroToken.TOKEN_INDEX);

		String subject = null;
		try {
			subject = Jwts.parser().setSigningKey("login do batman").parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new ServletException("Token invalido ou expirado!");
		}
		return subject;
	}

}
	

