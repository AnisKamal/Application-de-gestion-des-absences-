package com.abscence.core.services;

import java.util.List;

import com.abscence.core.bo.Conversation;

public interface IConversationService {
	public List<Conversation> getConversationByType(String classNam, String columnName, String value);
	
	public void addConversation(Conversation conversation);
}
