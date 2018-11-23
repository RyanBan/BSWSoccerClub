/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Wenfeng Ge
 */
public class TeamsData {
    String[] teamsCoaches;
    ArrayList<Team> allTeams;
    
    public TeamsData(){
        teamsCoaches = new String[16];
        allTeams = new ArrayList<>();
        allTeams.add(new Team("GU11A"));
        allTeams.add(new Team("GU11B"));
        allTeams.add(new Team("GU11C"));
        allTeams.add(new Team("GU11D"));
        allTeams.add(new Team("BU11A"));
        allTeams.add(new Team("BU11B"));
        allTeams.add(new Team("BU11C"));
        allTeams.add(new Team("BU11D"));
        allTeams.add(new Team("GU12A"));
        allTeams.add(new Team("GU12B"));
        allTeams.add(new Team("GU12C"));
        allTeams.add(new Team("GU12D"));
        allTeams.add(new Team("BU12A"));
        allTeams.add(new Team("BU12B"));
        allTeams.add(new Team("BU12C"));
        allTeams.add(new Team("BU12D"));
            
    }
    
    public void generateTeams(ArrayList<Players> playersList){
           for(int i=0;i<16;i++){
               allTeams.get(i).setCoachAcc(teamsCoaches[i]);
           }
           for(int i = 0;i < allTeams.size();i++){
               allTeams.get(i).getMembers().clear();
           }
           
           for(Players player : playersList){
                switch(player.getTeam()){
                    case "GU11A": allTeams.get(0).getMembers().add(player);
                        break;
                    case "GU11B": allTeams.get(1).getMembers().add(player);
                        break;
                    case "GU11C": allTeams.get(2).getMembers().add(player);
                        break;
                    case "GU11D": allTeams.get(3).getMembers().add(player);
                        break;
                    case "BU11A": allTeams.get(4).getMembers().add(player);
                        break;
                    case "BU11B": allTeams.get(5).getMembers().add(player);
                        break;
                    case "BU11C": allTeams.get(6).getMembers().add(player);
                        break;
                    case "BU11D": allTeams.get(7).getMembers().add(player);
                        break;
                    case "GU12A": allTeams.get(8).getMembers().add(player);
                        break;
                    case "GU12B": allTeams.get(9).getMembers().add(player);
                        break;
                    case "GU12C": allTeams.get(10).getMembers().add(player);
                        break;
                    case "GU12D": allTeams.get(11).getMembers().add(player);
                        break;
                    case "BU12A": allTeams.get(12).getMembers().add(player);
                        break;
                    case "BU12B": allTeams.get(13).getMembers().add(player);
                        break;
                    case "BU12C": allTeams.get(14).getMembers().add(player);
                        break;
                    case "BU12D": allTeams.get(15).getMembers().add(player);
                        break;                      
                    default:;    
                }
           }
           
        
    }
    
    public void loadTeamsCoaches(String fileName){
        Scanner fileReader = null;
         try{
            File dataFile=new File(fileName);
            fileReader = new Scanner(dataFile);
            fileReader.useDelimiter(System.getProperty("line.separator"));
            for (int i=0;i<teamsCoaches.length;i++){
                teamsCoaches[i] = fileReader.next();
            }
            System.out.println(fileName+" has been loaded successfully!");            
         }
         catch(Exception ex){
               System.out.print(ex);             
         }finally{
          if(fileReader!=null)   fileReader.close();
         }       
    }
    
    public void saveTeamsCoaches(String fileName){
        File f=new File(fileName);
        PrintWriter writer = null;
        try{
            writer=new PrintWriter(f);   
            for (String teamsCoache : teamsCoaches) {
                writer.println(teamsCoache);             
            }
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }finally {
          if(writer!=null)  writer.close();
       }
        
    }

    public String[] getTeamsCoaches() {
        return teamsCoaches;
    }

    public ArrayList<Team> getAllTeams() {
        return allTeams;
    }
    
    public void addPlayerToTeam(Players player,String teamname){
        if(player.getTeam().equals("UR")){
            
        }
    }
    
    public void removePlayerFromTeam(Players player){
        
    }
    public Team getTeamByName(String name){
        for(int i=0;i<allTeams.size();i++){
            if (name.equals(allTeams.get(i).getTeamName())) 
                return allTeams.get(i);
        }
        return null;
    }
}
