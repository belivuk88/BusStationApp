package ftninformatika.primer.testa.bus.service;

import java.util.List;

import ftninformatika.primer.testa.bus.model.Rezervacija;

public interface RezervacijaService {
	
	Rezervacija rezervisi(Long linijaId, Long userId);
	
	Rezervacija save(Rezervacija rezervacija);
	
	Rezervacija rezervisi(Rezervacija rezervacija);
	
	List<Rezervacija>getAll();

}
