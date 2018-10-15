/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prometheustest;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author josiah
 */
public class Dashboard4Controller extends ParentDashboardController {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.establishHandlers();
        super.dashboard2Handlers();
        establishToolTip();
        establishToolTip2();
        progressionWheelArrays();
    }    
    
    @Override
    public void initData(UserProfile profile) throws ClassNotFoundException, SQLException{
        currentUser = profile;
        courseTitle.setText(currentUser.getCourse());
        startYear.setText(Integer.toString(currentUser.getStartingYear()));
        secondYear.setText(Integer.toString(currentUser.getStartingYear()+1));
        thirdYear.setText(Integer.toString(currentUser.getStartingYear()+2));
        fourthYear.setText(Integer.toString(currentUser.getStartingYear()+3));
        super.establishList();
    }
    
    @FXML
    @Override
    public void clearTrimesters() throws ClassNotFoundException, SQLException {
       subjectList.getItems().clear();
       trimester1.getItems().clear();
       trimester2.getItems().clear();
       trimester3.getItems().clear();
       trimester4.getItems().clear();
       trimester5.getItems().clear();
       trimester6.getItems().clear();
       trimester7.getItems().clear();
       trimester8.getItems().clear();
       trimester9.getItems().clear();
       trimester10.getItems().clear();
       trimester11.getItems().clear();
       trimester12.getItems().clear();
       temporaryList.clear();
       clearUnitProgress();
       super.establishList();
   }
    
   @Override
   public void progressionWheelIncrement() {
        if (businessCores2.contains(tempSubject)) {
           businessCores2Units += dragUnits;
           businessCores2Gauge.setValue(businessCores2Units);
        }
        else if (informationSystemsCores.contains(tempSubject)) {
           ISCoresUnits += dragUnits;
           ISCoresGauge.setValue(ISCoresUnits);
        }
        else if (flexibleCores.contains(tempSubject)) {
            flexibleCoresUnits += dragUnits; 
            flexibleCoresGauge.setValue(flexibleCoresUnits);
        }
        else if (!"UNSW Business School".equals(tempFaculty)) {
            generalEducationUnits += dragUnits;
            generalEducationGauge.setValue(generalEducationUnits);
        }
        else {
            if (temporaryList.contains(subjectOffered)) {
                //Do nothing
            }
            else {
                businessElectivesUnits += dragUnits;
                businessElectivesGauge.setValue(businessElectivesUnits);
            }
        }
   }
   
   @Override 
   public void totalUnitTracker() {
       
       totalUnits += tempUnits;
       totalUnitLabel.setText(Integer.toString(totalUnits));
       double x = (double) totalUnits;
       double progress = x/192;
       unitProgressBar.setProgress(progress);
       tempUnits = 0;
   }
   
   @Override
   public void clearUnitProgress() {
       totalUnits = 0;
       totalUnitLabel.setText(Integer.toString(totalUnits));
       unitProgressBar.setProgress(0);
       
       businessCores2Units = 0;
       ISCoresUnits = 0;
       flexibleCoresUnits = 0; 
       businessElectivesUnits = 0;
       generalEducationUnits = 0;
       
       businessCores2Gauge.setValue(0);
       ISCoresGauge.setValue(0);
       flexibleCoresGauge.setValue(0);
       businessElectivesGauge.setValue(0);
       generalEducationGauge.setValue(0);
   }
   
}
