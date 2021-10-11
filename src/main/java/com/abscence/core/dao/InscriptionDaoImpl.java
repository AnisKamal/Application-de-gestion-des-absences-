package com.abscence.core.dao;

import org.springframework.stereotype.Repository;

import com.abscence.core.bo.Inscription;
import com.abscence.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class InscriptionDaoImpl extends HibernateSpringGenericDaoImpl<Inscription, Integer>
implements IInscriptionDao
{

	public InscriptionDaoImpl() {
		super(Inscription.class);
	}
	
}
