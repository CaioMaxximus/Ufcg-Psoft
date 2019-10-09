package psoft.lab2.lab2.entities;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;


public class DisciplinaDTO  {
	private String nome;
	private double nota;
	
	public DisciplinaDTO() {
		super();
	}
	
	public DisciplinaDTO(String nome, double nota) {
		this.nome = nome;
		this.nota = nota;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	
	}
	
	public Disciplina createDisciplina() {
		Disciplina novaDisciplina = new Disciplina(this.getNome(),this.getNota());
		return novaDisciplina;
		
	}

	
	
}
