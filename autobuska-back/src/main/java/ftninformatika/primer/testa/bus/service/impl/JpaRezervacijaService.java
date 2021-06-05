package ftninformatika.primer.testa.bus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftninformatika.primer.testa.bus.model.Linija;
import ftninformatika.primer.testa.bus.model.Rezervacija;
import ftninformatika.primer.testa.bus.repository.RezervacijaRepository;
import ftninformatika.primer.testa.bus.repository.UserRepository;
import ftninformatika.primer.testa.bus.service.LinijaService;
import ftninformatika.primer.testa.bus.service.RezervacijaService;

@Service
public class JpaRezervacijaService implements RezervacijaService {
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LinijaService linijaService;

	@Override
	public Rezervacija rezervisi(Long linijaId, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rezervacija save(Rezervacija rezervacija) {
		// TODO Auto-generated method stub
		return rezervacijaRepository.save(rezervacija);
	}

	@Override
	public Rezervacija rezervisi(Rezervacija rezervacija) {
		Linija linija = linijaService.findOne(rezervacija.getLinija().getId()).get();
		Rezervacija rezervisana;
		if(linija.getBrojMesta() > rezervacija.getBrojPutnika()) {
			rezervisana = rezervacijaRepository.save(rezervacija);
			linija.setBrojMesta(linija.getBrojMesta() - rezervacija.getBrojPutnika());
			linijaService.update(linija);
			return rezervisana;
		}
		return null;
	}

	@Override
	public List<Rezervacija> getAll() {
		// TODO Auto-generated method stub
		return rezervacijaRepository.findAll();
	}

}
