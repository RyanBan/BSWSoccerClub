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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Wenfeng Ge
 */
public class FXMLTeamsController implements Initializable {
    private Parent mainRoot;
    private PlayersData playersData;
    private TeamsData teamsData;
    private AccountsData accsData;
    
    @FXML
    private Button btSave;
    @FXML
    private Button btBack;
    @FXML
    private TableView<Players> tableURPlayers;
    @FXML
    private TableColumn<Players, Integer> colUrpId;
    @FXML
    private TableColumn<Players, String> colUrpName;
    @FXML
    private TableView<Account> tableCoachAcc;
    @FXML
    private TableColumn<Account, String> colCoach;
    @FXML
    private TextField tfPlayerId;
    @FXML
    private Button btSearchPlayer;
    @FXML
    private ComboBox<String> cboTeamsforPlayer;
    @FXML
    private Button btPlayerR;
    @FXML
    private Button btPlayerUR;
    @FXML
    private TextField tfCoach;
    @FXML
    private Button btSearchCoach;
    @FXML
    private ComboBox<String> cboTeamsforCoach;
    @FXML
    private Button btCoachR;
    @FXML
    private Button btCoachUR;
    @FXML
    private ComboBox<String> cboGroups;
    @FXML
    private TableView<Players> tableTA;
    @FXML
    private TableColumn<Players, Integer> colTaId;
    @FXML
    private TableColumn<Players, String> colTaName;
    @FXML
    private TableView<Players> tableTB;
    @FXML
    private TableColumn<Players, Integer> colTbId;
    @FXML
    private TableColumn<Players, String> colTbName;
    @FXML
    private TableView<Players> tableTC;
    @FXML
    private TableColumn<Players, Integer> colTcId;
    @FXML
    private TableColumn<Players, String> colTcName;
    @FXML
    private TableView<Players> tableTD;
    @FXML
    private TableColumn<Players, Integer> colTdId;
    @FXML
    private TableColumn<Players, String> colTdName;
    @FXML
    private Label lbTAcoach;
    @FXML
    private Label lbTBcoach;
    @FXML
    private Label lbTCcoach;
    @FXML
    private Label lbTDcoach;
    @FXML
    private Label lbStatus;
    @FXML
    private Label lbName;
    @FXML
    private Label lbYear;
    @FXML
    private Label lbGender;
    @FXML
    private Button btClear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboGroups.getItems().addAll("GU11","BU11","GU12","BU12");
        cboGroups.setValue("GU11");
        
        cboTeamsforCoach.getItems().addAll("GU11A","GU11B","GU11C","GU11D",
                        "BU11A","BU11B","BU11C","BU11D",
                        "GU12A","GU12B","GU12C","GU12D",
                        "BU12A","BU12B","BU12C","BU12D");
        cboTeamsforCoach.setValue("GU11A");
        cboTeamsforCoach.setDisable(true);
                
        colUrpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUrpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        colCoach.setCellValueFactory(new PropertyValueFactory<>("userName"));
        
        colTaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTaName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        colTbId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTbName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        colTcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        colTdId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTdName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
    }    
    public void tableInit(){
        playersData.setUnRegisteredPlayers();
        tableURPlayers.setItems(FXCollections.observableArrayList(
                playersData.getUnRegisteredPlayers()));
                
        accsData.updateCoachAccounts();
        tableCoachAcc.setItems(FXCollections.observableArrayList(
                accsData.getCoachAccounts()));
                
        teamsData.generateTeams(playersData.getAllPlayers());
        displayTeamsGroup(cboGroups.getValue());
        
    }
    
    private void displayTeamsGroup(String group){
        switch(group){
            case "GU11":tableTA.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(0).getMembers()));
                      lbTAcoach.setText(teamsData.getAllTeams().get(0).getCoachAcc());
                      
                        tableTB.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(1).getMembers()));
                      lbTBcoach.setText(teamsData.getAllTeams().get(1).getCoachAcc());
                      
                      tableTC.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(2).getMembers()));
                      lbTCcoach.setText(teamsData.getAllTeams().get(2).getCoachAcc());
                      
                      tableTD.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(3).getMembers()));
                      lbTDcoach.setText(teamsData.getAllTeams().get(3).getCoachAcc());                     
            break;
            case "BU11":tableTA.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(4).getMembers()));
                      lbTAcoach.setText(teamsData.getAllTeams().get(4).getCoachAcc());
                      
                        tableTB.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(5).getMembers()));
                      lbTBcoach.setText(teamsData.getAllTeams().get(5).getCoachAcc());
                      
                      tableTC.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(6).getMembers()));
                      lbTCcoach.setText(teamsData.getAllTeams().get(6).getCoachAcc());
                      
                      tableTD.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(7).getMembers()));
                      lbTDcoach.setText(teamsData.getAllTeams().get(7).getCoachAcc());     
            break;
            case "GU12":tableTA.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(8).getMembers()));
                      lbTAcoach.setText(teamsData.getAllTeams().get(8).getCoachAcc());
                      
                        tableTB.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(9).getMembers()));
                      lbTBcoach.setText(teamsData.getAllTeams().get(9).getCoachAcc());
                      
                      tableTC.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(10).getMembers()));
                      lbTCcoach.setText(teamsData.getAllTeams().get(10).getCoachAcc());
                      
                      tableTD.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(11).getMembers()));
                      lbTDcoach.setText(teamsData.getAllTeams().get(11).getCoachAcc());    
            break;
            case "BU12":tableTA.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(12).getMembers()));
                      lbTAcoach.setText(teamsData.getAllTeams().get(12).getCoachAcc());
                      
                        tableTB.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(13).getMembers()));
                      lbTBcoach.setText(teamsData.getAllTeams().get(13).getCoachAcc());
                      
                      tableTC.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(14).getMembers()));
                      lbTCcoach.setText(teamsData.getAllTeams().get(14).getCoachAcc());
                      
                      tableTD.setItems(FXCollections.observableArrayList(
                    teamsData.getAllTeams().get(15).getMembers()));
                      lbTDcoach.setText(teamsData.getAllTeams().get(15).getCoachAcc());    
                                 
        }
    }
    
    @FXML
    private void btSaveHandler(ActionEvent event) throws FileNotFoundException {
        playersData.saveData("playersData.csv");
       teamsData.saveTeamsCoaches("teamsCoaches.csv");
        lbStatus.setText("Players Data and Teams Data\nhave been saved.");
    }

    @FXML
    private void btBackHandler(ActionEvent event) {
        btBack.getScene().setRoot(mainRoot);
    }

    public void setMainRoot(Parent mainRoot) {
        this.mainRoot = mainRoot;
    }

    public void setPlayersData(PlayersData playersData) {
        this.playersData = playersData;
    }
    

    public void setTeamsData(TeamsData teamsData) {
        this.teamsData = teamsData;
    }

    public void setAccsData(AccountsData accsData) {
        this.accsData = accsData;
    }

    @FXML
    private void btSearchPlayerHandler(ActionEvent event) {
        clearControls();
        tfPlayerId.setEditable(false);
        int inputId,index;
        String group;
        try{
            inputId = Integer.parseInt(tfPlayerId.getText());
            index = playersData.getPlayerIndexById(inputId);
            if(index == -1) {
                lbStatus.setText("The player doesn't exists.");
                tfPlayerId.setEditable(true);
            }
            else{
                if(playersData.getAllPlayers().get(index).getTeam().equals("UR")){
                    btPlayerR.setDisable(false);
                    lbStatus.setText("You can register the player\nor clear the search.");
                    lbName.setText(playersData.getAllPlayers().get(index).getName());
                    lbYear.setText(playersData.getAllPlayers().get(index).getBirthYear()+"");
                    lbGender.setText(playersData.getAllPlayers().get(index).getGender());
                    
                    cboTeamsforPlayer.setDisable(false);
                    cboTeamsforPlayer.getItems().clear();
                    group = playersData.getAllPlayers().get(index).getGroup();
                    cboTeamsforPlayer.getItems().addAll(group+"A",group+"B",group+"C",group+"D");
                    cboTeamsforPlayer.setValue(group+"A");
                    
                }
                else{
                    btPlayerUR.setDisable(false);
                    lbStatus.setText("Unregister the player \nor clear the search.");
                    lbName.setText(playersData.getAllPlayers().get(index).getName());
                    lbYear.setText(playersData.getAllPlayers().get(index).getBirthYear()+"");
                    lbGender.setText(playersData.getAllPlayers().get(index).getGender());
                                  
                    cboTeamsforPlayer.getItems().clear();
                    cboTeamsforPlayer.getItems().addAll(playersData.getAllPlayers().get(index).getTeam());
                    cboTeamsforPlayer.setValue(playersData.getAllPlayers().get(index).getTeam());
                    cboTeamsforPlayer.setDisable(true);
                }
            }
            
            
        }catch(Exception ex){
            lbStatus.setText("Error:Id must be an integer.");
            tfPlayerId.setEditable(true);
        }       
    }

    @FXML
    private void btPlayerRegister(ActionEvent event) {
        int input,index;
        input = Integer.parseInt(tfPlayerId.getText());
        index = playersData.getPlayerIndexById(input);
        playersData.getAllPlayers().get(index).setTeam(cboTeamsforPlayer.getValue());
        tfPlayerId.setEditable(true);
        clearControls();        
        tableInit();
        lbStatus.setText("The player has been registered.");        
    }

    @FXML
    private void btPlayerUnRegister(ActionEvent event) {
        int input,index;
        input = Integer.parseInt(tfPlayerId.getText());
        index = playersData.getPlayerIndexById(input);
        playersData.getAllPlayers().get(index).setTeam("UR");
        tfPlayerId.setEditable(true);
        clearControls();
        tableInit();
        lbStatus.setText("The player has been unregistered.");     
        
    }

    @FXML
    private void btSearchCoach(ActionEvent event) {
        clearControls();
        tfCoach.setEditable(false);
        String input = tfCoach.getText();
        boolean coachExists = false;
        boolean coachRegistered = false;
        int index = -1;
        for(int i=0;i<accsData.getCoachAccounts().size()&&!coachExists;i++){
            if(accsData.getCoachAccounts().get(i).getUserName().equals(input))coachExists = true;           
        }
        
        if(!coachExists) {
            lbStatus.setText("The coach account doesn't exists.");
            tfCoach.setEditable(true);
        }
        else{
            for(int j=0;j<teamsData.getTeamsCoaches().length && !coachRegistered;j++){
                if(teamsData.getTeamsCoaches()[j].equals(input)) {
                    coachRegistered = true;
                    index = j;
                }
            }
            if(!coachRegistered) {
                btCoachR.setDisable(false);
                lbStatus.setText("Register the coach to one team\nor clear the search.");
                cboTeamsforCoach.setDisable(false);
            }
            else {
                btCoachUR.setDisable(false);
                lbStatus.setText("Unregister the coach\nor clear the search.");
                 cboTeamsforCoach.setValue(teamsData.getAllTeams().get(index).getTeamName());
                 cboTeamsforCoach.setDisable(true);
                
            }                      
        }       
    }

    @FXML
    private void btCoachRegister(ActionEvent event) {
        String coachAcc = tfCoach.getText();
        teamsData.getTeamByName(cboTeamsforCoach.getValue()).setCoachAcc(coachAcc);
        int index = teamsData.getAllTeams().indexOf(teamsData.getTeamByName(cboTeamsforCoach.getValue()));
        teamsData.getTeamsCoaches()[index]= coachAcc;
        clearControls();
        lbStatus.setText("The coach has been registered\nto the team.");
        tfCoach.setEditable(true);
    }

    @FXML
    private void btCoachUnregister(ActionEvent event) {
        teamsData.getTeamByName(cboTeamsforCoach.getValue()).setCoachAcc("None");
        int index = teamsData.getAllTeams().indexOf(teamsData.getTeamByName(cboTeamsforCoach.getValue()));
        teamsData.getTeamsCoaches()[index]= "None";
        clearControls();
        lbStatus.setText("The coach has been unregistered\nfrom the team.");
        tfCoach.setEditable(true);
    }

    private void btUpdateHandler(ActionEvent event) {
        tableInit();
    }

    @FXML
    private void cboDisplay(ActionEvent event) {
        displayTeamsGroup(cboGroups.getValue());
    }
    
    public void clearControls(){
        btPlayerR.setDisable(true);
        btPlayerUR.setDisable(true);
        btCoachR.setDisable(true);
        btCoachUR.setDisable(true);
        cboTeamsforCoach.setDisable(true);
        cboTeamsforPlayer.setDisable(true);
        
    }

    @FXML
    private void btClearHandler(ActionEvent event) {
        clearControls();
        lbStatus.setText("Status:");
        tfCoach.setText("");
        tfPlayerId.setText("");
        tfCoach.setEditable(true);
        tfPlayerId.setEditable(true);
        lbName.setText("");
        lbYear.setText("");
        lbGender.setText("");
        
    }
    
    
}
