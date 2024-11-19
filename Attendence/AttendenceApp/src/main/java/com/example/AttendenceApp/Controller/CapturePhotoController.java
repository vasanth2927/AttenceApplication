package com.example.AttendenceApp.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.AttendenceApp.Bean.RecognitionResponse;
import com.example.AttendenceApp.Service.PhotoService;

@RestController
@RequestMapping("/photo")
@CrossOrigin(origins = "*")                   
public class CapturePhotoController {
     
	
    @Autowired
    private PhotoService photoservice;

//   @GetMapping("/employeeform")
//    public ModelAndView Attendence() {
//        return new ModelAndView("Attendence");
//    }
//
//    @GetMapping("/newemployeeface")
//    public ModelAndView Resgisternewemployeeface() {
//        return new ModelAndView("Employeeform");
//    }
//
//    @PostMapping("/faceadddatabase")
//    public ModelAndView addEmployee(@RequestParam("id") Long id,
//                              @RequestParam("name") String name,
//                              @RequestParam("photo") String photo) {
//        // Create a new employee object
//        Employeephotodata employee = new Employeephotodata();
//        employee.setId(id);
//        employee.setName(name);
//        employee.setBiometricData(photo); // Store Base64 photo data
//
//        // Save the employee to the database
//        photoservice.saveEmployee(employee);
//
//        return new ModelAndView("Attendence"); // Redirect to form or some other page
//    }
       
    
    
    @GetMapping("/scanemployee")
    public ModelAndView Employeescan() {
        return new ModelAndView("Employeel_in_or_out");
    }

    @PostMapping("/scan-face")
    public ResponseEntity<RecognitionResponse> scanFace(@RequestBody Map<String, String> request) {
        String base64Photo = request.get("photo");
        System.out.println(base64Photo+"..............");
        RecognitionResponse isRecognized = photoservice.recognizeFace(base64Photo);

        RecognitionResponse response = new RecognitionResponse(isRecognized);
        return ResponseEntity.ok(isRecognized);  // Return a proper HTTP response
    }

}
