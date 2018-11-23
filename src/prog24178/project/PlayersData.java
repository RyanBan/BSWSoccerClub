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
public class PlayersData {
    private ArrayList<Players> allPlayers;
    private ArrayList<Players> unRegisteredPlayers;
        
    public PlayersData(){
        allPlayers = new ArrayList<>();
        unRegisteredPlayers = new ArrayList<>();
    }

    public ArrayList<Players> getAllPlayers() {
        return allPlayers;
    }

    public void setUnRegisteredPlayers() {
        unRegisteredPlayers.clear();
        for(int i=0;i<allPlayers.size();i++){
            if(allPlayers.get(i).getTeam().equals("UR")) {
                unRegisteredPlayers.add(allPlayers.get(i));
            }
        }
       
    }
    
    public int getPlayerIndexById(int id){
        boolean isFound = false;
        int index = -1; 
        for(int i=0;i<this.getAllPlayers().size() && !isFound;i++){
            if(id == this.getAllPlayers().get(i).getId()) {
                index = i;
                isFound = true;
            }
        }
        return index;
    }
    
    public int getURPlayerIndexById(int id){
        boolean isFound = false;
        int index = -1; 
        for(int i=0;i<this.getUnRegisteredPlayers().size() && !isFound;i++){
            if(id == this.getUnRegisteredPlayers().get(i).getId()) {
                index = i;
                isFound = true;
            }
        }
        return index;
    }
    
    
     public void loadData(String fileName) throws FileNotFoundException{
        Scanner fileReader = null;
         try{
            File dataFile=new File(fileName);
            fileReader = new Scanner(dataFile);
            fileReader.useDelimiter(System.getProperty("line.separator"));
            while(fileReader.hasNext()){
                String[] dataLine = fileReader.next().split(",");
                int id = Integer.parseInt(dataLine[0]);
                String name = dataLine[1];
                int birthyear = Integer.parseInt(dataLine[2]);
                String gender = dataLine[3];
                String email = dataLine[4];
                String team = dataLine[5];
                
                Players newPlayer = new Players(id,name,birthyear,gender,email,team);
                allPlayers.add(newPlayer);
            }
            System.out.println(fileName+" has been loaded successfully!");            
         }
         catch(Exception ex){
               System.out.print(ex);             
         }finally{
          if(fileReader!=null)   fileReader.close();
         }       
    }
     
    public void saveData(String fileName) throws FileNotFoundException{
        File f=new File(fileName);
        PrintWriter writer = null;
        try{
            writer=new PrintWriter(f);   
            for(int i=0;i<this.getAllPlayers().size();i++){
                    writer.println(this.getAllPlayers().get(i));             
            }
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }finally {
          if(writer!=null)  writer.close();
       }
    } 

    public ArrayList<Players> getUnRegisteredPlayers() {
        return unRegisteredPlayers;
    }
    
    
}
