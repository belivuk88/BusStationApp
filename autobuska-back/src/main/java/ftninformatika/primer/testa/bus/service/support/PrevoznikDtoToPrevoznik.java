package ftninformatika.primer.testa.bus.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ftninformatika.primer.testa.bus.model.Prevoznik;
import ftninformatika.primer.testa.bus.service.PrevoznikService;
import ftninformatika.primer.testa.bus.service.web.dto.PrevoznikDTO;

@Component
public class PrevoznikDtoToPrevoznik implements Converter <PrevoznikDTO, Prevoznik> {
	
	@Autowired
	private PrevoznikService prevoznikService;
	
	@Override
	public Prevoznik convert(PrevoznikDTO dto) {
		
		Prevoznik prevoznik;
	
		if(dto.getId() == null) {
			prevoznik = new Prevoznik();
	}else {
		prevoznik = prevoznikService.findOne(dto.getId()).get();
	}
		if(prevoznik !=null) {
			prevoznik.setNaziv(dto.getNaziv());
			prevoznik.setAdresa(dto.getAdresa());
			prevoznik.setPIB(dto.getPIB());
		}
		return prevoznik;
}
	
}
