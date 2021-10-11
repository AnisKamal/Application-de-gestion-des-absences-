package com.abscence.core.bo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Message {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Integer idMessage;
	
	private String texte;
	
	private Date dateHeure;

	@ManyToOne 
	@JoinColumn(name="idConversation")
	public Conversation conversation;
	
	/*
	public Compte expediteur;
	
	public Compte Destinataire;
	*/
	
	public Integer getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Integer idMessage) {
		this.idMessage = idMessage;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Date getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(Date dateHeure) {
		this.dateHeure = dateHeure;
	}
	
	 public Conversation getConversation() {
	      return conversation;
	   }
	 
	 public void setConversation(Conversation newConversation) {
	      if (this.conversation == null || !this.conversation.equals(newConversation))
	      {
	         if (this.conversation != null)
	         {
	            Conversation oldConversation = this.conversation;
	            this.conversation = null;
	            oldConversation.removeMessages(this);
	         }
	         if (newConversation != null)
	         {
	            this.conversation = newConversation;
	            this.conversation.addMessages(this);
	         }
	      }
	   }
	 
	 
}

