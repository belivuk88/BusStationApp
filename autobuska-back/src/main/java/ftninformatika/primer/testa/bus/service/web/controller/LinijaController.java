package ftninformatika.primer.testa.bus.service.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import ftninformatika.primer.testa.bus.model.Linija;
import ftninformatika.primer.testa.bus.service.LinijaService;
import ftninformatika.primer.testa.bus.service.support.LinijaDtoToLinija;
import ftninformatika.primer.testa.bus.service.support.LinijaToLinijaDto;
import ftninformatika.primer.testa.bus.service.web.dto.LinijaDTO;

@RestController
@RequestMapping(value= "/api/linije", produces = MediaType.APPLICATION_JSON_VALUE)
public class LinijaController {

	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private LinijaDtoToLinija toLinija;
	
	@Autowired
	private LinijaToLinijaDto toLinijaDto;
	
	@GetMapping
	public ResponseEntity<List<LinijaDTO>> get(@RequestParam(value = "destinacija", required = false) String destinacija,
			@RequestParam(value = "prevoznikId", required = false) Long prevoznikId,
			@RequestParam(value = "maxCenaKarte", required = false) Double maxCenaKarte,
			@RequestParam(value = "pageNo", defaultValue = "0")int pageNo){
//	public ResponseEntity<List<LinijaDTO>> getAll(
//			@RequestParam(required = false) Long prevoznikId,
//			@RequestParam(required = false) String destinacija,
//			@RequestParam(required = false) Integer brojMesta,
//			@RequestParam(required = false) String vremePolaska,
//			@RequestParam(required = false) Double minCenaKarte,
//			@RequestParam(required = false) Double maxCenaKarte,
//			@RequestParam(defaultValue = "0") int pageNo){
		
		Page<Linija> page = null;
		
		if(destinacija !=null || prevoznikId !=null || maxCenaKarte !=null) {
			page = linijaService.pretraga(destinacija, prevoznikId, maxCenaKarte, pageNo);
		}else {
			page = linijaService.findAll(pageNo);
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Total-Pages", page.getTotalPages() + "");
		
		return new ResponseEntity<>(toLinijaDto.convert(page.getContent()), responseHeaders, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LinijaDTO> getOne(@PathVariable Long id){
		Linija linija = linijaService.findOne(id).get();
		
		if(linija !=null) {
			return new ResponseEntity<>(toLinijaDto.convert(linija), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LinijaDTO> add(@Valid @RequestBody LinijaDTO linijaDTO){
		Linija linija = toLinija.convert(linijaDTO);
		
		Linija sacuvana = linijaService.save(linija);
		
		return new ResponseEntity<>(toLinijaDto.convert(sacuvana), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LinijaDTO> update(@PathVariable Long id, @Valid @RequestBody LinijaDTO linijaDTO){
		
		if(!id.equals(linijaDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Linija linija = toLinija.convert(linijaDTO);
		Linija sacuvanaLinija = linijaService.update(linija);
		
		return new ResponseEntity<>(toLinijaDto.convert(sacuvanaLinija), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Linija obrisanaLinija = linijaService.delete(id);
		
		if(obrisanaLinija !=null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/{id}/rezervisi")
	public ResponseEntity<Linija> rezervacija (@PathVariable Long id){
		
		Linija linija = linijaService.rezervacija(id);
		
		if(linija !=null) {
			
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
