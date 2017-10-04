package fr.eniecole.utils;

import fr.eniecole.bean.Statut;

public class ManipEnumStatut {

	public static Statut StringToEnum(String chaine){
		Statut statut = null;
		chaine = chaine.trim();
		if(chaine.equals("A TRAITER")) {
			statut = Statut.A_TRAITER;
		}else if (chaine.equals("EN COURS DE TRAITEMENT")){
			statut = Statut.EN_COURS_DE_TRAITEMENT;
		}else if (chaine.equals("TRAITEE")){
			statut = Statut.TRAITEE;
		}
		return statut;
	}
	
	public static String EnumToString(Statut statut){
		String resultat = null;
		if(statut.equals(Statut.A_TRAITER)){
			resultat = "A TRAITER";
		}else if (statut.equals(Statut.EN_COURS_DE_TRAITEMENT)){
			resultat = "EN COURS DE TRAITEMENT";
		}else if(statut.equals(Statut.TRAITEE)){
			resultat = "TRAITEE";
		}
		return resultat;
	}
}
