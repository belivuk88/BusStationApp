package ftninformatika.primer.testa.bus.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ftninformatika.primer.testa.bus.model.Linija;

@Repository
public interface LinijaRepository extends JpaRepository <Linija, Long> {
	
//	List<Linija> findByDestinacijaAndBrojMestaAndVremePolaskaAndCenaKarteBetween(String destinacija, Integer brojMesta, String vremePolaska, Double minCenaKarte, Double maxCenaKarte);
//	
//	List<Linija> findByPrevoznikIdAndBrojMestaAndVremePolaskaAndCenaKarteBetween(Long prevoznikId, Integer brojMesta, String vremePolaska, Double minCenaKarte, Double maxCenaKarte);
//	
//	List<Linija> findByPrevoznikIdAndDestinacijaAndBrojMestaAndVremePolaska(Long prevoznikId, String destinacija, Integer brojMesta, String vremePolaska);
//	
//	List<Linija> findByPrevoznikIdAndDestinacijaAndBrojMestaAndVremePolaskaAndCenaKarteBetween(Long prevoznikId, String destinacija, Integer brojMesta, String vremePolaska, Double minCenaKarte, Double maxCenaKarte);
	@Query("SELECT l FROM Linija l WHERE"
			+ " (:destinacija IS NULL OR l.destinacija like %:destinacija% ) AND"
			+ "(:prevoznikId IS NULL OR l.prevoznik.id = :prevoznikId) AND"
			+ "(:maxCenaKarte IS NULL OR l.cenaKarte >= :maxCenaKarte)")
	Page<Linija> pretraga(@Param("destinacija") String destinacija, @Param("prevoznikId") Long prevoznikId, @Param("maxCenaKarte") Double maxCenaKarte, Pageable pageable);

}
