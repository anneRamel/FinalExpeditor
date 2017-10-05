package fr.eniecole.utils;

import fr.eniecole.bean.Employe_Manager_Enum;

public class ManipEnumEmploye {

	public static Employe_Manager_Enum StringToEnum(String chaine){
		Employe_Manager_Enum statut = null;
		chaine = chaine.trim();
		if(chaine.equals("manager")) {
			statut = Employe_Manager_Enum.manager;
		}else if (chaine.equals("employe")){
			statut = Employe_Manager_Enum.employe;
		}
		return statut;
	}
	
	public static String EnumToString(Employe_Manager_Enum statut){
		String resultat = null;
		if(statut.equals(Employe_Manager_Enum.manager)){
			resultat = "manager";
		}else if (statut.equals(Employe_Manager_Enum.employe)){
			resultat = "employe";
		}
		return resultat;
	}
}
