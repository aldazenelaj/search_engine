package com.search.engine.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.search.engine.connection.Connection;
import com.search.engine.entity.Document;
import com.search.engine.exception.ExceptionMessage;
import com.search.engine.readInput.ReadInput;

public class DocumentService {

	public void addDocument(Map<String, String> map) {

		EntityManager entityManager = Connection.createConnection();

		try {
			Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				
				if(ReadInput.isNumeric(entry.getKey())) {//index command
				Document document = entityManager.find(Document.class, entry.getKey());

				if (document != null) {
					document.setToken(document.getToken() + " " + entry.getValue());
					entityManager.merge(document); 

				} else {
					document = new Document();
					document.setId(Integer.parseInt(entry.getKey()));
					document.setToken(entry.getValue());
					entityManager.persist(document);
				}
				entityManager.getTransaction().commit();
				System.out.println(ExceptionMessage.INDEX_OK+entry.getKey());
			}else {//query command
				
				this.search(entry.getValue(), entityManager);
				
			}
			}
		} catch (Exception e) {
			Connection.closeConnection(entityManager);
			System.out.println(ExceptionMessage.INTERNAL_SERVER_ERROR);
		} finally {
			Connection.closeConnection(entityManager);
		}

	}

	private void search(String expression,EntityManager entityManager) {
		Query query = entityManager.createNativeQuery("SELECT d.id FROM search_engine.documents d WHERE MATCH(token) AGAINST(:token in BOOLEAN MODE)");
		query.setParameter("token", this.expressionBuilder(expression));
		List<Integer> result = query.getResultList();
		System.out.print(ExceptionMessage.QUERY_RESULTS);
		for(int d : result) {
			System.out.print(" "+d);
		}
		
	}
	
	private String expressionBuilder(String expression) {
		String exp="";
		String[] split = expression.split("&");
		for(String s : split) {
			exp+=("+"+s.replace("|", " ")+" ");
		}
		
		return exp;
	}
}
