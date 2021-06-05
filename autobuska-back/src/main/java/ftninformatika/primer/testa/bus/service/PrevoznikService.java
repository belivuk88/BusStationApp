package ftninformatika.primer.testa.bus.service;

import java.util.List;
import java.util.Optional;

import ftninformatika.primer.testa.bus.model.Prevoznik;

public interface PrevoznikService {
	
	Optional<Prevoznik> findOne(Long id);
	
	List<Prevoznik> findAll();
	
	Prevoznik save (Prevoznik prevoznik);
	
	

}
