package com.abscence.core.services;

import java.util.List;  

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abscence.core.bo.Etudiant;
import com.abscence.core.bo.Inscription;
import com.abscence.core.dao.IEtudiantDao;

@Service
@Transactional
public class EtudiantServiceImpl implements IEtudiantService{
	
	@Autowired
	private IEtudiantDao etudiantDao;
	
	
	public void create(Etudiant etudiant) {
		etudiantDao.create(etudiant);
	}

	public List<Etudiant> getAllEtudiant() {
		
		return etudiantDao.getAll();
	}



	
	public Etudiant ObtenirEtudiant(Integer id) {
		
		return etudiantDao.findById(id);
	}
	
}
