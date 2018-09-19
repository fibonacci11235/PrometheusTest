/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prometheustest;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author josiah
 */
public class Dashboard1Controller extends ParentDashboardController {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //subjectList.getItems().addAll("INFS1602", "INFS1603", "INFS1609", "ACCT1501");
        
        
       
        
        //Assigns Drag and Drop functionality to trimester containers
        super.establishHandlers();
        
        establishToolTip();
             
    }  
    
   
}
