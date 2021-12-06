package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "filiale")
public class Filiale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long filialeId;
	private String nom;
	private int nbreEmp;
	@ManyToOne
	@JoinColumn(name = "entrepriseId")
	private Entreprise entreprise;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name="filiales_secteurs",
			joinColumns = @JoinColumn(name= "filiale_id"),
			inverseJoinColumns = @JoinColumn (name="secteur_id")
			)
	private Set<Secteur> secteurs = new HashSet<Secteur>();
	
	//A chaque fois que je rajoute un secteur dans une filiale, 
	//je rajoute le nbre d'employ�s de ce secteur � mon compteur d'employ�s
	public void addSecteur( Secteur secteur) {
		this.secteurs.add(secteur);
		this.nbreEmp += secteur.getEmployes().size();
	}
	
	public long getFilialeId() {
		return filialeId;
	}
	public void setFilialeId(long filialeId) {
		this.filialeId = filialeId;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNbreEmp() {
		return nbreEmp;
	}
	public void setNbreEmp(int nbreEmp) {
		this.nbreEmp = nbreEmp;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	
	public Set<Secteur> getSecteurs() {
		return secteurs;
	}
	public void setSecteurs(Set<Secteur> secteurs) {
		this.secteurs = secteurs;
	}
	@Override
	public String toString() {
		return "Filiale [filialeId=" + filialeId + ", nom=" + nom + ", nombreEmployes=" + nbreEmp
				+ ", entreprise=" + entreprise.getNom() + "]";
	}
	
	
}
