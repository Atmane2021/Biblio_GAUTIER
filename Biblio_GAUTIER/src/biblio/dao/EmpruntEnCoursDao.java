package biblio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import biblio.domain.EmpruntEnCours;
import biblio.domain.Utilisateur;

public class EmpruntEnCoursDao {
	Connection cnx3 = null;
	public EmpruntEnCoursDao(Connection cnx3) {
		this.cnx3=cnx3;
	}
	
	public boolean insertEmpruntEnCours( EmpruntEnCours emprunt ) throws SQLException {
		
		Statement stmt = cnx3.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT STATUS FROM EXEMPLAIRE WHERE idexemplaire ="+emprunt.getExemplaire().getIdExemplaire());
		rs.next();
		if (rs.getString(1).equalsIgnoreCase("DISPONIBLE")) {
		
			PreparedStatement pstmt = cnx3.prepareStatement("INSERT INTO EMPRUNTENCOURS VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD'))");
			pstmt.setInt(1,emprunt.getExemplaire().getIdExemplaire());
			pstmt.setInt(2,emprunt.getUtilisateur().getidUtilisateur());
			pstmt.setDate(3,Date.valueOf(emprunt.getDateEmprunt()));
			pstmt.executeUpdate();
			rs.close();
			stmt.close();
			pstmt.close();
			cnx3.close();
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Désolé mais l'exemplaire demandé est "+rs.getString(1), "Disponibilité de l'exemplaire demandé", JOptionPane.WARNING_MESSAGE);
			rs.close();
			stmt.close();
			cnx3.close();
			return false;
		}
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
	
	
	public List<EmpruntEnCoursDb> findByUtilisateur(Utilisateur u) throws SQLException {
		Statement stmt4 = cnx3.createStatement();
		List<EmpruntEnCoursDb> listeEmpruntEnCoursDb = new ArrayList<EmpruntEnCoursDb>();
		ResultSet rs4 = stmt4.executeQuery("select * FROM empruntencours where idutilisateur = "+u.getidUtilisateur());			
		while( rs4.next()){
			
			int idexemplaire = rs4.getInt(1);
			int idutilisateur = rs4.getInt(2);

			EmpruntEnCoursDb ex2 = new EmpruntEnCoursDb(idexemplaire,idutilisateur);//mapping Objet Relationel
			listeEmpruntEnCoursDb.add(ex2);
			
		}
		
		return listeEmpruntEnCoursDb;
	}
	
	

	

}
