package com.abscence.core.dao;

import org.springframework.stereotype.Repository;

import com.abscence.core.bo.Conversation;
import com.abscence.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class ConversationDaoImpl extends HibernateSpringGenericDaoImpl<Conversation, Integer>
implements IConversationDao{

	public ConversationDaoImpl() {
		super(Conversation.class);
		
	}

}
