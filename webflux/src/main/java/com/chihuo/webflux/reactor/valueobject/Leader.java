package com.chihuo.webflux.reactor.valueobject;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "leader")
public class Leader implements Serializable {

	private static final long serialVersionUID = -1656467610262499352L;

	@Id
	private Integer id;

	private String name;

	private double salary;

	public Leader() {

	}

	public Leader(Integer id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
