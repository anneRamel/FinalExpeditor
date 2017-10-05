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
	private int[] nbCommandesParEmploye;
	private String[] nomEmploye;
       
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
		Employe employe = (Employe) request.getSession().getAttribute("user");
		
		try {
			listeCommandes = dao.getListeCommandes();
			nbCommandes = dao.getNbCommandesParEmploye();
			getTableau(nbCommandes);
			if(listeCommandes.size()== 0){
				request.setAttribute("erreur", "Pas de commandes à traiter");
			}else{
				request.setAttribute("liste", listeCommandes);
				request.setAttribute("nbCommandes", nbCommandes);
				request.setAttribute("data", nbCommandesParEmploye);
				request.setAttribute("label", nomEmploye);
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
		doGet(request, response);
	}
	
	private void getTableau(Map<Employe, Integer> nbCommandes){
		int index = 0;
		int tailleMap = nbCommandes.size();
		nomEmploye = new String[tailleMap];
		nbCommandesParEmploye = new int[tailleMap];
		for(Map.Entry<Employe, Integer> entry : nbCommandes.entrySet()){
			nomEmploye[index] = entry.getKey().getNom();
			nbCommandesParEmploye[index] = entry.getValue();
			index ++;
		}
				
	}

}
