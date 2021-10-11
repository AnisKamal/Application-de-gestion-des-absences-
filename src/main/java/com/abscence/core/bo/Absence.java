package com.abscence.core.bo;

import java.util.*; 

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Absence {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Integer idAbsence;
	   
	private Date dateHeureDebutAbsence;
	
	private Date dateHeureFinAbsence;
	
	private Integer etat;
	
	private String typeSaisie;
	
//	public Collection<PieceJustificative> pieceJustificative;

	@ManyToOne
	@JoinColumn(name="id_inscription")
	public Inscription inscription;
	
	@ManyToOne
	@JoinColumn(name="id_typeSeance")
	public TypeSeance typeSeance;
//	
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
    public Enseignant observateur;

	public Integer getIdAbsence() {
		return idAbsence;
	}

	public void setIdAbsence(Integer idAbsence) {
		this.idAbsence = idAbsence;
	}

	public Date getDateHeureDebutAbsence() {
		return dateHeureDebutAbsence;
	}

	public void setDateHeureDebutAbsence(Date dateHeureDebutAbsence) {
		this.dateHeureDebutAbsence = dateHeureDebutAbsence;
	}

	public Date getDateHeureFinAbsence() {
		return dateHeureFinAbsence;
	}

	public void setDateHeureFinAbsence(Date dateHeureFinAbsence) {
		this.dateHeureFinAbsence = dateHeureFinAbsence;
	}

	public Integer getEtat() {
		return etat;
	}

	public void setEtat(Integer etat) {
		this.etat = etat;
	}

	public String getTypeSaisie() {
		return typeSaisie;
	}

	public void setTypeSaisie(String typeSaisie) {
		this.typeSaisie = typeSaisie;
	}

//	public Collection<PieceJustificative> getPieceJustificative() {
//		return pieceJustificative;
//	}
//
//	public void setPieceJustificative(Collection<PieceJustificative> pieceJustificative) {
//		this.pieceJustificative = pieceJustificative;
//	}
//
	public Inscription getInscription() {
		return inscription;
	}
//
	public void setInscription(Inscription newInscription) {
		if(this.inscription == null || !this.inscription.equals(newInscription))
		{
			if(this.inscription != null)
			{
				Inscription oldInscription = this.inscription;
				this.inscription = null;
				oldInscription.removeAbsences(this);
			}
			if(newInscription != null)
			{
				this.inscription = newInscription;
				this.inscription.addAbsences(this);
			}
		}
	}

	public TypeSeance getTypeSeance() {
		return typeSeance;
	}
	
	
	
	public void setTypeSeance(TypeSeance newTypeSeance) {
		
		if(this.typeSeance == null || !this.typeSeance.equals(newTypeSeance))
		{
			if(this.typeSeance != null)
			{
				TypeSeance oldTypeSeance = this.typeSeance;
				this.typeSeance = null;
				oldTypeSeance.removeAbsences(this);
			}
			if (newTypeSeance != null)
	         {
	            this.typeSeance = newTypeSeance;
	            this.typeSeance.addAbsences(this);
	         }
		} 
		
		this.typeSeance = newTypeSeance ; 
	}

	public Enseignant getObservateur() {
		return observateur;
	}

	public void setObservateur(Enseignant newEnseignant) {
		if (this.observateur == null || !this.observateur.equals(newEnseignant))
		{
			   if (this.observateur != null)
		         {
		            Enseignant oldEnseignant = this.observateur;
		            this.observateur = null;
		            oldEnseignant.removeAbsencesMarquees(this);
		         }
		         if (newEnseignant != null)
		         {
		            this.observateur = newEnseignant;
		            this.observateur.addAbsencesMarquees(this);
		         }
		}
	}
	
	
	@Override
	public String toString() {
		return "Absence [idAbsence=" + idAbsence + ", dateHeureDebutAbsence=" + dateHeureDebutAbsence
				+ ", dateHeureFinAbsence=" + dateHeureFinAbsence + ", etat=" + etat + ", typeSaisie=" + typeSaisie
				+ "]";
	}

}
