package com.abscence.core.services;

import java.util.List;

import com.abscence.core.bo.TypeSeance;


public interface ITypeSeanceService {
	
	public void create(TypeSeance typeSeance);
	
	public List<TypeSeance> getAllTypeSeance();
	
	public TypeSeance getTypeSeance(Integer id);

}
