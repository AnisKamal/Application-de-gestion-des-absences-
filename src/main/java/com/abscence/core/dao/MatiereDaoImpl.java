package com.abscence.core.dao;

import org.springframework.stereotype.Repository;

import com.abscence.core.bo.Matiere;
import com.abscence.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class MatiereDaoImpl extends HibernateSpringGenericDaoImpl<Matiere, Integer >
implements IMatiereDao{

	public MatiereDaoImpl() {
		super(Matiere.class);
		// TODO Auto-generated constructor stub
	}

}
