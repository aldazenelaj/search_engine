package com.search.engine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity 
@Table(name = "documents")
@Data
public class Document {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "token", nullable = false, length = 100)
	private String token;
}
