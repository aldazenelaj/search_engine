package com.search.engine.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {

	private static EntityManagerFactory emfactory ;
	
	public static EntityManager createConnection() {
		  emfactory = Persistence.createEntityManagerFactory( "document_persistence" );
	      
	      EntityManager entitymanager = emfactory.createEntityManager( );
	      entitymanager.getTransaction( ).begin( );
	      return entitymanager;
	}
	
	public static void closeConnection(EntityManager entityManager) {
		  entityManager.close( );
	      emfactory.close( );
	}
}
