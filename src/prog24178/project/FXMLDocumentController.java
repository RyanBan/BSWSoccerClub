/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 *
 * @author Wenfeng Ge
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ToggleGroup accGroup;
    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField tfPswd;
    @FXML
    private Button btLogin;
    @FXML
    private Button btReset;
    @FXML
    private RadioButton rdoAdmin;
    @FXML
    private RadioButton rdoCoach;
    @FXML
    private Label lbError;
    
    private AccountsData accData;
    private ArrayList<Account> adminAccounts;
    private ArrayList<Account> coachAccounts;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accData = new AccountsData();
        try {
            accData.loadData("accData.csv");
            adminAccounts = accData.getAdminAccounts();
            coachAccounts = accData.getCoachAccounts();
            System.out.println("accounts data loaded!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void loginHandler(ActionEvent event) throws IOException {
        boolean isUser = false;   
        
        if(rdoAdmin.isSelected()){
            for(int i=0;i<adminAccounts.size() && !isUser;i++){//for loop to check userName and pswd
                if(tfUser.getText().equals(adminAccounts.get(i).getUserName()) && 
                        tfPswd.getText().equals(adminAccounts.get(i).getPswd())){
                    isUser = true;
                }
            }
            if(isUser){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAdmin.fxml"));
                Parent root = loader.load();
                ((FXMLAdminController) loader.getController()).setAccData(accData);
                Scene currentScene = btLogin.getScene();
                currentScene.setRoot(root);
                
            }
            else{
                lbError.setText("Error:user name or password is wrong!");
            }
        }
        else{//rdoCoach is selected
           for(int i=0;i<coachAccounts.size() && !isUser;i++){//for loop to check userName and pswd
                if(tfUser.getText().equals(coachAccounts.get(i).getUserName()) && 
                        tfPswd.getText().equals(coachAccounts.get(i).getPswd())){
                    isUser = true;
                }
            }
            
            if(isUser){
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCoachDocument.fxml"));
            Scene currentScene = btLogin.getScene();
            currentScene.setRoot(root);
            }
            else{
                lbError.setText("Error:user name or password is wrong!");
            }
        }
    }

    @FXML
    private void resetHandler(ActionEvent event) {
        tfUser.setText("");
        tfPswd.setText("");
    }
    
}
