package ftninformatika.primer.testa.bus.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ftninformatika.primer.testa.bus.model.Linija;
import ftninformatika.primer.testa.bus.model.Rezervacija;
import ftninformatika.primer.testa.bus.repository.LinijaRepository;
import ftninformatika.primer.testa.bus.service.LinijaService;
import ftninformatika.primer.testa.bus.service.RezervacijaService;
@Service
public class JpaLinijaService implements LinijaService {
	
	@Autowired
	LinijaService linijaService;
	
	@Autowired
	LinijaRepository linijaRepository;
	
	@Autowired
	RezervacijaService rezervacijaService;


	@Override
	public List<Linija> findAll() {
		// TODO Auto-generated method stub
		return linijaRepository.findAll();
	}

	@Override
	public Page<Linija> findAll(int page) {
		// TODO Auto-generated method stub
		return linijaRepository.findAll(PageRequest.of(page, 2));
	}

	@Override
	public Optional<Linija> findOne(Long id) {
		// TODO Auto-generated method stub
		return linijaRepository.findById(id);
	}

	@Override
	public Linija save(Linija linija) {
		// TODO Auto-generated method stub
		return linijaRepository.save(linija);
	}

	@Override
	public Linija update(Linija linija) {
		// TODO Auto-generated method stub
		return linijaRepository.save(linija);
	}

	@Override
	public Linija delete(Long id) {
		// TODO Auto-generated method stub
		Linija linija = findOne(id).get();
		if(linija !=null) {
			linija.getPrevoznik().getLinije().remove(linija);
			linija.setPrevoznik(null);
			linijaRepository.delete(linija);
			return linija;
		}
		return null;
	}

	@Override
	public Linija rezervacija(Long id) {
		
		Linija linija = linijaRepository.getOne(id);
		
		if(linija.getBrojMesta()>0) {
			Rezervacija rezervacija = new Rezervacija();
			rezervacija.setBrojPutnika(1);
			rezervacija.setLinija(linija);
			rezervacijaService.save(rezervacija);
			
			linija.setBrojMesta(linija.getBrojMesta() - 1);
			linijaService.update(linija);
			
			return linijaRepository.save(linija);
		}
		
		return null;
	}

	@Override
	public Page<Linija> pretraga(String destinacija, Long prevoznikId, Double maxCenaKarte, int page) {
		// TODO Auto-generated method stub
		return linijaRepository.pretraga(destinacija, prevoznikId, maxCenaKarte, PageRequest.of(page, 2));
	}

//	@Override
//	public List<Linija> find(Long prevoznikId, String destinacija, Integer brojMesta, String vremePolaska,
//			Double minCenaKarte, Double maxCenaKarte) {
//		
//		if(minCenaKarte == null) {
//			minCenaKarte = 0.0;
//		}
//		
//		if(maxCenaKarte == null) {
//			maxCenaKarte = Double.MAX_VALUE;
//			
//		}
//		if(prevoznikId == null) {
//			return linijaRepository.findByDestinacijaAndBrojMestaAndVremePolaskaAndCenaKarteBetween(destinacija, brojMesta, vremePolaska, minCenaKarte, maxCenaKarte);	
//		}
//		if(destinacija == null) {
//			return linijaRepository.findByPrevoznikIdAndBrojMestaAndVremePolaskaAndCenaKarteBetween(prevoznikId, brojMesta, vremePolaska, minCenaKarte, maxCenaKarte);
//		}
//		
//		return linijaRepository.findByPrevoznikIdAndDestinacijaAndBrojMestaAndVremePolaskaAndCenaKarteBetween(prevoznikId, destinacija, brojMesta, vremePolaska, minCenaKarte, maxCenaKarte);
//	}

}
