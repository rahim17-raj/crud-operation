package org.jsp.crud_operation.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Student {
	@Id
	@GeneratedValue(generator = "x")
	@SequenceGenerator(initialValue = 1001, allocationSize = 1, name = "x")
	private int id;
	private String name;
	private long mobile;
	private String gender;
	private int sub1;
	private int sub2;
	private int sub3;
	
	public double getPercentage()
	{
		return (sub1+sub2+sub3)/3.0;
	}
}
