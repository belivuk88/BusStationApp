package ftninformatika.primer.testa.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftninformatika.primer.testa.bus.model.Prevoznik;

@Repository
public interface PrevoznikRepository extends JpaRepository <Prevoznik, Long> {
	
	

}
