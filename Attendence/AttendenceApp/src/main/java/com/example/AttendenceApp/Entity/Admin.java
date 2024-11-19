package com.example.AttendenceApp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor 

public class Admin {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long adminId;

	 private String username;
	    private String email;
	    private String phoneNumber;
	   
	    private String password;

//		@Override
//		public Collection<? extends GrantedAuthority> getAuthorities() {
//			// TODO Auto-generated method stub
//			return null;
//		}

}
