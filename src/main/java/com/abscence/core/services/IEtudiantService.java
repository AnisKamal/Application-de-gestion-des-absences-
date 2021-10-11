package com.abscence.core.services;

import java.util.List; 

import com.abscence.core.bo.Etudiant;
import com.abscence.core.bo.Inscription;

public interface IEtudiantService {
	public void create(Etudiant etudiant);
	
	public List<Etudiant>getAllEtudiant();
	
	public Etudiant ObtenirEtudiant(Integer id);
	
}
