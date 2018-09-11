/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prometheustest;

import java.io.IOException;
import java.net.URL;
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
    private ComboBox comboBox1;
    @FXML
    private ComboBox comboBox2;
    @FXML
    private ComboBox comboBox3;
    @FXML
    private Button submit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBox1.getItems().addAll("3979 - Information Systems",
                "3584 - Commerce / Information Systems",
                "3964 - BIS (Co-op)");

        comboBox2.getItems().addAll("2015", "2016", "2017", "2018");

        comboBox3.getItems().addAll("2018", "2019", "2020", "2021");
    }

    public void submitPressed(ActionEvent event) throws IOException {
        String course = comboBox1.getValue().toString();
        int startingYear = Integer.parseInt(comboBox2.getValue().toString());
        int completionYear = Integer.parseInt(comboBox3.getValue().toString());
        
        /*System.out.println("Course chosen "+course);
        System.out.println("Year of commencement: "+comboBox2.getValue().toString());
        System.out.println("Year of completion: "+comboBox3.getValue().toString());
         */

        UserProfile profile = new UserProfile(course, startingYear, completionYear);
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Dashboard.fxml"));
        Parent dashboardParent = loader.load();

        Scene dashboardScene = new Scene(dashboardParent);

        DashboardController controller = loader.getController();
        controller.initData(profile);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();

    }

}
