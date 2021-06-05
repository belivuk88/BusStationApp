package ftninformatika.primer.testa.bus.service.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ftninformatika.primer.testa.bus.model.Rezervacija;
import ftninformatika.primer.testa.bus.service.web.dto.RezervacijaDTO;

@Component
public class RezervacijaToRezervacijaDto implements Converter<Rezervacija, RezervacijaDTO> {
	
	private LinijaToLinijaDto toLinijaDto;
	
	@Override
	public RezervacijaDTO convert (Rezervacija rezervacija) {
		RezervacijaDTO rezervacijaDTO = new RezervacijaDTO();
		rezervacijaDTO.setId(rezervacija.getId());
		rezervacijaDTO.setBrojPutnika(rezervacija.getBrojPutnika());
		rezervacijaDTO.setLinija(toLinijaDto.convert(rezervacija.getLinija()));
		return rezervacijaDTO;
	}
	
	public List<RezervacijaDTO> convert(List<Rezervacija> rezervacije){
		List<RezervacijaDTO> rezervacijaDTO = new ArrayList<>();
		
		for(Rezervacija rezervacija: rezervacije) {
			rezervacijaDTO.add(convert(rezervacija));
		}
		
		return rezervacijaDTO;
	}

}
