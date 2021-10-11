package com.abscence.core.services;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abscence.core.bo.Message;
import com.abscence.core.dao.IMessageDao;

@Service
@Transactional 
public class MessageServiceImpl implements IMessageService{
	
	@Autowired
	private IMessageDao messageDao;
	
	
	public void addMessage(Message message) {
		messageDao.create(message); 
	}
	
	
	public void DeleteMessage(Integer id) {
	messageDao.delete(id);
		
	}
	
}
