package com.search.engine;

import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.search.engine.entity.Document;
import com.search.engine.properties.InitializeProperties;
import com.search.engine.readInput.ReadInput;
import com.search.engine.service.DocumentService;

public class SearchEngineApplication {
	
//	@Autowired
//	static DocumentService service;
	
	@Autowired
	static ReadInput input;
	
	public static void main(String[] args) {	
		InitializeProperties.initialize(args);
		
		input = new ReadInput();
		input.print();
		
		Map<String, String> map = input.getMap();
		
		 DocumentService service = new DocumentService();
		service.addDocument(map);
		
//		 EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "document_persistence" );
//	      
//	      EntityManager entitymanager = emfactory.createEntityManager( );
//	      entitymanager.getTransaction( ).begin( );
//	      Document d = new Document();
//	      d.setId(Integer.parseInt(str));
//	      d.setToken("hhhh");
//	      
//	      entitymanager.persist( d );
//	      entitymanager.getTransaction( ).commit( );

//	      entitymanager.close( );
//	      emfactory.close( );
	}

}
