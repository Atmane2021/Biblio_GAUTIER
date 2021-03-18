package biblio.control;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import biblio.dao.ExemplairesDao;
import biblio.dao.PingJdbc;
import biblio.domain.Exemplaire;
import biblio.domain.Utilisateur;

public class EmprunterCtl {

	public static void main(String[] args) {

		System.out.println("-------------Test 3.1 : Retour d'un exemplaire par son ID-----------------------");
		Exemplaire exemplaire1 = null;
		String i = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire : ","Recherche d'un exemplaire", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(exemplaire1.findByKey(Integer.parseInt(i)).toString());
		
		System.out.println("-------------Test 3.2 : Retour de tous les exemplaires-----------------------");
		int j = JOptionPane.showConfirmDialog(null, "Entrez l'ID de l'exemplaire : ","Recherche d'un exemplaire",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (j==0) {
			for(Exemplaire o : exemplaire1.findAll()){
				System.out.println(o.toString());
			}
						
		}
		System.out.println("-------------Test 4.1 : Retour d'un utilisateur par son ID-----------------------");
		Utilisateur utilisateur1 = null;
		String k = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire : ","Recherche d'un exemplaire", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(utilisateur1.findByKey(Integer.parseInt(k)).toString());
		
		System.out.println("-------------Test 4.2 : Retour de tous les utilisateurs-----------------------");
		int l = JOptionPane.showConfirmDialog(null, "Voulez-vous voir tous les utilisateurs","Recherche de tous les utilisateurs",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (l==0) {
			for(Utilisateur p : utilisateur1.findAll()){
				System.out.println(p.toString());
			}
						
		}
		
		System.out.println("-------------Test 6.1 : Insérer un emprunt en cours-----------------------");
		
		
		
		System.out.println("-------------Test 6.2 : Trouver un emprunt en cours par l'ID exemplaire-----------------------");
	
		String m = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire : ","Recherche d'un emprunt en cours", JOptionPane.INFORMATION_MESSAGE);
		
		
		System.out.println("-------------Test 6.3 : Trouver l'ensempble des emprunts en cours d'un utilisateur-----------------------");
		int n = JOptionPane.showConfirmDialog(null, "De quel utilisateur voulez-vous voir l'ensemble des emprunts en cours ? ","Recherche de tous les emprunts en cours",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		

	}

}
