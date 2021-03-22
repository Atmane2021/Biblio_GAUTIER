package biblio.control;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import biblio.dao.EmpruntEnCoursDao;
import biblio.dao.EmpruntEnCoursDb;
import biblio.dao.ExemplairesDao;
import biblio.dao.PingJdbc;
import biblio.dao.UtilisateursDao;
import biblio.domain.Adherent;
import biblio.domain.EmpruntEnCours;
import biblio.domain.Exemplaire;
import biblio.domain.Utilisateur;


public class ConsulterCtl {

	public static void main(String[] args) throws HeadlessException, IOException, NumberFormatException, SQLException {
		
		System.out.println("\n\n-------------Test 2.1 : Connection � la base avec le user Bibliothecaire-----------------------\n\n");
		int z=0, y=0;
		Properties properties = new Properties();
	      FileInputStream input = new FileInputStream("src\\biblio\\DAO\\jdbc.properties");
	      try{
	         properties.load(input);
	      }finally{
		         input.close();
		         
		  }
		if(PingJdbc.getConnectionByProperties() != null) JOptionPane.showMessageDialog(null, "Vous �tes connect�(e) � la base de donn�es de la biblioth�que avec le user : "+properties.getProperty("user"), "Etat de la connection � la base de donn�es" , JOptionPane.INFORMATION_MESSAGE);
		
		
		System.out.println("\n\n-------------Test 2.2 : Demande des objets aux DAO-----------------------\n\n");
		
		z=0;
		y=0;
		
		while(z==0) {
			
			z=JOptionPane.showConfirmDialog(null, "Voulez-vous consulter un exemplaire ? ", "Consultation d'exemplaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (z==1 ) continue;
		
		ExemplairesDao exemplaire1 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		
		String h = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire ( de 1 � 8 ): ","Recherche d'un exemplaire", JOptionPane.INFORMATION_MESSAGE);
		System.out.println("\nDemande d'exemplaire n�"+(++y)+" : "+exemplaire1.findByKey(Integer.parseInt(h)));
		
		}
		
		z=0;
		y=0;
		
		while(z==0) {
			
			z=JOptionPane.showConfirmDialog(null, "Voulez-vous consulter un utilisateur ? ", "Consultation d'utilisateur", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (z==1 ) continue;
			
			UtilisateursDao utilisateur1 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
			String k = JOptionPane.showInputDialog(null, "Entrez l'ID de l'utilisateur que vous souhaitez consulter (ex Employe = 2,3,6 ou Adherent = 1,4,5,7,8,9) : ","Recherche d'un utilisateur", JOptionPane.INFORMATION_MESSAGE); 
			System.out.println("\nDemande d'utilisateur n�"+(++y)+" : "+utilisateur1.findByKey(Integer.parseInt(k)).toString());
		
		
		}
		
		JOptionPane.showMessageDialog(null, "Au Revoir et a bient�t !", "Fin de session", JOptionPane.INFORMATION_MESSAGE);

	}
	
	public static String connectbase() throws IOException {
		Properties properties = new Properties();
	      FileInputStream input = new FileInputStream("src\\biblio\\DAO\\jdbc.properties");
	      try{
	         properties.load(input);
	      }finally{
		         input.close();
		         
		  }
	      return properties.getProperty("user");
		
	}
	
	public static String consultexemplaire(String h) throws FileNotFoundException, IOException, NumberFormatException, SQLException {
		if(!h.isBlank()) {
		ExemplairesDao exemplaire1 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		if(exemplaire1.findByKey(Integer.parseInt(h))==null ) return "Aucune r�ponse, choisissez parmi les r�ponses affich�es ci-jointes";
				return exemplaire1.findByKey(Integer.parseInt(h)).toString();
		}
		return "Renseignez le champs ID Exemplaire, s.v.p. !";
	}
	
	public static String consultutilisateur(String k) throws FileNotFoundException, IOException, NumberFormatException, SQLException {
		if(!k.isBlank()) {
		String o="";
		UtilisateursDao utilisateur1 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
		if(utilisateur1.findByKey(Integer.parseInt(k))==null ) return "Aucune r�ponse, choisissez parmi les r�ponses affich�es ci-jointes";
		o=utilisateur1.findByKey(Integer.parseInt(k)).toString();
		
		o=o+"\n\nListe des emprunts en cours : \n";
				
		EmpruntEnCoursDao eecd6 = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		for(EmpruntEnCoursDb s : eecd6.findByUtilisateur(utilisateur1.findByKey(Integer.parseInt(k)))){
			o=o+"\n ID exemplaire : "+s.getIdExemp();
		}
		return o;
		}
		return "Renseignez le champs ID Utilisateur, s.v.p. !";
		}
	
	public static String nbE() throws IOException {
		String h="";
		UtilisateursDao utilisateur7 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
		if(utilisateur7.findAll()==null) return "Aucune r�ponse, choisissez parmi les r�ponses affich�es ci-jointes";
		for(Utilisateur u : utilisateur7.findAll()){
			if ( u.getCategorieUtilisateur().contains("EMPLOYE")) h=h+u.getidUtilisateur()+" ";
		}
		
		return h;
	}
	public static String nbA() throws IOException {
		String i="";
		UtilisateursDao utilisateur6 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
		if(utilisateur6.findAll()==null) return "Aucune r�ponse, choisissez parmi les r�ponses affich�es ci-jointes";
		for(Utilisateur w : utilisateur6.findAll()){
			if ( w.getCategorieUtilisateur().equalsIgnoreCase("ADHERENT")) i=i+w.getidUtilisateur()+" ";
		}
		if(i.equals("") ) return "Aucune r�ponse, choisissez parmi les r�ponses affich�es ci-jointes";
		return i;
	}
	public static String nbL() throws IOException, SQLException {
		String j="";
		ExemplairesDao ex6 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		if(ex6.findAll()==null) return "Aucune r�ponse, choisissez parmi les r�ponses affich�es ci-jointes";
		for(Exemplaire v : ex6.findAll()){
			j=j+v.getIdExemplaire()+" ";
		}
		if(j.equals("") ) return "Aucune r�ponse, choisissez parmi les r�ponses affich�es ci-jointes";
		return j;
	}
}


