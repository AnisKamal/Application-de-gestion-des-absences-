package com.abscence.core.dao;

import org.springframework.stereotype.Repository;

import com.abscence.core.bo.Message;
import com.abscence.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class MessageDaoImpl extends HibernateSpringGenericDaoImpl <Message, Integer>
implements IMessageDao{

	public MessageDaoImpl() {
		super(Message.class);
	}
	
}
