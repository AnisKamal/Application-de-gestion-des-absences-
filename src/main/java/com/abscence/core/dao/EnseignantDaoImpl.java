package com.abscence.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.abscence.core.bo.Enseignant;
import com.abscence.genericdao.HibernateSpringGenericDaoImpl;

@Repository 
public class EnseignantDaoImpl extends HibernateSpringGenericDaoImpl <Enseignant, Integer>
implements IEnseignantDao
{
	public EnseignantDaoImpl() {
		super(Enseignant.class);
		// TODO Auto-generated constructor stub
	}

}
