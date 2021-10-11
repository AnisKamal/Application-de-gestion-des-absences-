package com.abscence.core.services;

import java.util.ArrayList;  
import java.util.Iterator;
import java.util.List; 

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abscence.core.bo.Etudiant;
import com.abscence.core.bo.Inscription;
import com.abscence.core.dao.IInscriptionDao;


@Service
@Transactional
public class InscriptionServiceImpl implements IInscriptionService{

	@Autowired 
	private IInscriptionDao inscriptionDao;
	

	public void create(Inscription inscription) {
		inscriptionDao.create(inscription);
	}
	
	
	public List<Inscription> getListInscriptionByYear(Integer annee) {
//		Integer id = Integer.parseInt(idNiveau);
//		Integer ans ;
//		List<Etudiant> et = new ArrayList<Etudiant>(); 
//		List<Inscription> in = new ArrayList<Inscription>();
//		in = inscriptionDao.getEntityByColValue("Inscription", "id_niveau", id)  ;
//		for(int i = 0; i < in.size(); i++) {	
//			if( in.get(i).getAnnee().equals(annee)) {
//				et.add(in.get(i).getEtudiant());
//			}
//		}
		
		String Annee = annee.toString();
		
		 return inscriptionDao.getEntityByColValue("Inscription", "annee", annee);
		
}


	public Inscription getInscription(Integer id) {
		
		return inscriptionDao.findById(id);
	}
}
