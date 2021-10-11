package com.abscence.core.dao;

import org.springframework.stereotype.Repository;

import com.abscence.core.bo.Niveau;
import com.abscence.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class NiveauDaoImpl extends HibernateSpringGenericDaoImpl<Niveau, Integer> implements INiveauDao{

	public NiveauDaoImpl() {
		super(Niveau.class);
	}

}
