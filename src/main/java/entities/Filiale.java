package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@Override
	public String toString() {
		return "Filiale [filialeId=" + filialeId + ", nom=" + nom + ", nombreEmployes=" + nbreEmp
				+ ", entreprise=" + entreprise.getNom() + "]";
	}
	
	
}
