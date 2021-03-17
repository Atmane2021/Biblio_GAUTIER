package biblio.domain;

import java.util.Date;

public class Employe extends Utilisateur
{
	private String codeMatricule;
	private EnumCategorieEmploye categorieEmploye;
	
	
	public Employe(String nom, String prenom,String dateNaissance,String sexe, int idUtilisateur,String pwd, String pseudonyme, String codeMatricule, EnumCategorieEmploye categorieEmploye)
	{
		super(nom, prenom, dateNaissance,sexe, idUtilisateur,pwd, pseudonyme);
		this.codeMatricule = codeMatricule;
		this.categorieEmploye = categorieEmploye;
	}


	@Override
	public String toString() {
		return super.toString()+"  Matricule = " + codeMatricule + ", categorie de l'employe = " + categorieEmploye + ".";
	}


	public String getCodeMatricule() {
		return codeMatricule;
	}


	public void setCodeMatricule(String codeMatricule) {
		this.codeMatricule = codeMatricule;
	}


	public EnumCategorieEmploye getCategorieEmploye() {
		return categorieEmploye;
	}


	public void setCategorieEmploye(EnumCategorieEmploye categorieEmploye) {
		this.categorieEmploye = categorieEmploye;
	}
}