/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Wenfeng Ge
 */
public class FXMLScheduleController implements Initializable {
    private Parent mainRoot;
    
    @FXML
    private Button btSave;
    @FXML
    private Button btBack;
    @FXML
    private Button btSubmit;
    @FXML
    private Label lbError;
    @FXML
    private TableView<Schedule> tableDisplay;
    @FXML
    private TableColumn<Schedule, String> colweek;
    @FXML
    private TableColumn<Schedule, String> colTime;
    @FXML
    private TableColumn<Schedule, String> colTeams;
    @FXML
    private TableColumn<Schedule, String> colField;
    
    private ObservableList<Schedule> schedulesGU11;
    private ObservableList<Schedule> schedulesBU11;
    private ObservableList<Schedule> schedulesGU12;
    private ObservableList<Schedule> schedulesBU12;
    private ArrayList<Schedule> sGU11 = new ArrayList<>();
    private ArrayList<Schedule> sBU11 = new ArrayList<>();
    private ArrayList<Schedule> sGU12 = new ArrayList<>();
    private ArrayList<Schedule> sBU12 = new ArrayList<>();
    
    @FXML
    private ComboBox<String> cboU11;
    @FXML
    private ComboBox<String> cboU12;
    @FXML
    private ComboBox<String> cboDisplayGroup;
    @FXML
    private Label lbNote;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            load("schedulesData.csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        schedulesGU11 = FXCollections.observableArrayList(sGU11);
        schedulesBU11 = FXCollections.observableArrayList(sBU11);
        schedulesGU12 = FXCollections.observableArrayList(sGU12);
        schedulesBU12 = FXCollections.observableArrayList(sBU12);
        tableInit();
        cboDisplayGroup.getItems().addAll("GU11","BU11","GU12","BU12");
        cboDisplayGroup.setValue("GU11");
        
        cboU11.getItems().addAll("Saturday 10:00am to 11:00am",
                "Saturday 3:00pm to 4:00pm","Sunday 10:00am to 11:00am",
                "Sunday 3:00pm to 4:00pm");
        cboU11.setValue("Saturday 10:00am to 11:00am");
        
        cboU12.getItems().addAll("Saturday 10:00am to 11:00am",
                "Saturday 3:00pm to 4:00pm","Sunday 10:00am to 11:00am",
                "Sunday 3:00pm to 4:00pm");
        cboU12.setValue("Sunday 10:00am to 11:00am");
    }    
    
    public void tableInit(){
        tableDisplay.setItems(schedulesGU11);
        colweek.setCellValueFactory(new PropertyValueFactory<>("week"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTeams.setCellValueFactory(new PropertyValueFactory<>("teams"));
        colField.setCellValueFactory(new PropertyValueFactory<>("field"));
    }
    
    @FXML
    private void btSaveHandler(ActionEvent event) throws FileNotFoundException {
        save("schedulesData.csv");
        
    }

    @FXML
    private void btBackHandler(ActionEvent event) {
        btBack.getScene().setRoot(mainRoot);
    }

    @FXML
    private void btSubmitHandler(ActionEvent event) {
        if(cboU11.getValue().equals(cboU12.getValue())){
            lbError.setText("Two group cannot choose the same time!");
        }
        else{
            lbError.setText("");
            for(int i=0;i<12;i++){
                sGU11.get(i).setTime(cboU11.getValue());
                sBU11.get(i).setTime(cboU11.getValue());
                sGU12.get(i).setTime(cboU12.getValue());
                sBU12.get(i).setTime(cboU12.getValue());
            }
        }
        
    }
    
    private void load(String fileName) throws FileNotFoundException{
        Scanner fileReader = null;
         try{
            File dataFile=new File(fileName);
            fileReader = new Scanner(dataFile);
            fileReader.useDelimiter(System.getProperty("line.separator"));
            for(int i=0;i<48;i++){
                String[] dataLine = fileReader.next().split(",");
                String week  = dataLine[0];
                String time = dataLine[1];
                String teams = dataLine[2];
                String field = dataLine[3];
                Schedule newSchedule = new Schedule(week,time,teams,field);
                switch(i/12){
                    case 0:sGU11.add(newSchedule);
                    break;
                    case 1:sBU11.add(newSchedule);
                    break;
                    case 2:sGU12.add(newSchedule);
                    break;
                    case 3:sBU12.add(newSchedule);
                        
                }
            }
            System.out.println(sGU11.size()+","+sBU11.size());            
         }
         catch(Exception ex){
               System.out.print(ex);             
         }finally{
             fileReader.close();
         }
    }
    
    private void save(String fileName) throws FileNotFoundException{
        File f=new File(fileName);
        PrintWriter writer = null;
        try{
            writer=new PrintWriter(f);   
            for(int i=0;i<48;i++){
                switch(i/12){
                    case 0: writer.println(sGU11.get(i));
                    break;
                    case 1:writer.println(sBU11.get(i-12));
                    break;
                    case 2:writer.println(sGU12.get(i-24));
                    break;
                    case 3:writer.println(sBU12.get(i-36));                        
                }
            }
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }finally {
            writer.close();
       }
        
    }

    public void setMainRoot(Parent mainRoot) {
        this.mainRoot = mainRoot;
    }


    @FXML
    private void tableDisplayHandler(ActionEvent event) {
        String displayChoice = cboDisplayGroup.getValue();
        switch(displayChoice){
            case "GU11":tableDisplay.setItems(schedulesGU11);
            break;
            case "BU11":tableDisplay.setItems(schedulesBU11);
            break;
            case "GU12":tableDisplay.setItems(schedulesGU12);
            break;
            case "BU12":tableDisplay.setItems(schedulesBU12);
        }
    }
    
    
}
