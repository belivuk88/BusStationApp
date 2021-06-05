package ftninformatika.primer.testa.bus.service.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ftninformatika.primer.testa.bus.model.Linija;
import ftninformatika.primer.testa.bus.service.web.dto.LinijaDTO;
import ftninformatika.primer.testa.bus.service.web.dto.PrevoznikDTO;

@Component
public class LinijaToLinijaDto implements Converter <Linija, LinijaDTO> {
	
	@Autowired
	private PrevoznikToPrevoznikDto toDto;
	
	@Override
	public LinijaDTO convert (Linija linija) {
		LinijaDTO linijaDTO = new LinijaDTO();
		linijaDTO.setId(linija.getId());
		linijaDTO.setDestinacija(linija.getDestinacija());
		linijaDTO.setBrojMesta(linija.getBrojMesta());
		linijaDTO.setVremePolaska(linija.getVremePolaska());
		linijaDTO.setCenaKarte(linija.getCenaKarte());
		linijaDTO.setPrevoznikId(linija.getPrevoznik().getId());
		
		PrevoznikDTO prevoznik = toDto.convert(linija.getPrevoznik());
		
		linijaDTO.setPrevoznik(prevoznik);
		
		return linijaDTO;
		
		
	}
	
	public List<LinijaDTO> convert (List<Linija> linije){
		List<LinijaDTO>linijeDTO = new ArrayList<>();	
	
		for (Linija linija : linije) {
			linijeDTO.add(convert(linija));
		}
		
		return linijeDTO;
	}

}


