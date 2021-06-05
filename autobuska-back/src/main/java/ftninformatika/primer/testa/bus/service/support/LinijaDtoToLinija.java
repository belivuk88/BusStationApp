package ftninformatika.primer.testa.bus.service.support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ftninformatika.primer.testa.bus.model.Linija;
import ftninformatika.primer.testa.bus.model.Prevoznik;
import ftninformatika.primer.testa.bus.service.LinijaService;
import ftninformatika.primer.testa.bus.service.PrevoznikService;
import ftninformatika.primer.testa.bus.service.web.dto.LinijaDTO;


@Component
public class LinijaDtoToLinija implements Converter <LinijaDTO, Linija> {
	
	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private PrevoznikService prevoznikService;
	
	@Override
	public Linija convert(LinijaDTO dto) {
		
		Prevoznik prevoznik = null;
		if(dto.getPrevoznikId() !=null) {
			prevoznik = prevoznikService.findOne(dto.getPrevoznikId()).get();
		}
		
		Linija linija;
		
		if(dto.getId() == null) {
			linija = new Linija();
		}else {
			linija = linijaService.findOne(dto.getId()).get();
		}
		
		if(linija !=null) {
			linija.setBrojMesta(dto.getBrojMesta());
			linija.setCenaKarte(dto.getCenaKarte());
			linija.setVremePolaska(dto.getVremePolaska());
			linija.setDestinacija(dto.getDestinacija());
			linija.setPrevoznik(prevoznik);
		}
		return linija;
	}
}
	

