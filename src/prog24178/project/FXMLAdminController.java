/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author Wenfeng Ge
 */
public class FXMLAdminController implements Initializable {

    @FXML
    private Button btTeams;
    @FXML
    private Button btSchedule;
    @FXML
    private Button btAccounts;
    @FXML
    private Button btPlayers;
    
    private Scene currentScene;
    
    private Parent playersRoot;
    private FXMLPlayersController plysController;
    
    private Parent teamsRoot;
    private FXMLTeamsController teamsController;
    
    private Parent scheduleRoot;
    private FXMLScheduleController schController;
    
    private Parent accsRoot;
    private FXMLAccountsController accsController;
      
    private AccountsData accData;
    private PlayersData playersData;
    private TeamsData teamsData;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      playersData = new PlayersData();
        try {
            playersData.loadData("playersData.csv");
            System.out.println(playersData.getAllPlayers().size()+" "+
                    playersData.getUnRegisteredPlayers().size());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        teamsData = new TeamsData();
        teamsData.loadTeamsCoaches("teamsCoaches.csv");
        
    }    

    @FXML
    private void btPlysHandler(ActionEvent event) throws IOException {
        currentScene = btPlayers.getScene();
        if(playersRoot == null)  { //keep the previous pane
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPlayers.fxml"));
            playersRoot = loader.load();
            plysController = (FXMLPlayersController) loader.getController();
            plysController.setMainRoot(currentScene.getRoot());
            plysController.setPlayersData(playersData);
            plysController.pageInit();
        }
        currentScene.setRoot(playersRoot);
    }

    @FXML
    private void btTmsHandler(ActionEvent event) throws IOException {
        currentScene = btTeams.getScene();
        if(teamsRoot == null)  { //keep the previous pane
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTeams.fxml"));
            teamsRoot = loader.load();
            teamsController = (FXMLTeamsController) loader.getController();
            teamsController.setMainRoot(currentScene.getRoot());
            teamsController.setPlayersData(playersData);
            teamsController.setTeamsData(teamsData);
            teamsController.setAccsData(accData);
        }
        teamsController.tableInit();
        
        currentScene.setRoot(teamsRoot);
    }

    @FXML
    private void btSchsHandler(ActionEvent event) throws IOException {
        currentScene = btSchedule.getScene();
        if(scheduleRoot == null)  { //keep the previous pane
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSchedule.fxml"));
            scheduleRoot = loader.load();
            schController = (FXMLScheduleController) loader.getController();
            schController.setMainRoot(currentScene.getRoot());
      
        }
        currentScene.setRoot(scheduleRoot);
    }

    @FXML
    private void btAccsHandler(ActionEvent event) throws IOException {
        currentScene = btAccounts.getScene();
        if(accsRoot == null)  { //keep the previous pane
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAccounts.fxml"));
            accsRoot = loader.load();
            accsController = (FXMLAccountsController) loader.getController();
            accsController.setMainRoot(currentScene.getRoot());
            accsController.setAccData(accData);
            accsController.tableInit();
            accsController.setTeamsData(teamsData);
        }
        currentScene.setRoot(accsRoot);
    }

    public void setAccData(AccountsData accData) {
        this.accData = accData;
    }
    
    
    
}
