package biblio.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import biblio.domain.EmpruntEnCours;
import biblio.domain.EnumStatusExemplaire;
import biblio.domain.Exemplaire;
import biblio.domain.Utilisateur;

public class EmpruntEnCoursDao {
	Connection cnx3 = null;
	public EmpruntEnCoursDao(Connection cnx3) {
		this.cnx3=cnx3;
	}
	public EmpruntEnCoursDb findByKey(int idExemplaire) throws SQLException
	{
		Statement stmt3 = cnx3.createStatement();
		ResultSet rs2 = stmt3.executeQuery(
				"select idexemplaire,idUtilisateur "+
				" FROM exemplaire,utilisateur where idexemplaire = " + idExemplaire);			
		EmpruntEnCoursDb ex = null;
		
		boolean next = rs2.next();
		
		
		if( next ) {
			int idexemplaire = rs2.getInt("idexemplaire"); // corrigé !!!
			int idutilisateur = rs2.getInt("idutilisateur"); // corrigé !!!			
			ex = new EmpruntEnCoursDb(idexemplaire,idutilisateur); //ici, mapping Objet Relationel
		}
		else {
			ex = null;
		}
		
		
		return ex;
			
	}
	public List<EmpruntEnCoursDb> findByUtilisateur(Utilisateur u) {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
