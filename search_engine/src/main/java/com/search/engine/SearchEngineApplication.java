package com.search.engine;

import java.util.LinkedHashMap;
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

	@Autowired
	static ReadInput input;

	public static void main(String[] args) {
		InitializeProperties.initialize(args);

		input = new ReadInput();
		input.print();

		LinkedHashMap<String, String> map = input.getMap();

		DocumentService service = new DocumentService();
		service.execute(map);

	}

}
