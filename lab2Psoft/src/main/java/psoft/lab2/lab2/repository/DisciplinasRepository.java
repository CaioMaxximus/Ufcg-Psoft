package psoft.lab2.lab2.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psoft.lab2.lab2.entities.Disciplina;

@Repository
public interface DisciplinasRepository<T, ID extends Serializable> extends JpaRepository<Disciplina,Long> {

}
