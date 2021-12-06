package manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.Entreprise;
import entities.Filiale;
import entities.Secteur;



public class SecteurManager {

public static void main(String[] args) {
		
		SessionFactory sessionFactory;
		
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		sessionFactory = new MetadataSources(registry)
				.buildMetadata()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Entreprise ent1 = session.get(Entreprise.class, 1L);
		System.out.println(ent1);
		
		Filiale fil = new Filiale();
		fil.setNom("Batiment");
		fil.setEntreprise(ent1);
		
		Filiale fil2 = new Filiale();
		fil2.setNom("Assurance");
		fil2.setEntreprise(ent1);
		
		Secteur sect = new Secteur();
		sect.setNom("Android");
		sect.setLocalisation("Roubaix");
		Secteur sect2 = new Secteur();
		sect2.setNom("IOS");
		sect2.setLocalisation("Marcq");
		
		fil.addSecteur(sect);
		fil2.addSecteur(sect2);
		fil2.addSecteur(sect2);

		sect.addFiliale(fil2);
		sect.addFiliale(fil);
		sect2.addFiliale(fil2);
		
		session.save(fil);
		session.save(fil2);
		
		session.getTransaction().commit(); 
		session.close();
		sessionFactory.close();
	}
}
