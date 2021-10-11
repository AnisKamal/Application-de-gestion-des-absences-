package com.abscence.core.services;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abscence.core.bo.Enseignant;
import com.abscence.core.dao.IEnseignantDao;

@Service
@Transactional 
public class EnseignantServiceImpl implements IEnseignantService{

	@Autowired
	private IEnseignantDao enseignantDao;
	
	
	public void create(Enseignant enseignant) {
		enseignantDao.create(enseignant);
	}

}
