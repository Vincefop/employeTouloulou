package entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "entreprise")
public class Entreprise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long entrepriseId;
	private String nom;
	@OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
	private Set<Filiale> filiales;
	
	public long getEntrepriseId() {
		return entrepriseId;
	}
	public void setEntrepriseId(long entrepriseId) {
		this.entrepriseId = entrepriseId;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Set<Filiale> getFiliales() {
		return filiales;
	}
	public void setFiliales(Set<Filiale> filiales) {
		this.filiales = filiales;
	}
	
	
}
