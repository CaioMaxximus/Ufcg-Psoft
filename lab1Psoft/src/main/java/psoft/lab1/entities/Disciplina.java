package psoft.lab1.entities;

public class Disciplina extends DisciplinaDTO {

	private int id;
	
	public Disciplina(String nome, double nota,int id) {
		super(nome, nota);
		this.id = id;
	}
	
	
	public int getId() {
		return this.id;
	}
	
	
	

}
