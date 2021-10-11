package com.abscence.core.services;

import java.util.List; 

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abscence.core.bo.TypeSeance;
import com.abscence.core.dao.ITypeSeanceDao;

@Service
@Transactional 
public class TypeSeanceServiceImpl implements ITypeSeanceService {

	@Autowired
	private ITypeSeanceDao typeSeanceDao ; 
	
	
	public void create(TypeSeance typeSeance) {
		
		typeSeanceDao.create(typeSeance);		
	}


	
	public List<TypeSeance> getAllTypeSeance() {
		return typeSeanceDao.getAll();
	}


	public TypeSeance getTypeSeance(Integer id) {
		
		return typeSeanceDao.findById(id);
	}

}
