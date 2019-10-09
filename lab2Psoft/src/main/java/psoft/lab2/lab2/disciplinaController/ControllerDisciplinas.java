package psoft.lab2.lab2.disciplinaController;

import java.util.Collection;
import java.util.Optional;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import psoft.lab2.lab2.entities.Comentario;
import psoft.lab2.lab2.entities.Disciplina;
import psoft.lab2.lab2.entities.DisciplinaDTO;
import psoft.lab2.lab2.entities.Usuario;
import psoft.lab2.lab2.services.ServicesDisciplina;



@RestController
@RequestMapping("/disciplinas")
public class ControllerDisciplinas {
	
	
	@Autowired
	ServicesDisciplina services;
	
	
	@PostMapping("")
	public Disciplina addDisciplina(@RequestBody DisciplinaDTO disciplina) {
		return services.addDisciplina(disciplina);
	}
	
	@RequestMapping("")
	public Collection<Disciplina> getDisciplinas() {
		return services.getDisciplinas();
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<Disciplina> getDisciplina(@PathVariable("id") int id){
		Optional<Disciplina> disciplina = services.getDisciplina(id);
		if(!disciplina.isPresent()) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Disciplina>(disciplina.get(),HttpStatus.OK);
		}
	}
	
	@PutMapping("/{id}/nome")
	public ResponseEntity<Disciplina> setNomeDisciplina(@PathVariable("id") int id,@RequestBody Disciplina disciplina){
		Optional<Disciplina> disciplinaSaida = services.setNomeDisciplina(id, disciplina);
		if(!disciplinaSaida.isPresent()) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Disciplina>(disciplinaSaida.get(),HttpStatus.OK);
		}
	}
	
	@PutMapping("/{id}/nota")
	public ResponseEntity<Disciplina> setNotaDisciplina(@PathVariable("id") int id,@RequestBody Disciplina disciplina){
		Optional<Disciplina> disciplinaSaida = services.setNotaDisciplina(id, disciplina);
		if(!disciplinaSaida.isPresent()) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Disciplina>(disciplinaSaida.get(),HttpStatus.OK);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Disciplina> deletarDisciplina(@PathVariable("id") int id){
		Optional<Disciplina> disciplinaSaida = services.deletarDisciplina(id);
		if(!disciplinaSaida.isPresent()) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Disciplina>(disciplinaSaida.get(),HttpStatus.OK);
		}
	}
	
	@RequestMapping("/ranking")
	public ResponseEntity<Collection> getDisciplinasOrdenadas(){
		return new ResponseEntity<Collection>(services.getDisciplinasOrdenadas(),HttpStatus.OK);
	}
	
	@PutMapping("/{id}/likes")
	public ResponseEntity<Disciplina> addLike(@PathVariable("id")int id){
		return new ResponseEntity<Disciplina>(services.addLikes(id).get(),HttpStatus.OK);
	}
	

	@PutMapping("/comentarios/{id}")
	public ResponseEntity<Disciplina> addComentario(@PathVariable("id") int id,@RequestBody Comentario comentario) {
		return new ResponseEntity<Disciplina>(services.addComentario(id, comentario).get() ,HttpStatus.OK);
	}
	
	
	
}
