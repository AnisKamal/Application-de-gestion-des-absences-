package com.abscence.core.bo;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import java.util.Iterator;

@Entity
public class Niveau {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Integer idNiveau;
	
	private String alias;
	
	private String titre;
	
	/*
	@OneToMany(mappedBy="niveau", cascade = CascadeType.ALL)
	public Collection<Module> modules ;
	*/
	
	@OneToMany(mappedBy="inscriptions", cascade = CascadeType.ALL)
	public Collection <Inscription> inscription;

	public Integer getIdNiveau() {
		return idNiveau;
	}

	public void setIdNiveau(Integer idNiveau) {
		this.idNiveau = idNiveau;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
/*
	public Collection<Module> getModules() {
		return modules;
	}
	*/
	/*
	 public void setModules(java.util.Collection<Module> newModules) {
	      removeAllModules();
	      for (Iterator iter = newModules.iterator(); iter.hasNext();)
	         addModules((Module)iter.next());
	   }
	 
	 public Iterator getIteratorModules() {
	      if (modules == null)
	         modules = new java.util.HashSet<Module>();
	      return modules.iterator();
	   }
	
	 public void addModules(Module newModule) {
	      if (newModule == null)
	         return;
	      if (this.modules == null)
	         this.modules = new java.util.HashSet<Module>();
	      if (!this.modules.contains(newModule))
	      {
	         this.modules.add(newModule);
	         newModule.setNiveau(this);      
	      }
	   }
	 
	 public void removeModules(Module oldModule) {
	      if (oldModule == null)
	         return;
	      if (this.modules != null)
	         if (this.modules.contains(oldModule))
	         {
	            this.modules.remove(oldModule);
	            oldModule.setNiveau((Niveau)null);
	         }
	   }
	 
	 public void removeAllModules() {
	      if (modules != null)
	      {
	         Module oldModule;
	         for (Iterator iter = getIteratorModules(); iter.hasNext();)
	         {
	            oldModule = (Module)iter.next();
	            iter.remove();
	            oldModule.setNiveau((Niveau)null);
	         }
	      }
	   }
*/
	 public Collection<Inscription> getInscription() {
	      if (inscription == null)
	         inscription = new HashSet<Inscription>();
	      return inscription ;
	   }
	   
	  
	   public Iterator getIteratorNiveau() {
	      if (inscription == null)
	    	  inscription = new HashSet<Inscription>();
	      return inscription.iterator();
	   }
	   
	   
	   public void setInscription(Collection<Inscription> newInscription) {
	      removeAllInscription();
	      for (Iterator iter = newInscription.iterator(); iter.hasNext();)
	         addInscription((Inscription)iter.next());
	   }
	   
	   
	   public void addInscription(Inscription newInscription) {
	      if (newInscription == null)
	         return;
	      if (this.inscription == null)
	         this.inscription = new HashSet<Inscription>();
	      if (!this.inscription.contains(newInscription))
	      {
	         this.inscription.add(newInscription);
	         newInscription.setInscriptions(this);      
	      }
	   }
	   
	   
	   public void removeInscription(Inscription oldInscription) {
	      if (oldInscription == null)
	         return;
	      if (this.inscription != null)
	         if (this.inscription.contains(oldInscription))
	         {
	            this.inscription.remove(oldInscription);
	            oldInscription.setInscriptions((Niveau)null);
	         }
	   }
	   
	  
	   public void removeAllInscription() {
	      if (inscription != null)
	      {
	         Inscription oldInscription;
	         for (java.util.Iterator iter = getIteratorNiveau(); iter.hasNext();)
	         {
	            oldInscription = (Inscription)iter.next();
	            iter.remove();
	            oldInscription.setInscriptions((Niveau)null);
	         }
	      }
	   }
	 
	
	//public Filiere filiere;
	
	
}
