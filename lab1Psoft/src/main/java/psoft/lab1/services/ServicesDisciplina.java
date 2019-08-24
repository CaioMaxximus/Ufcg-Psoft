package psoft.lab1.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.stereotype.Service;

import psoft.lab1.entities.Disciplina;
import psoft.lab1.entities.DisciplinaDTO;
@Service
public class ServicesDisciplina {

	Map<Integer, Disciplina> listaDisciplinas;
	private int id = 0;
	
	public ServicesDisciplina() {
		this.listaDisciplinas = new HashMap<Integer,Disciplina>();
	}
	
	
	public Disciplina addDisciplina(DisciplinaDTO disciplina){
		
		Disciplina novaDisciplina = new Disciplina(disciplina.getNome(),disciplina.getNota(),this.id);
		listaDisciplinas.put(this.id,novaDisciplina);
		this.id ++;
		return novaDisciplina;
		
		
	}
	
	public Collection<Disciplina> getDisciplinas(){
		return listaDisciplinas.values();
	}
	
	public Disciplina getDisciplina(int id) {
		return listaDisciplinas.get(id);
	}
	
	public Disciplina setNomeDisciplina(int id,Disciplina disciplina) {
		Disciplina novaDisciplina = listaDisciplinas.get(id);
		if (novaDisciplina != null) {
			novaDisciplina.setNome(disciplina.getNome());
		}
		return novaDisciplina;
	}
	
	public Disciplina setNotaDisciplina(int id,Disciplina disciplina) {
		Disciplina novaDisciplina = listaDisciplinas.get(id);
		if (novaDisciplina != null) {
			novaDisciplina.setNota(disciplina.getNota());
		}
		return novaDisciplina;
	}
	
	public Collection<Disciplina> getDisciplinasOrdenadas(){
		ArrayList<Disciplina> colecao = new ArrayList<Disciplina>();
		colecao.addAll(listaDisciplinas.values());
		Collections.sort(colecao);
		return colecao;
	}
	
	public Disciplina deletarDisciplina(int id) {
		return listaDisciplinas.remove(id);
		
	}
	
}
