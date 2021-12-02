package manager;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.*;

/**
 * Class qui me permet d'avoir accès et de modifier la table 
 * @author Administrateur
 *
 */
public class EmployeManager {
	
	//la sessionFactory
	protected SessionFactory sessionFactory;
	
	//List des Employees crées
	protected List<Employe> listEmployes = new ArrayList<Employe>();
	
	/**
	 * load une session hibernate
	 */
	protected void setup() { //COde pour load une session hibernate
		
		//On récupère la configuration d'hibernate pour récupérer la registry
		//Attention ici on ne se connecte pas donc on ne lance pas de session avec la bdd
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		
		try {
			//On essaye de se connecter à la bdd 
			//Construction de la session
			sessionFactory = new MetadataSources(registry)
					.buildMetadata()
					.buildSessionFactory();
		}catch(Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	/**
	 * //supprime la session hibernate
	 */
	protected void exit() { 
		sessionFactory.close();
	}
	
	
	//CRUD
	/**
	 * créer un enregistrement 
	 */
	protected void create() {
		Employe emp = new Employe();
		emp.setNom("Henri");
		emp.setPrenom("Jules");
		emp.setCourriel("jules.henri@employe.fr");
		emp.setAge(32);
		emp.setFonction("cadre");
		emp.setTelephone("0668997326");
		emp.setAdresse("1 rue Charles Edouard");
		
		
		Employe emp2 = new Employe();
		emp2.setNom("Machin");
		emp2.setPrenom("Nana");
		emp2.setCourriel("machin.nana@employe.fr");
		emp2.setAge(42);
		emp2.setFonction("cadre");
		emp2.setTelephone("0668997327");
		emp2.setAdresse("123 rue lalaland");
		
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(emp);
		session.save(emp2);
		//Je l'ajoute à la liste
		listEmployes.add(emp);
		listEmployes.add(emp2);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * //lecture d'un enregistrement et renvoit l'employe
	 * @param id
	 * @return
	 */
	protected Employe read(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Employe emp = session.get(Employe.class, id);
		System.out.println(emp.toString());
		
		return emp;
	}
	
	/**
	 * met à jour un enregistrement
	 * @param id
	 * @param newEmp
	 */
	protected void update(long id, Employe newEmp) {
		Employe emp = this.read(id);
		
		if(newEmp.getNom()!=null)
			emp.setNom(newEmp.getNom());
		if(newEmp.getPrenom()!=null)
			emp.setPrenom(newEmp.getPrenom());
		if(newEmp.getCourriel()!=null)
			emp.setCourriel(newEmp.getCourriel());
		if(newEmp.getAge()!=emp.getAge())
			emp.setAge(newEmp.getAge());
		if(newEmp.getFonction()!=null)
			emp.setFonction(newEmp.getFonction());
		if(newEmp.getTelephone()!=null)
			emp.setTelephone(newEmp.getTelephone());
		if(newEmp.getAdresse()!=null)
			emp.setAdresse(newEmp.getAdresse());
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(emp);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * supprimer un enregistrement
	 * @param emp
	 */
	protected void delete(Employe emp) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(emp);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * voir toute la table d'employee / tous les enregistrements
	 */
	protected void readAll() {
		
		for (Employe employe : listEmployes) {
			System.out.println(employe.toString());
		}
		
		
	}
	
	public static void main(String[] args) {
		EmployeManager manager = new EmployeManager();
		manager.setup();
		//CREATE
//		manager.create();
		
		//UPDATE
//		Employe emp = new Employe();
//		emp.setAge(23);
//		manager.update(1, emp);
		
		//DELETE
//		Employe emp = manager.read(1);
//		manager.delete(emp);
		
		//readAll
		manager.readAll();
		
		manager.exit();
	}
}

	
//Objectif :
//
//	 
//
//N1 : Créer un systeme pour enregistrer, editer et supprimer un employé puis voir un  employé et voir la liste de tous les employés
//de l'entreprise Touloulou.
//
// 
//
//Employé :
//Nom
//Prénom
//Courriel
//Age
//Fonction
//Telephone
//Addresse postal
//
// 
//
//N2 : Réfléchir en archi N'3
//
//1 controller
//1 service
//1 repo