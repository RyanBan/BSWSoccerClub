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
public class AccountsData {
    private ArrayList<Account> adminAccounts;
    private ArrayList<Account> coachAccounts;
    private ArrayList<Account> allAccounts;
       
    public AccountsData(){
        adminAccounts = new ArrayList<>();
        coachAccounts = new ArrayList<>();
        allAccounts = new ArrayList<>();
    }
    
    public void loadData(String fileName) throws FileNotFoundException{
        Scanner fileReader = null;
         try{
            File dataFile=new File(fileName);
            fileReader = new Scanner(dataFile);
            fileReader.useDelimiter(System.getProperty("line.separator"));
            while(fileReader.hasNext()){//2 cases: admin or coach
                String[] dataLine = fileReader.next().split(",");
                int id = Integer.parseInt(dataLine[0]);
                String userName = dataLine[1];
                String pswd = dataLine[2];
                String type = dataLine[3];
                Account acc = new Account(id,userName,pswd,type);
                if (type.equals("admin")) adminAccounts.add(acc);
                if (type.equals("coach")) coachAccounts.add(acc);
                allAccounts.add(acc);
            }
            System.out.println(fileName+" has been loaded successfully!");            
         }
         catch(Exception ex){
               System.out.print(ex);             
         }finally{
             fileReader.close();
         }
               
    
        
    }

    public ArrayList<Account> getAdminAccounts() {
        return adminAccounts;
    }
    
    public void updateCoachAccounts(){
        coachAccounts.clear();
        for(Account acc:allAccounts){
            if(acc.getType().equals("coach")) coachAccounts.add(acc);
        }
    }
    
    public ArrayList<Account> getCoachAccounts() {
        return coachAccounts;
    }

    public ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }
    public void saveData(String fileName) throws FileNotFoundException{
        File f=new File(fileName);
        PrintWriter writer = null;
        try{
            writer=new PrintWriter(f);   
            for(int i=0;i<this.getAllAccounts().size();i++){
                    writer.println(this.getAllAccounts().get(i));             
            }
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }finally {
            writer.close();
       }
    }
    
}
