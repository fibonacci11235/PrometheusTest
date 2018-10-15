/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prometheustest;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author josiah
 */
public class LandingController implements Initializable {

    @FXML
    private JFXComboBox comboBox1;
    @FXML
    private JFXComboBox comboBox2;
    @FXML
    private JFXComboBox comboBox3;
    @FXML
    private JFXButton submit;
    
    private String resource;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBox1.getItems().addAll("3979 - Information Systems",
                "3584 - Commerce / Information Systems",
                "3964 - BIS (Co-op)");

        comboBox2.getItems().addAll("2018", "2019", "2020", "2021");

        comboBox3.getItems().addAll("3 years", "4 years", "5 years");
    }

    public void submitPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        String course = comboBox1.getValue().toString();
        int startingYear = Integer.parseInt(comboBox2.getValue().toString());
        String duration = comboBox3.getValue().toString();
        
        UserProfile profile = new UserProfile(course, startingYear, duration);
        
       
        FXMLLoader loader = new FXMLLoader();
        
        if ("3979 - Information Systems".equals(course) && "3 years".equals(duration)) {
            resource = "Dashboard1.fxml";
            loader.setLocation(getClass().getResource(resource));
            Parent dashboard1Parent = loader.load();
            Scene dashboard1Scene = new Scene(dashboard1Parent);
            Dashboard1Controller controller = loader.getController();
            controller.initData(profile);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(dashboard1Scene);
            window.show();
        }
        else if ("3979 - Information Systems".equals(course) && "4 years".equals(duration)) {
            resource = "Dashboard2.fxml";
            loader.setLocation(getClass().getResource(resource));
            Parent dashboard2Parent = loader.load();
            Scene dashboard2Scene = new Scene(dashboard2Parent);
            Dashboard2Controller controller2 = loader.getController();
            controller2.initData(profile);
            Stage window2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            window2.setScene(dashboard2Scene);
            window2.show();
        }
        else if ("3979 - Information Systems".equals(course) && "5 years".equals(duration)) {
            resource = "Dashboard3.fxml";
            loader.setLocation(getClass().getResource(resource));
            Parent dashboard3Parent = loader.load();
            Scene dashboard3Scene = new Scene(dashboard3Parent);
            Dashboard3Controller controller3 = loader.getController();
            controller3.initData(profile);
            Stage window3 = (Stage)((Node)event.getSource()).getScene().getWindow();
            window3.setScene(dashboard3Scene);
            window3.show();
        }
        else if ("3584 - Commerce / Information Systems".equals(course) && "4 years".equals(duration)) {
            resource = "Dashboard4.fxml";
            loader.setLocation(getClass().getResource(resource));
            Parent dashboard4Parent = loader.load();
            Scene dashboard4Scene = new Scene(dashboard4Parent);
            Dashboard4Controller controller4 = loader.getController();
            controller4.initData(profile);
            Stage window4 = (Stage)((Node)event.getSource()).getScene().getWindow();
            window4.setScene(dashboard4Scene);
            window4.show();
        
        }
        else if ("3584 - Commerce / Information Systems".equals(course) && "5 years".equals(duration)) {
            
        
        }           
        
    }

}
