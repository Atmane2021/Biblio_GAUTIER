package biblio.control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

import javax.swing.JOptionPane;

import biblio.dao.EmpruntEnCoursDao;
import biblio.dao.EmpruntEnCoursDb;
import biblio.dao.ExemplairesDao;
import biblio.dao.PingJdbc;
import biblio.dao.UtilisateursDao;
import biblio.domain.EmpruntEnCours;
import biblio.domain.Exemplaire;
import biblio.domain.Utilisateur;

public class EmprunterCtl {

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {

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
		
		
		System.out.println("\n-------------Test 2.3 :Cr�ation d'un emprunt en cours pour un Employ� ou un Adh�rent avec r�gles m�tier-----------------------");
		
		
		z=0;
		y=0;
		
		while(z==0) {
		
			z=JOptionPane.showConfirmDialog(null, "Voulez-vous faire l'emprunt d'un exemplaire ? ", "Emprunt d'exemplaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (z==1 ) continue;
			
		UtilisateursDao utilisateur2 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
		ExemplairesDao exemplaire2 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		String a = JOptionPane.showInputDialog(null, "Entrez l'ID de l'emprunteur (ex Employe = 2,3,6 ou Adherent = 1,4,5,7,8) : ","R�alisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		String b = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire emprunt� ( de 1 � 8 ): ","R�alisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		EmpruntEnCoursDao eecd = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		eecd.insertEmpruntEnCours(new EmpruntEnCours(utilisateur2.findByKey(Integer.parseInt(a)),exemplaire2.findByKey(Integer.parseInt(b))));
		EmpruntEnCoursDao eecd3 = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		System.out.println("\nListe des emprunts en cours de l'emprunteur "+a+" : \n");
		for(EmpruntEnCoursDb v : eecd3.findByUtilisateur(utilisateur2.findByKey(Integer.parseInt(a)))) {
			System.out.println("Exemplaire id : "+v.getIdUtil()+"\n");
		}
		
		ExemplairesDao eecd6 = new ExemplairesDao(PingJdbc.getConnectionByProperties());

		System.out.println("\nEtat de l'exemplaire emprunt� : " + eecd6.findByKey(Integer.parseInt(b)).toString());
					
		}
		
		System.out.println("\n-------------Test 2.4 :Retour d'un emprunt en cours pourun Employ� ou un Adh�rent-----------------------");
		
		z=0;
		y=0;
		
		while(z==0) {
			
			z=JOptionPane.showConfirmDialog(null, "Voulez-vous faire la restitution d'un exemplaire ? ", "Restitution d'exemplaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (z==1 ) continue;
		
		ExemplairesDao exemplaire3 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		String c = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire restitu� ( de 1 � 8 ): ","Retour d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		EmpruntEnCoursDao eecd4 = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		eecd4.removeEmpruntEnCours(Integer.parseInt(c));

		ExemplairesDao eecd5 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		System.out.println("\nListe des exemplaires disponibles : \n");
		for(Exemplaire w : eecd5.findAll()) {
			if ( w.getStatus().toString().equalsIgnoreCase("DISPONIBLE")) System.out.println("Exemplaire id : "+w+"\n");
		}
		
		z=JOptionPane.showConfirmDialog(null, "Voulez-vous continuer la restitution d'exemplaire ? ", "Restitution d'exemplaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		}
		
		JOptionPane.showMessageDialog(null, "Au Revoir et a bient�t !", "Fin de session", JOptionPane.INFORMATION_MESSAGE);
		

	}

}
