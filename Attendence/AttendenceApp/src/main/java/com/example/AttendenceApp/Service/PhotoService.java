package com.example.AttendenceApp.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.AttendenceApp.Bean.RecognitionResponse;
import com.example.AttendenceApp.Entity.Employee;
import com.example.AttendenceApp.Entity.EmployeeAttendance;
import com.example.AttendenceApp.Repository.EmployeeAttendanceRepository;
import com.example.AttendenceApp.Repository.EmployeeRepository;

@Service
public class PhotoService {

    @Value("${facepp.api.key}")
    private String apiKey;

    @Value("${facepp.api.secret}")
    private String apiSecret;

    @Autowired
    private EmployeeRepository employeerep;
    
    @Autowired
    EmployeeAttendanceRepository employeeAttendanceRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public RecognitionResponse recognizeFace(String base64Photo) {
        try {
            List<Employee> employees = employeerep.findAll();
            for (Employee employee : employees) {
                String storedPhoto = employee.getBiometricData();
                LocalDate today = LocalDate.now();
                String responseString = callFaceRecognitionAPI(base64Photo, storedPhoto);
                JSONObject jsonResponse = new JSONObject(responseString);

                if (jsonResponse.has("confidence")) {
                    double confidence = jsonResponse.getDouble("confidence");
                    System.out.println("Confidence score: " + confidence);

                    if (confidence >= 80.0) {  // 70 is a common threshold, adjust as needed
                    	
                        System.out.println("Face recognition successful for employee: " + employee.getUsername());
                        RecognitionResponse attendanceResponse = handleAttendance(employee, today);

                        // Return the attendance response (login/logout success message)
                        return attendanceResponse;               }
                }
            }

            System.out.println("No matching face found");
            return new RecognitionResponse(false, "No matching face found");

        } catch (JSONException e) {
            System.err.println("JSON Parsing error: " + e.getMessage());
            return new RecognitionResponse(false, "Error processing face recognition");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            return new RecognitionResponse(false, "Unexpected error during face recognition");
        }
    }
    
    
    
    
    
    
    private RecognitionResponse handleAttendance(Employee employee, LocalDate today) {
        if (employee == null || today == null) {
            return new RecognitionResponse(false, "Employee or date cannot be null");
        }

        // Check if the employee has already logged in today
        Optional<EmployeeAttendance> attendanceOpt = employeeAttendanceRepository.findByEmployeeAndAttendanceDate(employee, today);
        
        if (attendanceOpt.isPresent()) {
            EmployeeAttendance attendance = attendanceOpt.get();
            
            if (attendance.getLogoutTime() == null) {
                // Logout the employee if they are logged in but not logged out yet
                attendance.setLogoutTime(LocalDateTime.now());
                employeeAttendanceRepository.save(attendance);
                return new RecognitionResponse(true, "Logout successful");
            } else {
                // Already logged out, no further action allowed
                return new RecognitionResponse(false, "You have already logged out today");
            }
        } else {
            // If no attendance record exists, log the employee in
            EmployeeAttendance newAttendance = new EmployeeAttendance(employee, today, LocalDateTime.now());
            employeeAttendanceRepository.save(newAttendance);
            return new RecognitionResponse(true, "Login successful");
        }
    }

    
    
    
    
    
    
    

    private String callFaceRecognitionAPI(String base64Photo1, String base64Photo2) {
        final String API_URL = "https://api-us.faceplusplus.com/facepp/v3/compare";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("api_key", apiKey);
        params.add("api_secret", apiSecret);
        params.add("image_base64_1", base64Photo1);
        params.add("image_base64_2", base64Photo2);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, requestEntity, String.class);
        return response.getBody();
    }
}
