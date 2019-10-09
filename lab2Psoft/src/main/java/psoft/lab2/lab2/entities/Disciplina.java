package psoft.lab2.lab2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Disciplina implements Comparable<Disciplina> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private double nota;
	private String comentarios;
	private int likes;
	
	
	public Disciplina() {
		super();
	}
	
	
	public Disciplina(String nome, double nota) {
		this.nome = nome;
		this.nota = nota;
		this.likes = 0;
		this.comentarios = "";
	}
	
	
	public long getId() {
		return this.id;
	}
	
	public void addComentario(String comentario) {
		this.comentarios +=  "\n" + comentario;
	}
	
	public String getComentarios() {
		return comentarios;
	}


	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}


	public int getLikes() {
		return likes;
	}


	public void setLikes(int likes) {
		this.likes = likes;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void addLike() {
		this.likes ++;
	}


	public int compareTo(Disciplina o) {
		// TODO Auto-generated method stub
		return (int) (this.nota - o.getNota());
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
	
	public String toString() {
		return this.nome + "\n";
	}
	
	
	
	

}
