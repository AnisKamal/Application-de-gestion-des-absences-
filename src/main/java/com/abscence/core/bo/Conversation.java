package com.abscence.core.bo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Conversation {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Integer idConversation;
	
	private String type;
	
	private String titre;
	
	private Integer etat;
	
	@OneToMany(mappedBy="conversation" , cascade = CascadeType.ALL)
	private Collection <Message> messages;

	public Integer getIdConversation() {
		return idConversation;
	}

	public void setIdConversation(Integer idConversation) {
		this.idConversation = idConversation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Integer getEtat() {
		return etat;
	}

	public void setEtat(Integer etat) {
		this.etat = etat;
	}
	
	public Collection <Message> getMessages(){
		if(messages == null)
			messages = new HashSet<Message>();
		return messages;
	}
	
	 public Iterator getIteratorMessages() {
	      if (messages == null)
	         messages = new HashSet<Message>();
	      return messages.iterator();
	   }
	
	 public void setMessages(Collection<Message> newMessages) {
	      removeAllMessages();
	      for (Iterator iter = newMessages.iterator(); iter.hasNext();)
	         addMessages((Message)iter.next());
	   }
	 
	 public void addMessages(Message newMessage) {
	      if (newMessage == null)
	         return;
	      if (this.messages == null)
	         this.messages = new HashSet<Message>();
	      if (!this.messages.contains(newMessage))
	      {
	         this.messages.add(newMessage);
	         newMessage.setConversation(this);      
	      }
	   }
	 
	 public void removeMessages(Message oldMessage) {
	      if (oldMessage == null)
	         return;
	      if (this.messages != null)
	         if (this.messages.contains(oldMessage))
	         {
	            this.messages.remove(oldMessage);
	            oldMessage.setConversation((Conversation)null);
	         }
	   }
	 
	 public void removeAllMessages() {
	      if (messages != null)
	      {
	         Message oldMessage;
	         for (Iterator iter = getIteratorMessages(); iter.hasNext();)
	         {
	            oldMessage = (Message)iter.next();
	            iter.remove();
	            oldMessage.setConversation((Conversation)null);
	         }
	      }
	   }
}
