package com.abscence.core.services;

import com.abscence.core.bo.Message;

public interface IMessageService {
	public void addMessage(Message message);
	
	public void DeleteMessage(Integer id);
}
