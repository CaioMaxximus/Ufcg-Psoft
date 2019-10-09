package psoft.lab2.lab2.disciplinaController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import psoft.lab2.lab2.entities.Usuario;
import psoft.lab2.lab2.services.ServicesUsuario;


@RestController
@RequestMapping("/usuarios")
public class ControllerUsuarios {
	
	
	@Autowired
	ServicesUsuario usuariosService;
	
	@PostMapping("")
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario){
		return new ResponseEntity<Usuario>(usuariosService.addUsuario(usuario),HttpStatus.OK);
	}
	
	
}
