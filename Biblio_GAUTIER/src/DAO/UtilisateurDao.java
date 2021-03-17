package DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Schemas.Adherent;
import Schemas.Employe;
import Schemas.EnumCategorieEmploye;
import Schemas.Utilisateur;

public class UtilisateurDao 
{
	public Utilisateur [] utilisateurDB =	
		{
				new Adherent("Moris", "Eléanor", "20/01/2021", "Femme", 1,"123quatre","Premier1", "0643 55 73 29"),
				new Adherent("Thomson", "Solan", "30/03/2019", "Homme", 2,"45ji","Solan77", "0589 65 04 13"),
				new Employe("Miral", "Paul", "12/04/1999", "Homme", 3,"emc2","Enstein555", "BU_Paris13_01 1", EnumCategorieEmploye.BIBLIOTHECAIRE),
				new Employe("Liran", "Marcella", "15/04/2018", "Femme", 4,"super4","Khara El", "BU_Paris13_01 2", EnumCategorieEmploye.RESPONSABLE)
				
		};
	
	
	public Utilisateur findByKey( int id){
	    for (Utilisateur utilisateur : utilisateurDB) {
	        if (utilisateur.getidUtilisateur()==id)
	        	return utilisateur;
	    }
	    return null;
	}

	public List <Utilisateur> findAll() {
		List <Utilisateur> userTrouves = new ArrayList <Utilisateur> ();
		for (Utilisateur util : utilisateurDB) {
			userTrouves.add(util);
		}
		return userTrouves;	
	}
	 public static void main(String[] args) {
		
            
			UtilisateurDao udao = new UtilisateurDao();
			 
			Iterator <Utilisateur> it;
			 
			System.out.println("-------------------------");
			System.out.println("\"1\" trouvé:\n\t" + udao.findByKey(1) );
			System.out.println("-------------------------");
			
			System.out.println("-------------------------");
			System.out.println("\"1\" trouvé:\n\t" + udao.findByKey(3) );
			System.out.println("-------------------------");
			
			System.out.println("-------------------------");
			System.out.println("Voici tous les Utilisateurs");
			System.out.println("-------------------------");
			it = udao.findAll().iterator();
			while( it.hasNext() ) 
			{
				System.out.println("\t" + it.next());
			}
	}
}