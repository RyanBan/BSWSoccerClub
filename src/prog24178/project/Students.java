/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

/**
 *
 * @author banse
 */
import java.util.Scanner;

/**
 *
 * @author banse
 */
public class Students extends FXMLDocumentController {
    
    private int id;
    protected String firstName;
    protected String lastName;
    private int birthYear;
    private String gender;
    private String email;
    private String team;
    
    public Students() {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.gender = gender;
        this.email = email;
        this.team = team;
    }

    public Students(int id, String firstName, String lastName, int birthYear, String gender, String email, String team) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.gender = gender;
        this.email = email;
        this.team = team;

    }

    public void loadStudent(String student_detail) {
        Scanner ip = new Scanner(student_detail);
        ip.useDelimiter(";");
        id = ip.nextInt();
        firstName = ip.next();
        lastName = ip.next();
        birthYear = ip.nextInt();
        gender = ip.next();
        email = ip.next();
        ip.close();
    }

    public String getName() {
        return firstName + " " + lastName;
    }
    
     public String getEmail(){
        return email;
    }

}