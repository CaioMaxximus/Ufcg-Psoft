package psoft.lab2.lab2.services;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Service;

import psoft.lab2.lab2.entities.Usuario;
import psoft.lab2.lab2.repository.UsuariosRepository;

@Service
public class ServicesUsuario {
	
	
	private UsuariosRepository<Usuario, String> usuariosDao ;
	
	public ServicesUsuario(UsuariosRepository<Usuario, String> usuariosDao) {
		super();
		this.usuariosDao = usuariosDao;
	}
	
	
//	
//	POST /v1/auth/usuarios (adiciona um usuario
//			com email, nome e senha - o email é o login do usuario e deve ser um 
//			identificador único na tabela de usuário);
	
	public Usuario addUsuario(Usuario usuario) {
		return usuariosDao.save(usuario);
	}
	
	public Optional<Usuario> getUsuario(String email) {
		return usuariosDao.findById(email);
	}
	
	public boolean exist(Usuario usuario) {
		Optional<Usuario> user = usuariosDao.findById(usuario.getEmail());
	
		return user.isPresent();
	}
	
	public boolean exist(String emailUsuario) {
		Optional<Usuario> user = usuariosDao.findById(emailUsuario);
	
		return user.isPresent();
	}
	
	public boolean login(Usuario usuario) {
		if (usuario.getEmail() != null) {
			
			Optional<Usuario> user = usuariosDao.findById(usuario.getEmail());
			System.out.println(usuario.getSenha() + "--");
			System.out.println(user.get().getSenha());
			System.out.println(user.isPresent());
			System.out.println(user.get().getSenha().equals(usuario.getSenha()));

			return user.isPresent() && user.get().getSenha().equals(usuario.getSenha());

		}
		return false;
	}
	
	public Optional<Usuario> remove(String email) {
		Optional<Usuario> user = getUsuario(email);
		usuariosDao.deleteById(email);
		return user;
	}

	
}
