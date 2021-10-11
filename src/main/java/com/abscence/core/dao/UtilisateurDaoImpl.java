package com.abscence.core.dao;

import org.springframework.stereotype.Repository;

import com.abscence.core.bo.Utilisateur;
import com.abscence.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class UtilisateurDaoImpl extends HibernateSpringGenericDaoImpl<Utilisateur, Integer>
implements IUtilisateurDao
{
	public UtilisateurDaoImpl() {
		super(Utilisateur.class);
	}
}
