package com.abscence.core.dao;

import org.springframework.stereotype.Repository;

import com.abscence.core.bo.TypeSeance;
import com.abscence.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class TypeSeanceDaoImpl extends HibernateSpringGenericDaoImpl<TypeSeance, Integer> 
	implements ITypeSeanceDao {

	public TypeSeanceDaoImpl() {
		super(TypeSeance.class);
		// TODO Auto-generated constructor stub
	}

}
