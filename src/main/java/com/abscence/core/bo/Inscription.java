package com.abscence.core.bo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Inscription {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Integer idInscription;
	
	private Integer annee ; 
	
	private Integer etat ;
	
	@ManyToOne
	@JoinColumn(name="id_niveau")
	public Niveau inscriptions;
	
	@ManyToOne
	@JoinColumn(name="id_etudiant")
	public Etudiant etudiant;
	
	
	@OneToMany(mappedBy = "inscription", cascade=CascadeType.ALL)
	public Collection<Absence> absences ;
	
	
	public Integer getIdInscription() {
		return idInscription;
	}

	public void setIdInscription(Integer idInscription) {
		this.idInscription = idInscription;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public Integer getEtat() {
		return etat;
	}

	public void setEtat(Integer etat) {
		this.etat = etat;
	} 
	
	public Collection <Absence> getAbsences() {
		if(absences == null)
			absences = new HashSet<Absence>();
		return absences;
	}
	
	public Iterator getIteratorAbsences() {
		if(absences == null)
			absences = new  HashSet<Absence>();
		return absences.iterator();
	}
	
	   public void setAbsences(java.util.Collection<Absence> newAbsences) {
		   removeAllAbsences();
		   for (Iterator iter = newAbsences.iterator(); iter.hasNext();)
		         addAbsences((Absence)iter.next());
		   }
	   
	   public void addAbsences(Absence newAbsence) {
		      if (newAbsence == null)
		         return;
		      if (this.absences == null)
		         this.absences = new HashSet<Absence>();
		      if (!this.absences.contains(newAbsence))
		      {
		         this.absences.add(newAbsence);
		         newAbsence.setInscription(this);      
		      }
		   }
	   
	   public void removeAbsences(Absence oldAbsence) {
		      if (oldAbsence == null)
		         return;
		      if (this.absences != null)
		         if (this.absences.contains(oldAbsence))
		         {
		            this.absences.remove(oldAbsence);
		            oldAbsence.setInscription((Inscription)null);
		         }
		   }
	   
	   public void removeAllAbsences() {
		      if (absences != null)
		      {
		         Absence oldAbsence;
		         for (Iterator iter = getIteratorAbsences(); iter.hasNext();)
		         {
		            oldAbsence = (Absence)iter.next();
		            iter.remove();
		            oldAbsence.setInscription((Inscription)null);
		         }
		      }
		   }
	   
	   public Etudiant getEtudiant() {
		   return this.etudiant;
	   }
	   
	   public void setEtudiant(Etudiant newEtudiant) {
		   if (this.etudiant == null || !this.etudiant.equals(newEtudiant))
		      {
		         if (this.etudiant != null)
		         {
		            Etudiant oldEtudiant = this.etudiant;
		            this.etudiant = null;
		            oldEtudiant.removeInscriptions(this);
		         }
		         if (newEtudiant != null)
		         {
		            this.etudiant = newEtudiant;
		            this.etudiant.addInscriptions(this);
		         }
		      }
		   }
	   
	   public Niveau getInscriptions() {
		      return inscriptions;
		   }
		   
		  
	   public void setInscriptions(Niveau newNiveau) {
		      if (this.inscriptions == null || !this.inscriptions.equals(newNiveau))
		      {
		         if (this.inscriptions != null)
		         {
		            Niveau oldNiveau = this.inscriptions;
		            this.inscriptions = null;
		            oldNiveau.removeInscription(this);
		         }
		         if (newNiveau != null)
		         {
		            this.inscriptions = newNiveau;
		            this.inscriptions.addInscription(this);
		         }
		      }
		   }
}
