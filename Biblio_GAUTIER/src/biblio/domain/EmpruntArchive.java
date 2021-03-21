//Source file: C:\\Users\\Cedric\\Desktop\\UML-SQL\\Bibliotheque_V2\\Schemas\\EmpruntArchive.java

package biblio.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import biblio.dao.ExemplairesDao;
import biblio.dao.PingJdbc;
import biblio.dao.UtilisateursDao;


public class EmpruntArchive
{
   private LocalDate dateRestitutionEff;
   private LocalDate dateEmprunt;
   private Utilisateur utilisateur;
   private Exemplaire exemplaire;

   private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

   
   public EmpruntArchive(EmpruntEnCours ep,String dateRestitutionEff) 
   {
    setDateRestitutionEff(dateRestitutionEff);
    setExemplaire(ep.getExemplaire());
    setUtilisateur(ep.getUtilisateur());
    setDateEmprunt(ep.getDateEmprunt().toString());
   }
   
 


public EmpruntArchive(int id, String dte, String dte2, int ide, int idu) throws SQLException, FileNotFoundException, IOException {
	setDateEmprunt(dte);
	setDateRestitutionEff(dte2);
	ExemplairesDao exdao = new ExemplairesDao(PingJdbc.getConnectionByProperties());
	setExemplaire(exdao.findByKey(ide));
	UtilisateursDao utildao = new UtilisateursDao(PingJdbc.getConnectionByProperties());
	setUtilisateur(utildao.findByKey(idu));
	
}




@Override
public String toString() {
	return "EmpruntArchive [dateRestitutionEff=" + dateRestitutionEff + ", dateEmprunt=" + dateEmprunt
			+ ", utilisateur =" + utilisateur.getNom() + ", exemplaire=" + exemplaire + "]";
}




public LocalDate getDateRestitutionEff() {
	return dateRestitutionEff;
}
public void setDateRestitutionEff(String dateRestitutionEff) {
	
	this.dateRestitutionEff = LocalDate.parse(dateRestitutionEff);
}
public LocalDate getDateEmprunt() {
	return dateEmprunt;
}
public void setDateEmprunt(String dateEmprunt) {
	this.dateEmprunt = LocalDate.parse(dateEmprunt);
}
public Utilisateur getUtilisateur() {
	return utilisateur;
}
public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
}
public Exemplaire getExemplaire() {
	return exemplaire;
}
public void setExemplaire(Exemplaire exemplaire) {
	this.exemplaire = exemplaire;
}


}
