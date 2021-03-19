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

		System.out.println("\n\n-------------Test 2.1 : Connection à la base avec le user Bibliothecaire-----------------------\n\n");
		Properties properties = new Properties();
	      FileInputStream input = new FileInputStream("src\\biblio\\DAO\\jdbc.properties");
	      try{
	         properties.load(input);
	      }finally{
		         input.close();
		         
		  }
		if(PingJdbc.getConnectionByProperties() != null) JOptionPane.showMessageDialog(null, "Vous êtes connecté(e) à la base de données de la bibliothèque avec le user : "+properties.getProperty("user"), "Etat de la connection à la base de données" , JOptionPane.INFORMATION_MESSAGE);
		
		
//		System.out.println("\n\n-------------Test 2.2 : Demande des objets aux DAO-----------------------\n\n");
//		
//		ExemplairesDao exemplaire1 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
//		
//		String h = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire ( de 1 à 8 ): ","Recherche d'un exemplaire", JOptionPane.INFORMATION_MESSAGE);
//		System.out.println("Première demande : "+exemplaire1.findByKey(Integer.parseInt(h)));
//		String i = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire ( de 1 à 8 ): ","Recherche d'un exemplaire", JOptionPane.INFORMATION_MESSAGE);
//		System.out.println("Deuxième demande : "+exemplaire1.findByKey(Integer.parseInt(i)));
//		UtilisateursDao utilisateur1 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
//		String k = JOptionPane.showInputDialog(null, "Entrez l'ID de l'utilisateur que vous souhaitez consulter (ex Adherent = 1,4,5,7,8,9) : ","Recherche d'un utilisateur", JOptionPane.INFORMATION_MESSAGE); 
//		System.out.println("Troisième demande : "+utilisateur1.findByKey(Integer.parseInt(k)).toString());
//		String f = JOptionPane.showInputDialog(null, "Entrez l'ID de l'utilisateur que vous souhaitez consulter (ex Employe = 2,3,6) : ","Recherche d'un utilisateur", JOptionPane.INFORMATION_MESSAGE);
//		System.out.println("Troisième demande : "+utilisateur1.findByKey(Integer.parseInt(f)).toString());
//		
		System.out.println("\n-------------Test 2.3 :Création d'un emprunt en cours pour un Employé-----------------------");
		
		UtilisateursDao utilisateur2 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
		ExemplairesDao exemplaire2 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		String a = JOptionPane.showInputDialog(null, "Entrez l'ID de l'emprunteur (ex Employe = 2,3,6) : ","Réalisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		String b = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire emprunté ( de 1 à 8 ): ","Réalisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		EmpruntEnCoursDao eecd = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		eecd.insertEmpruntEnCours(new EmpruntEnCours(utilisateur2.findByKey(Integer.parseInt(a)),exemplaire2.findByKey(Integer.parseInt(b))));
		EmpruntEnCoursDao eecd3 = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		System.out.println("Liste des emprunts en cours de l'emprunteur "+a+" : \n");
		for(EmpruntEnCoursDb v : eecd3.findByUtilisateur(utilisateur2.findByKey(Integer.parseInt(a)))) {
			System.out.println("Exemplaire id : "+v.getIdUtil()+"\n");
		}		
		
		System.out.println("\n-------------Test 2.4 :Création d'un emprunt en cours pour un Adhérent-----------------------");
		
		String c = JOptionPane.showInputDialog(null, "Entrez l'ID de l'emprunteur (ex Adherent = 1,4,5,7,8) : ","Réalisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		String d = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire emprunté ( de 1 à 8 ): ","Réalisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		EmpruntEnCoursDao eecd2 = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		eecd2.insertEmpruntEnCours(new EmpruntEnCours(utilisateur2.findByKey(Integer.parseInt(c)),exemplaire2.findByKey(Integer.parseInt(d))));		
		
		EmpruntEnCoursDao eecd4 = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		System.out.println("Liste des emprunts en cours de l'emprunteur "+c+" : \n");
		for(EmpruntEnCoursDb w : eecd4.findByUtilisateur(utilisateur2.findByKey(Integer.parseInt(c)))) {
			System.out.println("Exemplaire id : "+w.getIdUtil()+"\n");
		}
		
//		System.out.println("\n-------------Test 3.1 : Retour de tous les utilisateurs-----------------------");
//		int l = JOptionPane.showConfirmDialog(null, "Voulez-vous voir tous les utilisateurs ?","Recherche de tous les utilisateurs",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
//		if (l==0) {
//			for(Utilisateur p : utilisateur1.findAll()){
//				System.out.println(p.toString());
//			}
//						
//		}
//		
//		System.out.println("\n-------------Test 6.1 : Insérer un emprunt en cours-----------------------");
//		
//		
//		
//		System.out.println("\n-------------Test 6.2 : Trouver un emprunt en cours par l'ID exemplaire-----------------------");
//	
//		String m = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire : ","Recherche d'un emprunt en cours", JOptionPane.INFORMATION_MESSAGE);
//		
//		
//		System.out.println("\n-------------Test 6.3 : Trouver l'ensempble des emprunts en cours d'un utilisateur-----------------------");
//		int n = JOptionPane.showConfirmDialog(null, "De quel utilisateur voulez-vous voir l'ensemble des emprunts en cours ? ","Recherche de tous les emprunts en cours",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		

	}

}
