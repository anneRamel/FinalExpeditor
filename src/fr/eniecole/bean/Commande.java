package fr.eniecole.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Commande implements Serializable{
	
	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date dateCommande;
	private Statut statut;
	private float poidsTotal;
	private Societe societe;
	private Employe employe;
	private Map<Article, Integer> articlesCommandes;
			
	public Commande() {
		super();
		this.articlesCommandes = new HashMap<>();
	}
	
		
	public Commande(int id, Date dateCommande, Statut statut, float poidsTotal, Societe societe, Employe employe,
			Map<Article, Integer> articlesCommandes) {
		super();
		this.id = id;
		this.dateCommande = dateCommande;
		this.statut = statut;
		this.poidsTotal = poidsTotal;
		this.societe = societe;
		this.employe = employe;
		this.articlesCommandes = articlesCommandes;
	}

	public Societe getSociete() {
		return societe;
	}
	
	public void setSociete(Societe societe) {
		this.societe = societe;
	}
	
	public Employe getEmploye() {
		return employe;
	}
	
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	public Map<Article, Integer> getArticlesCommandes() {
		return articlesCommandes;
	}
	
	public void setArticlesCommandes(Map<Article, Integer> articlesCommandes) {
		this.articlesCommandes = articlesCommandes;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDateCommande() {
		return dateCommande;
	}
	
	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	
	public Statut getStatut() {
		return statut;
	}
	
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
	public float getPoidsTotal() {
		return poidsTotal;
	}
	
	public void setPoidsTotal(float poidsTotal) throws Exception {
		if(poidsTotal < 0){
			throw new Exception("Le poids doit être supérieur à 0");
		}else{
			this.poidsTotal = poidsTotal;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Commande [id=");
		builder.append(id);
		builder.append(", dateCommande=");
		builder.append(dateCommande);
		builder.append(", statut=");
		builder.append(statut);
		builder.append(", poidsTotal=");
		builder.append(poidsTotal);
		builder.append(", societe=");
		builder.append(societe);
		builder.append(", employe=");
		builder.append(employe);
		builder.append(", articlesComanndes=");
		builder.append(articlesCommandes);
		builder.append("]");
		return builder.toString();
	}

}
