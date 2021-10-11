package com.abscence.core.services;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abscence.core.bo.Notification;
import com.abscence.core.dao.INotificationDao;

@Transactional
@Service
public class NotificationServiceImpl implements INotificationService{
	
	@Autowired
	private INotificationDao notificationDao;
	
	
	public void addNotification(Notification notification) {
		notificationDao.create(notification);
	}

}
