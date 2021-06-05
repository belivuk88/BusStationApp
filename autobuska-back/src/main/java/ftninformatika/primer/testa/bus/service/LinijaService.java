package ftninformatika.primer.testa.bus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import ftninformatika.primer.testa.bus.model.Linija;

public interface LinijaService {
	
	List<Linija> findAll();
	
	Page<Linija> findAll(int page);
	
	Optional<Linija> findOne(Long id);
	
	Linija save (Linija linija);
	
	Linija update (Linija linija);
	
	Linija delete (Long id);
	
	Linija rezervacija(Long id);
	
//	List <Linija> find(Long prevoznikId, String destinacija, Integer brojMesta, String vremePolaska, Double minCenaKarte, Double maxCenaKarte);
	
	Page<Linija>pretraga(String destinacija, Long prevoznikId, Double maxCenaKarte, int pageNum);
	

}
