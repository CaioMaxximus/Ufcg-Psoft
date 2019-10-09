package psoft.lab2.lab2.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import psoft.lab2.lab2.entities.Comentario;
import psoft.lab2.lab2.entities.Disciplina;
import psoft.lab2.lab2.entities.DisciplinaDTO;
import psoft.lab2.lab2.repository.DisciplinasRepository;


@Service
public class ServicesDisciplina {

	private DisciplinasRepository<Disciplina,Long> disciplinasDAO;
	
	public ServicesDisciplina(DisciplinasRepository<Disciplina,Long> disciplinasDao) {
		super();
		this.disciplinasDAO = disciplinasDao;
	}
	
	@PostConstruct
	public void initDisciplinas() {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<DisciplinaDTO>> typeReference = new TypeReference<List<DisciplinaDTO>>() {};
		InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/disciplinas.json");
		if(this.disciplinasDAO.count() == 0) {
			try {
				List<DisciplinaDTO> disciplinas = mapper.readValue(inputStream, typeReference);
				for (DisciplinaDTO disciplinaDTO : disciplinas) {
					this.addDisciplina(disciplinaDTO);
				}
				System.out.println("Salvou!!!");
			}catch(IOException e) {
				System.out.println("Deu ruim " + e.getMessage());
			}
		}
		for (Disciplina d : disciplinasDAO.findAll()) {
			System.out.println(d);
		}
	}
	
	
	public Disciplina addDisciplina(DisciplinaDTO disciplina){
		
		Disciplina novaDisciplina = disciplina.createDisciplina();
		disciplinasDAO.save(novaDisciplina);
		return novaDisciplina;
		
		
	}
	
	public Collection<Disciplina> getDisciplinas(){
		return disciplinasDAO.findAll();
	}
	
	public Optional<Disciplina> getDisciplina(int id) {
		return disciplinasDAO.findById((long) id);
	}
	
	public Optional<Disciplina> setNomeDisciplina(int id,Disciplina disciplina) {
		Optional<Disciplina> novaDisciplina = disciplinasDAO.findById((long) id);
		if (novaDisciplina.isPresent()) {
			novaDisciplina.get().setNome(disciplina.getNome());
		}
		return novaDisciplina;
	}
	
	public Optional<Disciplina> setNotaDisciplina(int id,Disciplina disciplina) {
		Optional<Disciplina> novaDisciplina = disciplinasDAO.findById((long) id);
		if (novaDisciplina.isPresent()) {
			novaDisciplina.get().setNota(disciplina.getNota());
		}
		return novaDisciplina;
	}
	
	public Collection<Disciplina> getDisciplinasOrdenadas(){
		ArrayList<Disciplina> colecao = new ArrayList<Disciplina>();
		colecao.addAll(disciplinasDAO.findAll());

		Collections.sort(colecao);
		return colecao;
	}
	
	public Optional<Disciplina> deletarDisciplina(int id) {
		Optional<Disciplina> saida = this.getDisciplina(id);
		disciplinasDAO.deleteById((long) id);
		return saida;
		
	}
	
	public Optional<Disciplina> addLikes(int id) {
		Optional<Disciplina> saida = this.disciplinasDAO.findById((long) id);
		if(saida.isPresent()) {
			saida.get().addLike();
			disciplinasDAO.save(saida.get());
			System.out.println("like adicionado! ");
		}
		return saida;
	}
	
	public Optional<Disciplina> addComentario(int id,Comentario comentario){
		Optional<Disciplina> disciplina = this.disciplinasDAO.findById((long)id);
		System.out.println(comentario.getComentario());
		if(disciplina.isPresent()) {
			disciplina.get().addComentario(comentario.getComentario());
			disciplinasDAO.save(disciplina.get());
			System.out.println("salvo! ");
		}
		return disciplina;
	}
	
	
}
