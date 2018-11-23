/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.util.ArrayList;

/**
 *
 * @author Wenfeng Ge
 */
public class Team {
    private String coachAcc;
    private String teamName;
    private ArrayList<Players> members;
    
    public Team(String teamName){
        coachAcc = "None";
        this.teamName = teamName;
        members = new ArrayList<>();
    } 

    public String getCoachAcc() {
        return coachAcc;
    }

    public void setCoachAcc(String coachAcc) {
        this.coachAcc = coachAcc;
    }

    public ArrayList<Players> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Players> members) {
        this.members = members;
    }

    public String getTeamName() {
        return teamName;
    }
    
    
    
    
}
