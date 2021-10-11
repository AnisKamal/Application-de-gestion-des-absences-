package com.abscence.core.bo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity  
public class Enseignant extends Utilisateur{
	
	private String Specialite;
	
	
	
	public String getSpecialite() {
		return Specialite;
	}

	public void setSpecialite(String specialite) {
		Specialite = specialite;
	}

	@OneToMany(mappedBy="observateur" , cascade = CascadeType.ALL)
	public Collection<Absence> absencesMarquees;
	
	
	public Collection <Absence> getAbsencesMarquees() {
		  if (absencesMarquees == null)
		     absencesMarquees = new HashSet<Absence>();
		 return absencesMarquees;
	}
	
	public Iterator getIteratorAbsencesMarquees() {
	      if (absencesMarquees == null)
	         absencesMarquees = new HashSet<Absence>();
	      return absencesMarquees.iterator();
	   }
	
	public void setAbsencesMarquees(Collection<Absence> newAbsencesMarquees) {
	      removeAllAbsencesMarquees();
	      for (Iterator iter = newAbsencesMarquees.iterator(); iter.hasNext();)
	         addAbsencesMarquees((Absence)iter.next());
	   }
	
	public void addAbsencesMarquees(Absence newAbsence) {
	      if (newAbsence == null)
	         return;
	      if (this.absencesMarquees == null)
	         this.absencesMarquees = new HashSet<Absence>();
	      if (!this.absencesMarquees.contains(newAbsence))
	      {
	         this.absencesMarquees.add(newAbsence);
	         newAbsence.setObservateur(this);      
	      }
	   }
	
	public void removeAbsencesMarquees(Absence oldAbsence) {
	      if (oldAbsence == null)
	         return;
	      if (this.absencesMarquees != null)
	         if (this.absencesMarquees.contains(oldAbsence))
	         {
	            this.absencesMarquees.remove(oldAbsence);
	            oldAbsence.setObservateur((Enseignant)null);
	         }
	   }
	
	   public void removeAllAbsencesMarquees() {
		      if (absencesMarquees != null)
		      {
		         Absence oldAbsence;
		         for (java.util.Iterator iter = getIteratorAbsencesMarquees(); iter.hasNext();)
		         {
		            oldAbsence = (Absence)iter.next();
		            iter.remove();
		            oldAbsence.setObservateur((Enseignant)null);
		         }
		      }
		   }
}
