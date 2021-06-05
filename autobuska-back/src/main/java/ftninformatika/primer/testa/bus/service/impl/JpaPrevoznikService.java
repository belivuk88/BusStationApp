package ftninformatika.primer.testa.bus.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftninformatika.primer.testa.bus.model.Prevoznik;
import ftninformatika.primer.testa.bus.repository.PrevoznikRepository;
import ftninformatika.primer.testa.bus.service.PrevoznikService;
@Service
public class JpaPrevoznikService implements PrevoznikService {

	@Autowired
	PrevoznikRepository prevoznikRepository;
	
	@Override
	public Optional<Prevoznik> findOne(Long id) {
		// TODO Auto-generated method stub
		return prevoznikRepository.findById(id); 
	}

	@Override
	public List<Prevoznik> findAll() {
		// TODO Auto-generated method stub
		return prevoznikRepository.findAll();
	}

	@Override
	public Prevoznik save(Prevoznik prevoznik) {
		// TODO Auto-generated method stub
		return prevoznikRepository.save(prevoznik);
	}

}
