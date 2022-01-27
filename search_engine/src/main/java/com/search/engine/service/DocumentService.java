package com.search.engine.service;

import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;

import com.search.engine.connection.Connection;
import com.search.engine.entity.Document;

public class DocumentService {

	
	
 public void addDocument(Map<Integer,String> map) {
	 
	 EntityManager entityManager = Connection.createConnection();
	 
	 Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
	 while(iterator.hasNext())
     {
          Map.Entry<Integer, String> entry = iterator.next();
          Document document = new Document();
//          document.setId(entry.getKey());
//          document.setToken(entry.getValue());
//          entityManager.persist(document);
      //    entityManager.getTransaction( ).commit( );
          
        //  entityManager.getTransaction().begin();
          document = entityManager.find(Document.class, entry.getKey());
          document.setToken(entry.getValue());
          entityManager.merge(document); // modify person data entityManager.getTransaction().commit();
          entityManager.getTransaction( ).commit( );
          Connection.closeConnection(entityManager);
     }
	// return true;
 }
}
