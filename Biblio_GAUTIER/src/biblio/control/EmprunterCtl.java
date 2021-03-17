package biblio.control;

import biblio.dao.ExemplairesDAO;
import biblio.dao.PingJdbc;

public class EmprunterCtl {

	public static void main(String[] args) {

		// TestDeBase 1
				ExemplairesDAO exemplairedao = new ExemplairesDAO(PingJdbc.getConnectionByProperties());

	}

}
