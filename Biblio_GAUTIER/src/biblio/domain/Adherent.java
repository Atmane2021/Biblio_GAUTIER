package biblio.domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Adherent extends Utilisateur
{
	private String telephone;
	private int nbMaxPrets = 3;
	private int dureeMaxPrets =15;
		
	public Adherent(int idUtilisateur,String nom, String prenom,String pwd,String pseudonyme,String dateNaissance,String sexe,String cat, String telephone)
	{
		super(nom, prenom, dateNaissance,sexe, idUtilisateur,pwd, pseudonyme);
		this.telephone = telephone;
		this.dureeMaxPrets = dureeMaxPrets;
	}

	
	@Override
	public String toString() {
		return "Nom = " + getNom() + ", Prenom = " + getPrenom()+ " numero de telephone = " + telephone + ", id de l'utilisateur = " + getidUtilisateur() + ", pseudo = " + getpseudonyme() + ", genre = "
				+ getSexe() + ".";
	}


	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getNbMaxPrets() {
		return nbMaxPrets;
	}

	public void setNbMaxPrets(int nbMaxPrets) {
		this.nbMaxPrets = nbMaxPrets;
	}

	public int getDureeMaxPrets() {
		return dureeMaxPrets;
	}

	public void setDureeMaxPrets(int dureeMaxPrets) {
		this.dureeMaxPrets = dureeMaxPrets;
	}

	public boolean isConditionsPretAcceptees()
	{
		
		if((getNbEmpruntsEnCours()>=nbMaxPrets) )
		{
			System.out.println("Vous avez dépassé les 3 emprunts autorisés !!");
			return false; 
		}
		else if(( this.getNbRetards()>dureeMaxPrets ))
		{
			System.out.println("Vous ne pouvez emprunter de nouveau, car un de vos emprunt est en retard");
			return false; 
		}
		return true;
	}

	public int getNbRetards()
	{	if( getNbEmpruntsEnCours()>0 ) {
		for(int i=0 ;  i<getEmpruntenCours().size() ; i++) {
		long dif = ChronoUnit.DAYS.between(LocalDate.parse(getEmpruntenCours().get(i).getDateEmprunt().toString()), LocalDate.now());		
		if( (int) dif > 15 && getEmpruntenCours().get(i).getUtilisateur().getidUtilisateur()==this.getidUtilisateur()) return (int) dif;
		}
	}
		return 0;
		
	}
	
	@Override
	public void addEmpruntEnCours (EmpruntEnCours ep) throws Exception
	{
		if(isConditionsPretAcceptees()==true)
		{
			emprunt.add(ep);
			System.out.println("emprunt autorisé");
		}
		else System.out.println("REFUSE");
		
	}
	
	public static void main(String[] args) {
		Adherent az = new Adherent("Moris", "Eléanor", "20/01/2021", "Femme", 1,"123quatre","Premier1", "0643 55 73 29");
		
	}
}