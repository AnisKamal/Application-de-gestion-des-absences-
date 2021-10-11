package com.abscence.core.bo;


import java.util.Collection; 
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class TypeSeance {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment" , strategy="increment")
	private Integer idTypeSeance;
	
	private String intule;
	
	private String alias;
	
	public Integer getIdTypeSeance() {
		return idTypeSeance;
	}

	public void setIdTypeSeance(Integer idTypeSeance) {
		this.idTypeSeance = idTypeSeance;
	}

	public String getIntule() {
		return intule;
	}

	public void setIntule(String intule) {
		this.intule = intule;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@OneToMany(mappedBy = "typeSeance", cascade = CascadeType.ALL)
	private Collection<Absence> absences;
	
	public Collection<Absence> getAbsences(){
		if(absences == null) {
			absences = new HashSet<Absence>();
		}
		return absences;
	}
	
	public Iterator getIteratorAbsences() {
		if(absences == null) {
			absences = new HashSet<Absence>();
		}
		return absences.iterator();
	}
	
	public void setAbsences(HashSet<Absence> newAbsences) {
		removeAllAbsences();
		
		for(Iterator iter = newAbsences.iterator();iter.hasNext();)
			addAbsences((Absence)iter.next());
	}
	
	public void addAbsences(Absence newAbsence) {
		if(newAbsence == null)
			return;
		if(this.absences == null)
			this.absences = new HashSet<Absence>();
		
		if(!this.absences.contains(newAbsence))
		{
			this.absences.add(newAbsence);
			newAbsence.setTypeSeance(this);
		}
	}
	
	 public void removeAbsences(Absence oldAbsence) {
	      if (oldAbsence == null)
	         return;
	      if (this.absences != null)
	         if (this.absences.contains(oldAbsence))
	         {
	            this.absences.remove(oldAbsence);
	            oldAbsence.setTypeSeance((TypeSeance)null);
	         }
	   }
	 
	 public void removeAllAbsences() {
	      if (absences != null)
	      {
	         Absence oldAbsence;
	         for (java.util.Iterator iter = getIteratorAbsences(); iter.hasNext();)
	         {
	            oldAbsence = (Absence)iter.next();
	            iter.remove();
	            oldAbsence.setTypeSeance((TypeSeance)null);
	         }
	      }
	   }
	  
	@Override
	public String toString() {
		return "TypeSeance [idTypeSeance=" + idTypeSeance + ", intule=" + intule + ", alias=" + alias + ", absences="
				+ absences + "]";
	}
}
