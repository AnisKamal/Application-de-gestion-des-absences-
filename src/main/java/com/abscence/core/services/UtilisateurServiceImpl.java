package com.abscence.core.services;

import java.util.List; 

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abscence.core.bo.Utilisateur;
import com.abscence.core.dao.IUtilisateurDao;

@Service
@Transactional
public class UtilisateurServiceImpl implements IUtilisateurService{

	@Autowired
	private IUtilisateurDao utilisateurDao;
	
	
	
	public void create(Utilisateur utilisateur) {
		utilisateurDao.create(utilisateur);
	}



	
	public List<Utilisateur> getAllUtilisateur() {
		return utilisateurDao.getAll();
	}

}
