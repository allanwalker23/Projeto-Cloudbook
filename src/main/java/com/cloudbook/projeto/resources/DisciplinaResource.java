package com.cloudbook.projeto.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cloudbook.projeto.domain.Disciplina;
import com.cloudbook.projeto.domain.Repositorio;
import com.cloudbook.projeto.domain.dto.DisciplinaDTO;
import com.cloudbook.projeto.domain.dto.DisciplinaNewDTO;
import com.cloudbook.projeto.domain.dto.RepositorioDTO;
import com.cloudbook.projeto.services.DisciplinaService;

@RestController
@RequestMapping(value = "/disciplinas")
public class DisciplinaResource {

	@Autowired
	private DisciplinaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Disciplina> find(@PathVariable Integer id) {
		Disciplina obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody DisciplinaNewDTO disDto){
		Disciplina obj = service.fromDTO(disDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	@RequestMapping(value="/{id}",method =RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody DisciplinaDTO disDto,@PathVariable Integer id ){
		Disciplina obj = service.fromDTO(disDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DisciplinaDTO>> findAll() {
		List<Disciplina> list = service.findAll();
		List<DisciplinaDTO> listDto = list.stream().map(obj -> new DisciplinaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<DisciplinaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "ordeyBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Disciplina> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<DisciplinaDTO> listDto = list.map(obj -> new DisciplinaDTO(obj));
		return ResponseEntity.ok().body(listDto);

	}
	
	@RequestMapping(value="/findDisciplinas",method=RequestMethod.GET)
	public ResponseEntity<Page<DisciplinaDTO>> findPage(
			@RequestParam(value="id", defaultValue="") Integer id,  
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		List<Integer> ids = new ArrayList<>();
		ids.add(id);
		Page<Disciplina> list = service.search(ids, page, linesPerPage, orderBy, direction);
		Page<DisciplinaDTO> listDto = list.map(obj -> new DisciplinaDTO(obj));  
		return ResponseEntity.ok().body(listDto);
		
	}

}
