/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

/**
 *
 * @author Wenfeng Ge
 */
public class Players {
    private int id;
    private String name;
    private int birthYear; //2007, 2008
    private String gender;//Boy or Girl
    private String email;
    private String team;//four teams choices A B C D
        
    public Players(int id, String name, int birthYear, String gender, String email, String team) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.gender = gender;
        this.email = email;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + birthYear + "," + gender + "," + email + "," + team;
    }
    
    public String getGroup(){
        String group="";
        switch(birthYear){
            case 2007: group = gender+"U12";
            break;
            case 2008: group = gender+"U11";
        }
        
        return group;
    }
}
