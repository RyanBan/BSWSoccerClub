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
public class Schedule {
    private String week;
    private String time;
    private String teams;
    private String field;
    
    public Schedule(String week,String time,String teams,String field){
        this.week = week;
        this.time = time;
        this.teams = teams;
        this.field = field;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
    
    @Override
    public String toString(){
        return this.getWeek()+","+this.getTime()+","+this.getTeams()+","+this.getField();
    }
    
}
