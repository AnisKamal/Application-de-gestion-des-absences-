package com.abscence.core.services;

import java.util.List;

import com.abscence.core.bo.Etudiant;
import com.abscence.core.bo.Inscription;

public interface IInscriptionService {
	public void create(Inscription inscription);
	
	public List<Inscription> getListInscriptionByYear(Integer year);
	
}
