package ftninformatika.primer.testa.bus.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


import ftninformatika.primer.testa.bus.enumerations.KorisnickaUloga;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private KorisnickaUloga role;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Rezervacija> rezervacije = new ArrayList<>();

    public User(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public KorisnickaUloga getRole() {
		return role;
	}


	public void setRole(KorisnickaUloga role) {
		this.role = role;
	}


	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}


	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
	

    

}
