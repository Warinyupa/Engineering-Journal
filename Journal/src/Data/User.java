/*
 * this class use to keep your Profile.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Date;

/**
 *
 * @author user
 */
public class User {
private String username;
private String password;
private String firstname;
private String lastname;
private String studentID;
private String birthDay;
private String telephone;
private String email;
private String facebook;
private String status;

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}


public String getBirthDay() {
return birthDay;
}

public void setBirthDay(String birthDay) {
this.birthDay = birthDay;
}

public void setEmail(String email) {
this.email = email;
}

public String getEmail() {
return email;
}


public String getFacebook() {
return facebook;
}

public void setFacebook(String facebook) {
this.facebook = facebook;
}

public String getFirstname() {
return firstname;
}

public void setFirstname(String firstname) {
this.firstname = firstname;
}

public String getLastname() {
return lastname;
}

public void setLastname(String lastname) {
this.lastname = lastname;
}

public String getTelephone() {
return telephone;
}

public void setTelephone(String telephone) {
this.telephone = telephone;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getStudentID() {
return studentID;
}

public void setStudentID(String studentID) {
this.studentID = studentID;
}
           
}
