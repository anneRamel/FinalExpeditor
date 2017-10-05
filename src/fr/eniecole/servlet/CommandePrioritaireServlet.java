package fr.eniecole.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eniecole.bean.Commande;
import fr.eniecole.bean.Employe;
import fr.eniecole.bean.Statut;
import fr.eniecole.dal.CommandeDAO;

/**
 * Servlet implementation class CommandePrioritaireServlet
 */
public class CommandePrioritaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommandePrioritaireServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Employe employe = (Employe) request.getSession().getAttribute("user");
		Employe employe = new Employe();
		employe.setId(1);
		
		try {
			Commande commande = new CommandeDAO().getCommande();
			commande = new CommandeDAO().getDetailCommande(commande);
			commande.setEmploye(employe);
			commande.setStatut(Statut.EN_COURS_DE_TRAITEMENT);
			new CommandeDAO();
			CommandeDAO.modifier(commande);
			request.getSession().setAttribute("commandeEnCours", commande);
			RequestDispatcher rd = null;
			rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/employe/EmployeCommande.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imprimer = request.getParameter("Imprimer");
		if(imprimer != null){
			float poids = Float.parseFloat(request.getParameter("poidsTotals"));
			Commande commandeEnCours = (Commande) request.getSession().getAttribute("commandeEnCours");
			commandeEnCours.setStatut(Statut.TRAITEE);
        	try {
				commandeEnCours.setPoidsTotal(poids);
				CommandeDAO.modifier(commandeEnCours);
				RequestDispatcher rd = null;
				rd = getServletContext().getRequestDispatcher("/WEB-INF/PDF/BonLivraison.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
