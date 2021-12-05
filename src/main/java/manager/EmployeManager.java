package manager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.Employe;
import entities.Secteur;

/**
 * Class qui me permet d'avoir accès et de modifier la table 
 * @author Administrateur
 *
 */
public class EmployeManager {
	
	//la sessionFactory
	protected SessionFactory sessionFactory;
	
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
		
		Secteur secteur1 = new Secteur();
		secteur1.setNom("Finances");
		secteur1.setLocalisation("Paris");
		emp.setSecteur(secteur1);
		Set<Employe> employes = new HashSet<Employe>();
		employes.add(emp);
		secteur1.setEmployes(employes);
		
		
		
		Employe emp2 = new Employe();
		emp2.setNom("Machin");
		emp2.setPrenom("Nana");
		emp2.setCourriel("machin.nana@employe.fr");
		emp2.setAge(39);
		emp2.setFonction("employe");
		emp2.setTelephone("0668997327");
		emp2.setAdresse("123 rue lalaland");
		
		Secteur secteur2 = new Secteur();
		secteur2.setNom("Developpement");
		secteur2.setLocalisation("Lille");
		emp2.setSecteur(secteur2);
		
		
		Employe emp3 = new Employe();
		emp3.setNom("Sylvie");
		emp3.setPrenom("Loute");
		emp3.setCourriel("nouvelle.nana@employe.fr");
		emp3.setAge(42);
		emp3.setFonction("employe");
		emp3.setTelephone("0668997327");
		emp3.setAdresse("34 bd Louis");
		emp3.setSecteur(secteur2);
		
		Set<Employe> employes2 = new HashSet<Employe>();
		employes2.add(emp2);
		employes2.add(emp3);
		secteur2.setEmployes(employes2);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(secteur1);
		session.save(secteur2);
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
		if(newEmp.getAge()!=0)
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
		//solution avec des querys
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Employe> listEmpQuery = session.createQuery("select e from Employe e", Employe.class).getResultList();
		for (Employe employe : listEmpQuery) {
			System.out.println(employe.toString());
		}
		
		session.getTransaction().commit();
		session.close();
		
		
	}
	
	public static void main(String[] args) {
		EmployeManager manager = new EmployeManager();
		manager.setup();
		
		
		//CREATE
//		manager.create();
		
		//READ
//		manager.read(1L);
		
		
		//UPDATE
//		Employe emp = new Employe();
//		emp.setAge(22);
//		manager.update(2, emp);
		
		//DELETE
		Employe emp = manager.read(2);
		manager.delete(emp);
		
		//readAll
//		manager.readAll();
		
		manager.exit();
	}
}
