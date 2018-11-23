/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author banse
 */
public class FXMLCoachDocumentController implements Initializable {

    @FXML
    private ListView<String> lv;
    protected ObservableList<Students> studentList2 = FXCollections.observableArrayList();
    protected ArrayList<Students> studentList = new ArrayList<Students>();

    public Scanner parse;

    @FXML
    private RadioButton teamA;
    @FXML
    private RadioButton teamB;
    @FXML
    private RadioButton teamC;
    @FXML
    private Button chooseEmailBtn;
    @FXML
    private TextField emailTitle;
    @FXML
    private TextField emailText;
    @FXML
    private Button sendBtn;
    @FXML
    private ToggleGroup team;
    @FXML
    private Label showResult;
    @FXML
    private TextField emailAd;
    @FXML
    private Label navi;
    @FXML
    private RadioButton teamD;
    @FXML
    private ImageView iv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //chooseEmailBtn.setDisable(true);
        //chooseEmailBtn.setDisable(false);
        iv.setVisible(true);
    }
    File teamA_file = new File("users.d1");
    File teamB_file = new File("users.d2");
    File teamC_file = new File("users.d3");
    File teamD_file = new File("users.d4");

    public void run(File file) throws FileNotFoundException {
        studentList.clear();
        Scanner line = new Scanner(file);
        line.useDelimiter("\r\n");

        while (line.hasNext()) {

            String s1 = line.next();
            Students student = new Students();
            student.loadStudent(s1);
            studentList.add(student);
        }
        for (Students stu : studentList) {
            lv.getItems().add(stu.getName());
        }
        line.close();
    }

    @FXML
    private void teamAHandle(ActionEvent event) throws FileNotFoundException {
        lv.getItems().clear();
        run(teamA_file);
    }

    @FXML
    private void teamBHandle(ActionEvent event) throws FileNotFoundException {
        lv.getItems().clear();
        run(teamB_file);
    }

    @FXML
    private void teamCHandle(ActionEvent event) throws FileNotFoundException {
        lv.getItems().clear();
        run(teamC_file);
    }   
    
    @FXML
    private void teamDHandle(ActionEvent event) throws FileNotFoundException {
        lv.getItems().clear();
        run(teamD_file);
    }

    @FXML
    private void chooseEmailBtnHandle(ActionEvent event) {

        int index = lv.getSelectionModel().getSelectedIndex();
        String emailAddress = studentList.get(index).getEmail();
        emailAd.setText(emailAddress);
    }

    @FXML
    private void sendBtnHandle(ActionEvent event) {
        
        String toEmail = emailAd.getText();
    
        String title = emailTitle.getText();

        String msg = emailText.getText();


        String[] to = {toEmail};
        if (EmailSender.sendMail("ban981127@gmail.com", "zack1127", title, msg, to)) {
            showResult.setText("Email sent successfuly!");
        } else {
            showResult.setText("Sorry..error...");
        }   
    }


}
