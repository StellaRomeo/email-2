package co.develhope.email2.api.controllers;

import co.develhope.email2.api.entities.NotificationDTO;
import co.develhope.email2.emails.services.EmailService;
import co.develhope.email2.students.entities.Student;
import co.develhope.email2.students.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class NotificationController {

    @Autowired
    StudentService studentService;

    @Autowired
    EmailService emailService;

    @PostMapping("/notification")
    public ResponseEntity sendNotification(@RequestBody NotificationDTO payload){
        try{
            Student studentToNotify = studentService.getStudentById(payload.getContactId());
            System.out.println("studentToNotify: " + studentToNotify);
            emailService.sendTo(studentToNotify.getEmail(), payload.getTitle(), payload.getText());
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
