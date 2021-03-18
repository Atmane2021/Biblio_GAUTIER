package biblio.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import biblio.dao.ExemplairesDao;
import biblio.dao.PingJdbc;

public class Exemplaire {
	private int idExemplaire;
	private LocalDate dateAchat;
	private EnumStatusExemplaire status;
	private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String isbn;
	
	public Exemplaire(int idExemplaire,String dateAchat,EnumStatusExemplaire status,String isbn) {
		this.isbn=isbn;
		this.setIdExemplaire(idExemplaire);
		this.setDateAchat(dateAchat);
		this.setStatus(status);
		
	}

	@Override
	public String toString() {
		return "Exemplaire [idExemplaire = " + idExemplaire + ", dateAchat=" + dateAchat + ", status=" + status + ", isbn=" + isbn + "]";
	}

	public String getIsbn() {
		
		return isbn;
	}

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(String dateAchat) {
		this.dateAchat = LocalDate.parse(dateAchat,df);
	}

	public int getIdExemplaire() {
		return idExemplaire;
	}

	public void setIdExemplaire(int idExemplaire) {
		this.idExemplaire = idExemplaire;
	}

	public EnumStatusExemplaire getStatus() {
		return status;
	}

	public void setStatus(EnumStatusExemplaire status) {
		this.status=status;
	}
	
	public Exemplaire findByKey( int idExemplaire){		
			try {
				ExemplairesDao exemplairedao = new ExemplairesDao(PingJdbc.getConnectionByProperties());
					return exemplairedao.findByKey(idExemplaire);
			} catch (IOException | SQLException e) {				
				e.printStackTrace();
			}			
		return null;
	}
	public List<Exemplaire> findAll(){		
		try {
			ExemplairesDao exemplairedao = new ExemplairesDao(PingJdbc.getConnectionByProperties());
				return exemplairedao.findall();
		} catch (IOException | SQLException e) {				
			e.printStackTrace();
		}			
	return null;
}
	
	
}