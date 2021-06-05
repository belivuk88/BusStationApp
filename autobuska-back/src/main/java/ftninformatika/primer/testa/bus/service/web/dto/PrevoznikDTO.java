package ftninformatika.primer.testa.bus.service.web.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class PrevoznikDTO {

	@Positive(message =" Id mora biti pozitivan broj.")
	private Long id;
	
	@NotBlank(message = "Naziv prevoznika nije zadat.")
	private String naziv;
	
	
	private String adresa;
	
	
	private String PIB;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public String getPIB() {
		return PIB;
	}


	public void setPIB(String pIB) {
		PIB = pIB;
	}
}
