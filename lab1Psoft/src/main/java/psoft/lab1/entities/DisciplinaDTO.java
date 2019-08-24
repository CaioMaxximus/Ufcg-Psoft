package psoft.lab1.entities;

public class DisciplinaDTO implements Comparable<DisciplinaDTO> {
	private String nome;
	private double nota;
	
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
	@Override
	public int compareTo(DisciplinaDTO o) {
		// TODO Auto-generated method stub
		return (int) (this.nota - o.getNota());
	}
		
	
	
}
