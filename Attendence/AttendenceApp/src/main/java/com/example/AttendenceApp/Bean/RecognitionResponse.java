package com.example.AttendenceApp.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecognitionResponse {
	
	  public RecognitionResponse(RecognitionResponse isRecognized) {
		// TODO Auto-generated constructor stub
	}
	private boolean success;
	    private String message;
  
}
