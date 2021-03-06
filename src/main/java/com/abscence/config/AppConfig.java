package com.abscence.config;

import java.util.Properties;    

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.abscence.core.bo.Absence;
import com.abscence.core.bo.Compte;
import com.abscence.core.bo.Conversation;
import com.abscence.core.bo.Enseignant;
import com.abscence.core.bo.Etudiant;
import com.abscence.core.bo.Inscription;
import com.abscence.core.bo.Matiere;
import com.abscence.core.bo.Message;
import com.abscence.core.bo.Niveau;
import com.abscence.core.bo.Notification;
import com.abscence.core.bo.TypeSeance;
import com.abscence.core.bo.Utilisateur;

//import com.ensah.core.bo.Person;
//import com.ensah.core.bo.Role;
//import com.ensah.core.bo.UserAccount;

//Configuration d'une application Spring MVC (@EnableWebMvc)
//Avec support des transactions (@EnableTransactionManagement)

@EnableWebMvc // Configuration d'une Application Spring MVC
@Configuration // Classe de configuration qui va contenir des bean ?? cr??er automatiquement par
				// Spring
@ComponentScan(basePackages = { "com.abscence" }) // Packages ?? scanner pour chercher les bean spring de type component
												// (c??d controller, repository, service)
@EnableTransactionManagement // support des transactions
public class AppConfig implements WebMvcConfigurer {

	/**
	 * A utiliser si vous voulez faire la journalisation. Voir le fichier log4j.xml
	 */
	private Logger LOGGER = Logger.getLogger(getClass().getName());

	public AppConfig() {

		// On enregistre une trace dans le journal
		LOGGER.debug(" configuration init...");
	}

	// Configuration du ViewResolver

	@Bean
	public ViewResolver internalResourceViewResolver() { 
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);

		bean.setPrefix("/WEB-INF/view/");
		bean.setSuffix(".jsp");
		return bean;
	}

	// Configuration de la Template HibernateTemplate

	@Bean // N??cessaire pour que Spring cr??era automatiquement HibernateTemplate et lui
			// injecter les d??pendances n??cessaires
	@Autowired // Injection de la session factory (HibernateTemplate a besoin de cr??er les
				// sessions via la session Factory)
	public HibernateTemplate hibernateTemplate(final SessionFactory sessionFactory) { 

		// Cr??ation du bean HibernateTemplate
		HibernateTemplate hibernateTemplate = new HibernateTemplate();
		
		

		// Injection de la session Factory re??u par autowired
		hibernateTemplate.setSessionFactory(sessionFactory);
		
		

		// Tracer la bonne cr??ation de la template dans le journal
		if (hibernateTemplate != null) {
			LOGGER.debug(" HibernateTemplate created ...");
		}
		
		
		return hibernateTemplate;
	}

	// Configuration de la Session Factory de Hibernate
	@Bean // N??cessaire pour que Spring cr??ra automatiquemnt la sessionFactory
	public LocalSessionFactoryBean sessionFactory() { 

		// Code copi?? de la documentation

		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(hibernateProperties());

		// TODO: Indiquer vos classes annot??es par @Entity ici
		sessionFactory.setAnnotatedClasses(Absence.class, TypeSeance.class,Inscription.class, 
				Enseignant.class , Utilisateur.class, Niveau.class,Matiere.class,
				Etudiant.class, Conversation.class, Message.class, Compte.class,
				Notification.class /* une suite de classe */);

		// Tracer dans le journal pour des raisons juste de d??bougage
		// que la session Factory a ??t?? bien cr??e
		if (sessionFactory != null) {
			LOGGER.debug(" sessionFactory created ...");
		}

		return sessionFactory;
	}

	// Les propri??t??s de la configuration Hibernate
	public Properties hibernateProperties() { 

		Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDB103Dialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");

		// D'autres propri??t??s si n??cessaire ....
		

		return hibernateProperties;
	}

	// Configuration de la source de donn??es
	@Bean // n??cessaire car Spring va cr??er la datasource automatiquement et l'injecter
			// apres dans la session factory
	public DataSource getDataSource() { 

		// Les informations de votre base de donn??es

		BasicDataSource dataSource = new BasicDataSource();

		// TODO : A mettre ?? jour en fonction de votre base de donn??es
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver"); // Driver
		dataSource.setUrl("jdbc:mysql://localhost:3306/dbTestCours"); // L'url d'acc??s ?? la base de donn??es
		dataSource.setUsername("root"); // login
		dataSource.setPassword(""); // mot de passe
		return dataSource;
	}

	// Permet de traduire toutes les exceptions de la couche persistance en une
	// seule exception
	// de type PersistenceExceptionTranslationPostProcessor (embalage des execptions
	// de la couche de persistance)
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() { 
		return new PersistenceExceptionTranslationPostProcessor();
	}

	// Configuration du Gestionnaire des Transactions
	// Spring d??finit une API pour la gestion des Transactions
	// (PlatformTransactionManager) qui cache l'impl??mentation r??elle du
	// gestionnaire
	// de transactions pour ne pas d??pendre l'application (couche service) avec
	// les impl??mentations (avec les frameworks sous-jacents utilis??s dans la couche
	// dao)
	// Ici nous avons configu?? un gestionnaire de transactions pour Hibernate
	// (HibernateTransactionManager) mais que nous retournons sous forme de
	// PlatformTransactionManager
	// (car HibernateTransactionManager est une impl??mentation de
	// HibernateTransactionManager)t

	@Bean // Permet ?? Spring de cr??er automatiquement ce bean de gestion des transaction
			// et de l'injecter dans son bon endroit pour l'utilsier dans la couche service
			// dans les services annot??es par @transactional et @service
	@Autowired // Permet d'injecter sessionFactory n??cessaire pour le gestionnaire de
				// transaction
	public PlatformTransactionManager transactionManager(final SessionFactory sessionFactory) {

		// Cr??ation du gestionnaire de transaction de Hibernate
		final HibernateTransactionManager txManager = new HibernateTransactionManager();

		// On lui injecte la session Factory cr??e dans un autre bean un peu plus haut
		// dans cette classe
		txManager.setSessionFactory(sessionFactory);

		// Tracer si vous voulez
		if (txManager != null) {
			LOGGER.debug(" Hibernate Transaction Manager created ...");

		}

		return txManager;
		
	}

	// Spring security Custom Success Handler: Permet de d??finir un gestionnaire
	// personnalis?? pour la
	// redirection apr??s authenitication avec succ??s
	//
	//@Bean // n??cessaire car c'est Spring qui cr??er automatiquement cette classe de type
			// MySimpleUrlAuthenticationSuccessHandler
/*
	public AuthenticationSuccessHandler redirectionAfterAuthenticationSuccessHandler() { 
		return new RedirectionAfterAuthenticationSuccessHandler();
	}
*/
	
}