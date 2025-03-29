package com.testbook.user.entity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.testbook.base.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "users")
@Getter
@Setter
public class UserEntity extends BaseEntity<String> {

	@Indexed(unique = true)
	private String email;
	private String password;
	private String name;
	private String surname;
	private String mobileNo;
	private String City;
	private String State;
	private int age;
	private List<String> SubjectInterest;
	
	@Override
	public void prePersist() {
		if (this.id == null) {
			this.id = UUID.randomUUID().toString();
		}
		super.prePersist();
	}
}
