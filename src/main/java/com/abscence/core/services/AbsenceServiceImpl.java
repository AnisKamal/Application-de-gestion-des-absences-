package com.abscence.core.services;

import java.util.List;  

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abscence.core.bo.Absence;
import com.abscence.core.dao.IAbsenceDao;

@Service
@Transactional
public class AbsenceServiceImpl implements IAbsenceService{

	@Autowired
	private IAbsenceDao absenceDao;
	
	public void addAbsence(Absence absence) {
		absenceDao.create(absence);
	}

	public Absence getAbsence(Integer id) {
		return absenceDao.findById(id);
	}
	
	
	public void deleteAbsence(Integer id) {
		absenceDao.delete(id);
	}
	
}
