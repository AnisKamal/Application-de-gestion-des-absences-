package com.abscence.core.services;

import java.util.List;  

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abscence.core.bo.Conversation;
import com.abscence.core.dao.IConversationDao;

@Service
@Transactional
public class ConversationServiceImpl implements IConversationService {
	
	@Autowired
	private IConversationDao conversationDao;


	public List<Conversation> getConversationByType(String classNam, String columnName, String value) {
		return conversationDao.getEntityByColValue(classNam, columnName, value);
	}

	
	public void addConversation(Conversation conversation) {
		conversationDao.create(conversation);
	}

}
