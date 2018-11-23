/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Wenfeng Ge
 */
public class FXMLAccountsController implements Initializable {

    @FXML
    private Button btBack;
    private Parent mainRoot;
    private AccountsData accData;
    private TeamsData teamsData;
    private ObservableList<Account> tableData;
    @FXML
    private TableView<Account> tableAcc;
    @FXML
    private TableColumn<Account, Integer> colId;
    @FXML
    private TableColumn<Account, String> colUname;
    @FXML
    private TableColumn<Account, String> colPswd;
    @FXML
    private TableColumn<Account, String> colType;
    @FXML
    private Button btAdd;
    @FXML
    private Button btDel;
    @FXML
    private Button btEdit;
    @FXML
    private Button btSave;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfUname;
    @FXML
    private TextField tfPswd;
    @FXML
    private ToggleGroup accType;
    @FXML
    private Button btSubmit;
    @FXML
    private Label lbNote;
    @FXML
    private Label lbError;
    @FXML
    private RadioButton rdoAdmin;
    @FXML
    private RadioButton rdoCoach;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btSubmit.setDisable(true);// TODO
    }    

    public void tableInit(){
        tableData = FXCollections.observableList(accData.getAllAccounts());
        tableAcc.setItems(tableData);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUname.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPswd.setCellValueFactory(new PropertyValueFactory<>("pswd"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
    }
    @FXML
    private void btBackHandler(ActionEvent event) {
        btSubmit.setDisable(true);
        btBack.getScene().setRoot(mainRoot);
    }

    public void setMainRoot(Parent mainRoot) {
        this.mainRoot = mainRoot;
    }

    public void setAccData(AccountsData accData) {
        this.accData = accData;
        System.out.println(this.accData.getAdminAccounts().size()+", "
                +this.accData.getAllAccounts().size());
    }

    @FXML
    private void btAddHandler(ActionEvent event) {
        lbNote.setText("Complete the above form then \nclick submit to add a new account!");
        lbError.setText("");
                
        tfUname.setDisable(false);
        tfPswd.setDisable(false);
        rdoAdmin.setDisable(false);
        rdoCoach.setDisable(false);
        
        btSubmit.setDisable(false);
        btSubmit.setOnAction(e->{ addAccount();});
        
    }

    @FXML
    private void btDelHandler(ActionEvent event) {
        lbError.setText("");
        lbNote.setText("Input an account id \nclick submit to delete the account!");
        tfUname.setDisable(true);
        tfPswd.setDisable(true);
        rdoAdmin.setDisable(true);
        rdoCoach.setDisable(true);
        
        btSubmit.setDisable(false);
        btSubmit.setOnAction(e->{ deleteAccount();});
    }

    @FXML
    private void btEditHandler(ActionEvent event) {
        lbError.setText("");
        lbNote.setText("Input an account id with new password\n"
                + "click submit to make new password effective!");
        
        tfUname.setDisable(true);
        tfPswd.setDisable(false);
        rdoAdmin.setDisable(true);
        rdoCoach.setDisable(true);
        
        btSubmit.setDisable(false);
        btSubmit.setOnAction(e->{ editAccount();});
    }

    @FXML
    private void btSaveHandler(ActionEvent event) throws FileNotFoundException {
        lbError.setText("");
        btSubmit.setDisable(true);
        accData.saveData("accData.csv");
        teamsData.saveTeamsCoaches("teamsCoaches.csv");
        lbNote.setText("Account Changes have been saved to data file!");
    }
    
    private void addAccount(){
        int inputId;
        String inputUser,inputPswd,inputType;
        boolean idExists = false;
        boolean userExists = false;
        try{
            if(tfId.getText().equals("") || tfUname.getText().equals("") || tfPswd.getText().equals("")){
                lbError.setText("No field can be empty!");
            }
            else{
                inputId = Integer.parseInt(tfId.getText());  
                inputUser = tfUname.getText();
                for(int i=0;i<accData.getAllAccounts().size() && !idExists;i++){
                    if (inputId == accData.getAllAccounts().get(i).getId()) idExists = true;
                }
                for(int i=0;i<accData.getAllAccounts().size() && !userExists;i++){
                    if (inputUser.equals(accData.getAllAccounts().get(i).getUserName())) 
                        userExists = true;
                }
                if (idExists && userExists) lbError.setText("Both id and username have already exist.");
                else if (idExists) lbError.setText("The id has already exists.");
                else if (userExists) lbError.setText("The username has already exists.");
                else {
                    inputPswd = tfPswd.getText();
                    RadioButton rbSelected = (RadioButton) accType.getSelectedToggle();
                    inputType = rbSelected.getText();
                    Account newAcc = new Account(inputId,inputUser,inputPswd,inputType);
                    tableData.add(newAcc);
                    System.out.println(accData.getAllAccounts().size());
                    lbNote.setText("The new account has been added.\n Please save before close the program.");
                    lbError.setText("");
                }
            }
        }catch(Exception ex){
            lbError.setText("Id must be an integer!");
        }
        
    }
    
    private void deleteAccount(){
        int inputId;
        boolean idExists = false;
        try{
            inputId = Integer.parseInt(tfId.getText());  
            for(int i=0;i<tableData.size() && !idExists;i++){
                if (inputId == tableData.get(i).getId()) {
                    idExists = true;
                   
                    for(int j=0;j<teamsData.getTeamsCoaches().length;j++){
                        if (tableData.get(i).getUserName().equals(teamsData.getTeamsCoaches()[j])){
                            teamsData.getTeamsCoaches()[j] = "None";
                        }
                    }
                    tableData.remove(i);
                    System.out.println(accData.getAllAccounts().size());
                    lbNote.setText("The account has been deleted.\n"
                            + "Please save before close the program.");
                    lbError.setText("");
                }
            }
            if(!idExists) lbError.setText("The account doesn't exist!\nYou don't need delete it.");
            
        }catch(Exception ex){
            lbError.setText("Id must be an integer!");
        }
    }
    
    private void editAccount(){
        int inputId;
        int indexId=-1;
        boolean idExists = false;
        try{
            inputId = Integer.parseInt(tfId.getText()); 
            for(int i=0;i<tableData.size() && !idExists;i++){
                    if (inputId == tableData.get(i).getId()) {
                        idExists = true;
                        indexId = i;
                    }
            }
            if(idExists) {
                if(tfPswd.getText().equals("")){
                    lbError.setText("password cannot be empty!");
                }
                else{
                    tableData.get(indexId).setPswd(tfPswd.getText());
                    lbNote.setText("The account password has been reset.\n"
                                + "Please save before close the program.");
                    lbError.setText("");
                }
            }    
            else{              
                lbError.setText("The account doesn't exist!");
            }            
                     
        }catch(Exception ex){
            lbError.setText("Id must be an integer!");
        }
        
    }

    public void setTeamsData(TeamsData teamsData) {
        this.teamsData = teamsData;
    }

   
    
}
