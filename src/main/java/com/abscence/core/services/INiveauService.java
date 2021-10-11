package com.abscence.core.services;

import java.util.List;

import com.abscence.core.bo.Niveau;

public interface INiveauService {
	public List<Niveau> getAllNiveau();
	
	public void create(Niveau niveau);
	
	public Niveau getNiveau(Integer id);
}
