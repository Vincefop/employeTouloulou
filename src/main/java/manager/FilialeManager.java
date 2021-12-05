package manager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.Entreprise;
import entities.Filiale;

/**
 * Class qui me permet d'avoir accès et de modifier la table 
 * @author Administrateur
 *
 */
public class FilialeManager {
	
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
		
		Filiale filiale = new Filiale();
		filiale.setNom("Retail");
		Filiale filiale2 = new Filiale();
		filiale2.setNom("Banque");
		Filiale filiale3 = new Filiale();
		filiale3.setNom("Defense");
		
		Entreprise ent1 = new Entreprise();
		ent1.setNom("SII");
		
		filiale.setEntreprise(ent1);
		filiale2.setEntreprise(ent1);
		filiale3.setEntreprise(ent1);
		
		Set<Filiale> filiales = new HashSet<Filiale>();
		filiales.add(filiale);
		filiales.add(filiale2);
		filiales.add(filiale3);
		ent1.setFiliales(filiales);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(ent1);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * //lecture d'un enregistrement et renvoit la filiale
	 * @param id
	 * @return
	 */
	protected Filiale read(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Filiale fil = session.get(Filiale.class, id);
		session.getTransaction().commit();
		session.close();
		System.out.println(fil.toString());
		return fil;
		
	}
	
	/**
	 * met à jour un enregistrement
	 * @param id
	 * @param newFil
	 */
	protected void update(long id, Filiale newFil) {
		Filiale fil = this.read(id);
		
		if(newFil.getNom()!=null)
			fil.setNom(newFil.getNom());
		if(newFil.getNbreEmp()!=fil.getNbreEmp())
			fil.setNbreEmp(newFil.getNbreEmp());
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(fil);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * supprimer un enregistrement
	 * @param emp
	 */
	protected void delete(Filiale fil) {
	
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(fil);
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
		List<Filiale> listFilQuery = session.createQuery("from Filiale", Filiale.class).getResultList();
		for (Filiale filiale : listFilQuery) {
			System.out.println(filiale.toString());
		}
		
		session.getTransaction().commit();
		session.close();
		
		
	}
	
	public static void main(String[] args) {
		FilialeManager manager = new FilialeManager();
		manager.setup();
		//CREATE
//		manager.create();
		
		//READ
		
//		manager.read(1L);
		
		//UPDATE
//		Filiale fil = new Filiale();
//		fil.setNom("Recherche");
//		fil.setNbreEmp(2);
//		manager.update(1, fil);
		
		//DELETE
//		Filiale fil = manager.read(2);
//		manager.delete(fil);
		
		//readAll
//		manager.readAll();
		
		manager.exit();
	}
}