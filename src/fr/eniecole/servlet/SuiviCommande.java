package fr.eniecole.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eniecole.bean.Commande;
import fr.eniecole.bean.Employe;
import fr.eniecole.dal.CommandeDAO;

/**
 * Servlet implementation class SuiviCommande
 */
public class SuiviCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommandeDAO dao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuiviCommande() {
        super();
        dao = new CommandeDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		List<Commande> listeCommandes = null;
		Map<Employe, Integer> nbCommandes = null;
		
		try {
			listeCommandes = dao.getListeCommandes();
			nbCommandes = dao.getNbCommandesParEmploye();
			if(listeCommandes.size()== 0){
				request.setAttribute("erreur", "Pas de commandes � traiter");
			}else{
				request.setAttribute("liste", listeCommandes);
				request.setAttribute("nbCommandes", nbCommandes);
			}
			rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/manager/suiviCommande.jsp");
			System.out.println(listeCommandes);
			System.out.println(nbCommandes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rd != null){
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
