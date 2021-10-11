package com.abscence.core.services;

import java.util.List; 

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abscence.core.bo.Niveau;
import com.abscence.core.dao.INiveauDao;

@Service
@Transactional
public class NiveauServiceImpl implements INiveauService{

	@Autowired
	private INiveauDao niveauDao;
	
	public List<Niveau> getAllNiveau() {	
		return niveauDao.getAll();
	}

	public void create(Niveau niveau) {
		 niveauDao.create(niveau);
	}

	
	public Niveau getNiveau(Integer id) {
		return niveauDao.findById(id);
	}
}
