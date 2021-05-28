package br.com.hudson.devsuperior.domain.dto;

import java.io.Serializable;
import java.time.Instant;

import br.com.hudson.devsuperior.domain.model.Client;

public class ClientDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private String name;	
	private Double income;	
	private Instant birthDate;	
	private Integer children;
	
	public ClientDTO() {
		
	}

	public ClientDTO(Long id, String name, Double income, Instant birthDate, Integer children) {		
		this.id = id;
		this.name = name;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDTO(Client client) {		
		this.id = client.getId();
		this.name = client.getName();
		this.income = client.getIncome();
		this.birthDate = client.getBirthDate();
		this.children = client.getChildren();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}
	
}
