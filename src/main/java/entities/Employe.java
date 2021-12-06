package entities;

import javax.persistence.*;

@Entity
@Table(name="employees")
public class Employe {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String nom;
	private String prenom;
	private String courriel;
	private int age;
	private String fonction;
	private String telephone;
	private String adresse;
	@ManyToOne
	@JoinColumn(name = "secteurId")
	private Secteur secteur;
	public Employe() {
	}
	public Employe(long id, String nom, String prenom, String courriel, int age, String fonction, String telephone,
			String adresse) {
		this();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.courriel = courriel;
		this.age = age;
		this.fonction = fonction;
		this.telephone = telephone;
		this.adresse = adresse;
	}
	
	
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		//je v�rifie que ce n'est pas la premi�re fois qu'on attribue un secteur � cet employ�
		if(this.secteur!=null) {
			//A chaque fois que je change un employ� de secteur
			//je l'enl�ve du compteur d'employ� de la filiale ou les filiales o� il �tait compt�
			for (Filiale fil : this.secteur.getFiliales()) {
				if(this.secteur!=null) {//Je v�rifie qu'il avait bien un secteur affili� 
					fil.setNbreEmp(fil.getNbreEmp()-1);//et j'enl�ve 1 du compteur
				}
			}
			//ensuite je le change de secteur. 
			this.secteur = secteur;
			//Et je rajoute une personne � chaque filiale connect�e � ce secteur
			for (Filiale fil : this.secteur.getFiliales()) {
				fil.setNbreEmp(fil.getNbreEmp()+1);
			}
		}
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCourriel() {
		return courriel;
	}
	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	@Override
	public String toString() {
		return "Employe [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", courriel=" + courriel + ", age=" + age
				+ ", fonction=" + fonction + ", telephone=" + telephone + ", adresse=" + adresse + ", secteur="
				+ secteur.getNom() + "]";
	}
	
	
	
	
	
}
