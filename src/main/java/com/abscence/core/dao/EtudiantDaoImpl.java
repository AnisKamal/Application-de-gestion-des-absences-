package com.abscence.core.dao;

import org.springframework.stereotype.Repository;

import com.abscence.core.bo.Etudiant;
import com.abscence.genericdao.HibernateSpringGenericDaoImpl;


@Repository
public class EtudiantDaoImpl extends HibernateSpringGenericDaoImpl<Etudiant, Integer>
implements IEtudiantDao{
	
	public EtudiantDaoImpl() {
		super(Etudiant.class);
	}
	
}
