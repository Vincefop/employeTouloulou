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
@Table(name="secteur")
public class Secteur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long secteurId;
	private String nom;
	private String localisation;
	@OneToMany(mappedBy = "secteur", cascade = CascadeType.ALL)
	private Set<Employe> employes;
	
	
	public long getSecteurId() {
		return secteurId;
	}
	public void setSecteurId(long secteurId) {
		this.secteurId = secteurId;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public Set<Employe> getEmployes() {
		return employes;
	}
	public void setEmployes(Set<Employe> employes) {
		this.employes = employes;
	}
	
	
}
