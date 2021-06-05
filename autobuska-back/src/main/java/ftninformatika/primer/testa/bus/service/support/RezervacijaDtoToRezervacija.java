package ftninformatika.primer.testa.bus.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ftninformatika.primer.testa.bus.model.Rezervacija;
import ftninformatika.primer.testa.bus.service.web.dto.RezervacijaDTO;

@Component
public class RezervacijaDtoToRezervacija implements Converter <RezervacijaDTO, Rezervacija> {

	@Autowired
	private LinijaDtoToLinija toLinija;
	
	@Override
	public Rezervacija convert(RezervacijaDTO rezervacijaDTO) {
		Rezervacija rezervacija = new Rezervacija();
		
		rezervacija.setId(rezervacijaDTO.getId());
		rezervacija.setBrojPutnika(rezervacijaDTO.getBrojPutnika());
		rezervacija.setLinija(toLinija.convert(rezervacijaDTO.getLinija()));
		
		return rezervacija;
	}
}
