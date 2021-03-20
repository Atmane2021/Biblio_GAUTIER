package biblio.control;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import biblio.dao.ExemplairesDao;
import biblio.dao.PingJdbc;
import biblio.dao.UtilisateursDao;

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

}