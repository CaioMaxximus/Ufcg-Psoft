package psoft.lab2.lab2.disciplinaController;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import psoft.lab2.lab2.entities.Usuario;
import psoft.lab2.lab2.services.JWTServices;
import psoft.lab2.lab2.services.ServicesUsuario;


@RestController
@RequestMapping("/auth")
public class ControllerLogin{

	@Autowired
	private ServicesUsuario servicesUsuario;
	
	@Autowired
	private JWTServices servicesJWT;
	
	private final String TOKEN_KEY = "login do batman";
	
	@PostMapping("/login")
	public LoginResponse authenticate(@RequestBody Usuario usuario) throws ServletException{
		// Recupera o usuario
				
			// verificacoes
				if (!servicesUsuario.exist(usuario)) {
					throw new ServletException("Usuario nao encontrado!");
				}

				if (!servicesUsuario.login(usuario)) {
					throw new ServletException("Senha invalida!");
				}

				String token = Jwts.builder().setSubject(usuario.getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
						.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 10000)).compact();

				return new LoginResponse(token);
	}
	
	@DeleteMapping("/usuarios")
	public ResponseEntity<Usuario> deleteUsuario(@RequestHeader ("Authorization") String header) {
		
		try {
			String email = servicesJWT.getSujeitoDoToken(header);
			System.out.println(email);
			if(servicesJWT.usuarioExiste(header)) {
				return new ResponseEntity<Usuario>(servicesUsuario.remove(email).get(),HttpStatus.OK);
			}
		}catch(ServletException e){
			return new ResponseEntity<Usuario>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
	}
	
	public class LoginResponse{
		
		private String token;
		
		public LoginResponse(String token) {
			this.token = token;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	}
	
	
	
}
