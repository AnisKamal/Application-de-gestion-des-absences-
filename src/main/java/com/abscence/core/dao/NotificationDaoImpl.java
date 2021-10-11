package com.abscence.core.dao;

import org.springframework.stereotype.Repository;

import com.abscence.core.bo.Notification;
import com.abscence.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class NotificationDaoImpl extends HibernateSpringGenericDaoImpl<Notification, Integer>
implements INotificationDao{

	public NotificationDaoImpl() {
		super(Notification.class);
	}

}
