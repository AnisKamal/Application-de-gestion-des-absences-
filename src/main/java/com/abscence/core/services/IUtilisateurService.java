package com.abscence.core.services;

import java.util.List;

import com.abscence.core.bo.Utilisateur;

public interface IUtilisateurService {
	public void create(Utilisateur utilisateur);
	
	public List<Utilisateur>getAllUtilisateur();
}
