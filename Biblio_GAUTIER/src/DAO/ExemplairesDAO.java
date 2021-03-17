package DAO;

import Schemas.EnumStatusExemplaire;
import Schemas.Exemplaire;
//Cette Classe permet l'accès aux données (dao)
//version "Mock object" : objet bouchon pour les tests
public class ExemplairesDAO {
	//Tableau qui simule la Base de Données des exemplaires
	public static Exemplaire[] exemplaireDB = {
				new Exemplaire ("abc",1,"11/08/2010",EnumStatusExemplaire.DISPONIBLE),new Exemplaire ("abr",2,"20/10/2012",EnumStatusExemplaire.PRETE),new Exemplaire ("aac",3,"10/04/2012",EnumStatusExemplaire.SUPPRIME),new Exemplaire ("c",4,"20/11/2015",EnumStatusExemplaire.DISPONIBLE)
			};
	private static Exemplaire[] requete = new Exemplaire[2];;

	
	public static Exemplaire[] findByKey(int id, int id2) {
	
		for (Exemplaire exemplaire : exemplaireDB) {
				
		     if(exemplaire.getIdExemplaire()==id) requete[0]=exemplaire;
		     if(exemplaire.getIdExemplaire()==id2) requete[1]=exemplaire;
		    	
		     }
		 
		 return requete;
		
	}
	
}