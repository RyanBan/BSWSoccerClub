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
public class Account {
    private int id;
    private String userName;
    private String pswd;
    private String type; 
    
    public Account(int id,String userName,String pswd,String type){
        this.id = id;
        this.userName = userName;
        this.pswd = pswd;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
    
    @Override
    public String toString(){
        return this.getId()+","+this.getUserName()+","+
                this.getPswd()+","+this.getType();
    }
}
