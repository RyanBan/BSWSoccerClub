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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Wenfeng Ge
 */
public class FXMLPlayersController implements Initializable {
    
    @FXML
    private Button btBack;
    private Parent mainRoot;
    @FXML
    private Button btAdd;
    @FXML
    private Button btDel;
    @FXML
    private Button btEdit;
    @FXML
    private Button btSave;
    @FXML
    private Label lbMenu;
    @FXML
    private TextField tfName;
    @FXML
    private RadioButton rdoGirl;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton rdoBoy;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfId;
    @FXML
    private Button btSearch;
    @FXML
    private Button btSubmit;
    @FXML
    private Label lbError;
    @FXML
    private Pagination pagination;
    private PlayersData playersData;
    private TableView<Players> table = createTable();
    private int rowsPerPage = 19;
    private int maxPageIndex;
    @FXML
    private ComboBox<String> cboYear;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btSubmit.setDisable(true);
        btSearch.setDisable(true);
    }    
    public void pageInit(){
        System.out.println("player control have player"+playersData.getAllPlayers().size());
        calculateMaxPageIndex();
        pagination.setPageCount(maxPageIndex+1);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                if(pageIndex > maxPageIndex) {
                    return null;
                }
                else{
                    return createPage(pageIndex);
                }
            }
        });
        
    }
    private TableView<Players> createTable(){
        TableView<Players> newTable = new TableView<>();
        TableColumn<Players,Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn<Players,String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Players,Integer> colYear = new TableColumn<>("Birth Year");
        colYear.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
        
        TableColumn<Players,String> colGender = new TableColumn<>("Gender");
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        
        TableColumn<Players,String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        TableColumn<Players,String> colTeam = new TableColumn<>("Team");
        colTeam.setCellValueFactory(new PropertyValueFactory<>("team"));
        
        newTable.getColumns().addAll(colId,colName,colYear,colGender,colEmail,colTeam);
        return newTable;
    }
    
    private Node createPage(int pageIndex){
        int startIndex = pageIndex*rowsPerPage;
        int endIndex = Math.min(startIndex+rowsPerPage, playersData.getAllPlayers().size());
        table.setItems(FXCollections.observableArrayList(
                playersData.getAllPlayers().subList(startIndex, endIndex)));
        return table;
    }
    
    @FXML
    private void btBackHandler(ActionEvent event) {
        btSubmit.setDisable(true);
        btBack.getScene().setRoot(mainRoot);
        
    }

    public void setMainRoot(Parent mainRoot) {
        this.mainRoot = mainRoot;
    }

    @FXML
    private void btAddHandler(ActionEvent event) {
        lbMenu.setText("Complete the form then \nclick submit to add a new player!");
        lbError.setText("");
        tfName.setDisable(false);
        cboYear.setDisable(false);
        rdoBoy.setDisable(false);
        rdoGirl.setDisable(false);
        tfEmail.setDisable(false);
        cboYear.getItems().clear();
        cboYear.getItems().addAll("2007","2008");
        cboYear.setValue("2007");
        btSearch.setDisable(false);
        btSubmit.setDisable(false);
        btSubmit.setOnAction(e->{ addPlayer();});
    }
    
    private void addPlayer(){
        int inputId,inputYear,index;
        String inputName,inputGender,inputEmail;
       
        try{
            if(tfId.getText().equals("") || tfName.getText().equals("") || 
                    tfEmail.getText().equals("")){
                lbError.setText("No field can be empty!");
            }
            else{
                inputId = Integer.parseInt(tfId.getText());  
                index = playersData.getPlayerIndexById(inputId);
                if (index != -1 ) lbError.setText("Error:The player "+inputId+ " exists!");
                else {
                    inputName = tfName.getText();
                    if(cboYear.getValue().equals("2007")) inputYear = 2007;
                    else inputYear = 2008;
                    RadioButton rbSelected = (RadioButton) genderGroup.getSelectedToggle();
                    inputGender = rbSelected.getText().substring(0, 1);
                    inputEmail = tfEmail.getText();
                    Players newPlayer = new Players(inputId,inputName,inputYear,inputGender,
                            inputEmail,"UR");
                    playersData.getAllPlayers().add(newPlayer);
                    
                    lbMenu.setText("The new player has been added.\n Please save before close the program.");
                    lbError.setText("");
                    pageInit();
                }
            }
        }catch(Exception ex){
            lbError.setText("Id must be an integer!");
        }
    }
    
    @FXML
    private void btDelHandler(ActionEvent event) {
        lbError.setText("");
        lbMenu.setText("Search the player by id \nbefore you click submit to delete it!");
        tfName.setDisable(true);
        cboYear.setDisable(true);
        rdoBoy.setDisable(true);
        rdoGirl.setDisable(true);
        tfEmail.setDisable(true);
        btSubmit.setDisable(false);
        btSearch.setDisable(false);
        btSubmit.setOnAction(e->{ deletePlayer();});
    }
    
    private void deletePlayer(){
        int inputId,index;
        try{
            inputId = Integer.parseInt(tfId.getText());  
            index = playersData.getPlayerIndexById(inputId);
            if (index != -1 ) {
                    playersData.getAllPlayers().remove(index);                 
                    lbMenu.setText("The player has been deleted.\n"
                            + "Please save before close the program.");
                    lbError.setText("");
                    pageInit();
            }
            else{
                lbError.setText("Error:the player "+inputId+" doesn't exist!");
            }
            
        }catch(Exception ex){
            lbError.setText("Id must be an integer!");
        }
    }

    @FXML
    private void btEditHandler(ActionEvent event) {
        lbMenu.setText("Search the player by id before edit it,\nclick submit to make changes effective.");
        lbError.setText("");
        tfName.setDisable(false);
        cboYear.setDisable(true);
        rdoBoy.setDisable(true);
        rdoGirl.setDisable(true);
        tfEmail.setDisable(false);
       
        btSearch.setDisable(false);
        btSubmit.setDisable(false);
        btSubmit.setOnAction(e->{ editPlayer();});
        
    }
    private void editPlayer(){
        int inputId,index;
        String inputName,inputEmail;
       
        try{
            if(tfId.getText().equals("") || tfName.getText().equals("") || 
                    tfEmail.getText().equals("")){
                lbError.setText("No field can be empty!");
            }
            else{
                inputId = Integer.parseInt(tfId.getText());  
                index = playersData.getPlayerIndexById(inputId);
                if (index == -1 ) {
                    lbError.setText("Error:The player "+inputId+ " doesn't exist!");
                }
                else {
                    inputName = tfName.getText();
                    
                    inputEmail = tfEmail.getText();
                    
                    playersData.getAllPlayers().get(index).setName(inputName);
                   
                    playersData.getAllPlayers().get(index).setEmail(inputEmail);
                    
                    lbMenu.setText("The player's data has been changed.\n Please save before close the program.");
                    lbError.setText("");
                    pageInit();
                }
            }
        }catch(Exception ex){
            lbError.setText("Id must be an integer!");
        }
        
    }
    @FXML
    private void btSaveHandler(ActionEvent event) throws FileNotFoundException {
        lbError.setText("");
        btSubmit.setDisable(true);
        playersData.saveData("playersData.csv");
        lbMenu.setText("Players data have been saved to file!");
    }

    @FXML
    private void btSearchHandler(ActionEvent event) {
        lbError.setText("");
        lbMenu.setText("");
        tfName.setText("");
        tfEmail.setText("");
        int id,index;
        try{
            id = Integer.parseInt(tfId.getText());
            index = playersData.getPlayerIndexById(id);
            if(index!=-1){
                lbMenu.setText("The player "+id+" exists!");
                tfName.setText(playersData.getAllPlayers().get(index).getName());
                if(playersData.getAllPlayers().get(index).getBirthYear()==2007){
                    cboYear.setValue("2007");
                }
                else cboYear.setValue("2008");
                if(playersData.getAllPlayers().get(index).getGender().equals("G")){
                    rdoGirl.setSelected(true);
                    rdoBoy.setSelected(false);
                }
                else{
                    rdoGirl.setSelected(false);
                    rdoBoy.setSelected(true);
                }
                tfEmail.setText(playersData.getAllPlayers().get(index).getEmail());
                
            }
            else{
                lbMenu.setText("The player "+id+ " doesn't exists!");
            }
        }catch(Exception ex){
            lbError.setText("id must be an integer!");
        }
        
    }

    public void setPlayersData(PlayersData playersData) {
        this.playersData = playersData;
    }

    public void calculateMaxPageIndex() {
        int dataSize = this.playersData.getAllPlayers().size();
        if(dataSize%rowsPerPage == 0) maxPageIndex = dataSize/rowsPerPage - 1;
        else maxPageIndex = dataSize/rowsPerPage;
    }
    
}
