package psoft.lab2.lab2.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psoft.lab2.lab2.entities.Usuario;

@Repository
public interface UsuariosRepository<T,ID extends Serializable> extends JpaRepository<Usuario,String> {

}
