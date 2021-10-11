package com.abscence.core.services;

import java.util.List;

import com.abscence.core.bo.Absence;

public interface IAbsenceService {
	
	public void addAbsence(Absence absence);
	
	public Absence getAbsence(Integer id);
	
	public void deleteAbsence(Integer id);
	
}
