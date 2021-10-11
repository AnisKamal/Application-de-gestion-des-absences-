package com.abscence.core.bo;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Etudiant extends Utilisateur {
	
	private String cne;
	
	private Date dateNaissance;
	
	
	public String getCne() {
		return cne;
	}

	public void setCne(String cne) {
		this.cne = cne;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	@OneToMany(mappedBy="etudiant" , cascade=CascadeType.ALL)
	public Collection<Inscription> inscriptions;
	
	public Collection<Inscription> getInscriptions(){
		 if (inscriptions == null)
	         inscriptions = new HashSet<Inscription>();
	      return inscriptions;
	}
	
	 public Iterator getIteratorInscriptions() {
	      if (inscriptions == null)
	         inscriptions = new HashSet<Inscription>();
	      return inscriptions.iterator();
	   }
	 
	 public void setInscriptions(Collection<Inscription> newInscriptions) {
	      removeAllInscriptions();
	      for (Iterator iter = newInscriptions.iterator(); iter.hasNext();)
	         addInscriptions((Inscription)iter.next());
	   }
	 
	 public void addInscriptions(Inscription newInscription) {
	      if (newInscription == null)
	         return;
	      if (this.inscriptions == null)
	         this.inscriptions = new HashSet<Inscription>();
	      if (!this.inscriptions.contains(newInscription))
	      {
	         this.inscriptions.add(newInscription);
	         newInscription.setEtudiant(this);      
	      }
	   }
	 
	 public void removeInscriptions(Inscription oldInscription) {
	      if (oldInscription == null)
	         return;
	      if (this.inscriptions != null)
	         if (this.inscriptions.contains(oldInscription))
	         {
	            this.inscriptions.remove(oldInscription);
	            oldInscription.setEtudiant((Etudiant)null);
	         }
	   }
	 
	 
	 public void removeAllInscriptions() {
	      if (inscriptions != null)
	      {
	         Inscription oldInscription;
	         for (java.util.Iterator iter = getIteratorInscriptions(); iter.hasNext();)
	         {
	            oldInscription = (Inscription)iter.next();
	            iter.remove();
	            oldInscription.setEtudiant((Etudiant)null);
	         }
	      }
	   }

	 
}
