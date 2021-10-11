package com.abscence.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.abscence.core.bo.Absence;
import com.abscence.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class AbsenceDaoImpl extends HibernateSpringGenericDaoImpl<Absence, Integer> implements IAbsenceDao{

	public AbsenceDaoImpl() {
		super(Absence.class);
	}

}
