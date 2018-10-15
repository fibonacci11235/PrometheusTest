/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prometheustest;

import com.jfoenix.controls.*;
import eu.hansolo.enzo.gauge.FlatGauge;
import java.awt.AWTException;

import java.awt.Desktop;


import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
/**
 *
 * @author josiah
 */
public abstract class ParentDashboardController implements Initializable {
    public UserProfile currentUser;
    private Stage stage;
    
  
    @FXML
    public Label courseTitle;
    @FXML
    public Label startYear;
    @FXML
    public JFXButton backButton;
    @FXML
    public Button recommend;
    @FXML
    public JFXButton clear;
    @FXML
    public JFXButton webLinkButton;
    @FXML
    public JFXButton savePlan;
    @FXML
    public ListView subjectList;
    @FXML
    public ListView trimester1;
    @FXML
    public ListView trimester2;
    @FXML
    public ListView trimester3;
    @FXML
    public ListView trimester4;
    @FXML
    public ListView trimester5;
    @FXML
    public ListView trimester6;
    @FXML
    public ListView trimester7;
    @FXML
    public ListView trimester8;
    @FXML
    public ListView trimester9;
    @FXML
    public ListView trimester10;
    @FXML
    public ListView trimester11;
    @FXML
    public ListView trimester12;
    @FXML
    public ListView trimester13;
    @FXML
    public ListView trimester14;
    @FXML
    public ListView trimester15;
    
    @FXML
    public Label secondYear;
    @FXML
    public Label thirdYear;
    @FXML
    public Label fourthYear;
    @FXML
    public Label fifthYear;
    @FXML
    private TextField alertField;
    
    static final DataFormat SUBJECT_LIST = new DataFormat("SubjectList");
    
    
    
    public String trimesterOffering;
    public String subjectOffered;
    
    
    
    private String sql = "SELECT * FROM COURSE ORDER BY COURSE_CODE";
    @FXML
    public JFXTextField courseSearch;
    @FXML
    public JFXButton courseSearchButton; 
    
    // Progress bar
    @FXML
    public Label totalUnitLabel;
    public int tempUnits;
    public int totalUnits = 0;
    
    @FXML
    public JFXProgressBar unitProgressBar; 
    
    //Temporary Variables 
    List<String> temporaryList = new ArrayList<>();
    public String tempSubject;
    public int dragUnits;
    public String tempPrerequisites;
    public String listViewSource;
    public String tempFaculty;
    
    
    //ProgressionWheelArrays
    List<String> businessCores = new ArrayList<>();
    List<String> businessCores2 = new ArrayList<>();
    List<String> informationSystemsCores = new ArrayList<>();
    List<String> prescribedElectives = new ArrayList<>();
    List<String> flexibleCores = new ArrayList<>();
    List<String> level1Cores = new ArrayList<>();
    List<String> level2Cores = new ArrayList<>();
    List<String> level3Cores = new ArrayList<>();
    
    
    
    public int businessCoresUnits = 0;
    public int ISCoresUnits = 0;
    public int prescribedElectivesUnits = 0; 
    public int freeElectivesUnits = 0;
    public int generalEducationUnits = 0;
    public int businessCores2Units = 0;
    public int flexibleCoresUnits = 0;
    public int businessElectivesUnits = 0;
    
    @FXML
    public JFXButton businessCoresButton;
    @FXML
    public JFXButton ISCoresButton;
    @FXML
    public JFXButton prescribedElectivesButton;
    @FXML
    public JFXButton freeElectivesButton;
    @FXML
    public JFXButton generalEducationButton;
    @FXML
    public JFXButton businessCores2Button;
    @FXML
    public JFXButton flexibleCoresButton;
    @FXML
    public JFXButton businessElectivesButton;
    
    @FXML
    public FlatGauge businessCoresGauge; 
    @FXML 
    public FlatGauge ISCoresGauge;
    @FXML
    public FlatGauge prescribedElectivesGauge;
    @FXML
    public FlatGauge freeElectivesGauge;
    @FXML
    public FlatGauge generalEducationGauge;
    @FXML
    public FlatGauge businessCores2Gauge;
    @FXML
    public FlatGauge flexibleCoresGauge;
    @FXML
    public FlatGauge businessElectivesGauge;
    
    @FXML
    public Rectangle snapshotRectangle;
    @FXML
    public AnchorPane anchorPane;
    
    
    //Subjects
//    private Subject INFS1602 = new Subject("INFS1602", 6, "Digital Transformation in Business", "", "T1, T2, T3");
//    private Subject INFS1603 = new Subject("INFS1603", 6, "Introduction to Business Databases", "", "T1, T2");
//    private Subject INFS1609 = new Subject("INFS1609", 6, "Fundamentals of Business Programming", "", "T1, T3");
//    private Subject ACCT1501 = new Subject("ACCT1501", 6, "Accounting and Financial Management 1A", "", "T1, T2, T3");
//    private Subject MGMT1001 = new Subject("MGMT1001", 6, "Managing Organisations and People", "", "T1, T2, T3");
//    private Subject ECON1101 = new Subject("ECON1101", 6, "Microeconomics 1", "", "T1, T2, T3");
//    private Subject MATH1041 = new Subject("MATH1041", 6, "Statistics for Life and Social Sciences", "", "T1, T2, T3");
//    private Subject ECON1203 = new Subject("ECON1203", 6, "Business and Economic Statistics", "", "T1, T2, T3");
//    private Subject ACCT1511 = new Subject("ACCT1511", 6, "Accounting and Financial Management 1B", "ACCT1501", "T1, T2");
//    
//    private Subject INFS2603 = new Subject("INFS2603", 6, "Business Analysis", "INFS1602, INFS1603", "T3");
//    private Subject INFS2605 = new Subject("INFS2605", 6, "Intermediate Business Programming", "INFS1603, INFS1609", "T1, T2");
//    private Subject INFS2608 = new Subject("INFS2608", 6, "Database Management & Big Data Infrastructures", "INFS1603", "T1");
//    private Subject INFS2621 = new Subject("INFS2621", 6, "Enterprise Systems", "INFS1602", "T2");
//    private Subject INFS2631 = new Subject("INFS2631", 6, "Innovation and Technology Management", "INFS1602", "T1");
//    
//    private Subject INFS3603 = new Subject("INFS3603", 6, "Introduction to Business Analytics", "INFS1602, 72 UOC", "T1, T2");
//    private Subject INFS3617 = new Subject("INFS3617", 6, "Networking & Cyber Security", "INFS1602, 72 UOC", "T1");
//    private Subject INFS3020 = new Subject("INFS3020", 6, "International IS/IT Practicum", "Minimum WAM of 60, 96 UOC (36UOC INFS)", "T2");
//    private Subject INFS3632 = new Subject("INFS3632", 6, "Service and Quality Management", "INFS1602, 72 UOC", "T2");
//    private Subject INFS3604 = new Subject("INFS3604", 6, "Business Process Management", "INFS2603, 72 UOC", "T3");
//    private Subject INFS3873 = new Subject("INFS3873", 6, "Business Analytics Methods", "INFS3603, 72 UOC", "T3");
//    private Subject INFS3634 = new Subject("INFS3634", 6, "Mobile Applications Development", "INFS2605, INFS2608, 72 UOC", "T1, T3");
//    private Subject INFS3830 = new Subject("INFS3830", 6, "Social Media and Analytics", "INFS3603, 72 UOC", "T1");
//    private Subject INFS3605 = new Subject("INFS3605", 6, "IS Innovation & Transformation", "INFS3634 or INFS3611, 72 UOC", "T1, T2");
//    //BIS (Co-op) Exclusive
//    private Subject INFS2101 = new Subject("INFS2101", 12, "Industry Placement 1", "INFS2603", "T1");
//    private Subject INFS3202 = new Subject("INFS3202", 12, "Industry Pacement 2", "INFS2101", "T1");
//    private Subject INFS3303 = new Subject("INFS3303", 12, "Industry Placement 3", "INFS3202", "T2, T3");
//    private Subject INFS4800 = new Subject("INFS4800", 6, "Thesis A", "Enrolled in Honours", "T1");
//    private Subject INFS4801 = new Subject("INFS4801", 6, "Thesis B", "INFS4800", "T1");
//    private Subject INFS4802 = new Subject("INFS4802", 6, "Thesis C", "TBA", "TBA");
//    private Subject INFS4886 = new Subject("INFS4886", 6, "Principles of Research Design", "Enrolled in Honours", "T3");
//    private Subject INFS4887 = new Subject("INFS4887", 6, "Business Research Methods", "Enrolled in Honours, INFS4886", "T1");
//    private Subject INFS4831 = new Subject("INFS4831", 6, "Information Systems Consulting", "Enrolled in Honours", "T1");
//    private Subject INFS4858 = new Subject("INFS4858", 6, "Managing Complex Projects", "Enrolled in Honours", "T1");
//    private Subject INFS4907 = new Subject("INFS4907", 6, "Managing Security and Ethics in Cyberspace", "Enroleld in Honours", "T2");
//    //Comm/InfoSys Exclusive
//    private Subject COMM1000 = new Subject("COMM1000", 6, "Creating Social Change", "", "T1, T2, T3");
//    private Subject ECON1102 = new Subject("ECON1102", 6, "Macroeconomics 1", "ECON1101", "T1, T2");
//    private Subject FINS1613 = new Subject("FINS1613", 6, "Business Finance", "", "T1, T2, T3");
//    private Subject MARK1012 = new Subject("MARK1012", 6, "Marketing Fundamentals", "", "T1, T2, T3");
//    private Subject MGMT1101 = new Subject("MGMT1101", 6, "Global Business Environment", "", "T1, T2, T3");
//    private Subject TABL1710 = new Subject("TABL1710", 6, "Business and the Law", "Not enrolled in Law programs", "T1, T2, T3");
//            
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        //subjectList.getItems().addAll("INFS1602", "INFS1603", "INFS1609", "ACCT1501");
        
        
        
        
        //Assigns Drag and Drop functionality to trimester containers
        establishHandlers();
        
             
    }    

   
    
    @FXML
    public void backPressed(ActionEvent event) throws IOException{
      
        Parent dashboard = FXMLLoader.load(getClass().getResource("Landing.fxml"));
        Scene dashboardScene = new Scene (dashboard);
        
        Stage window = window = (Stage)((Node)event.getSource()).getScene().getWindow();
       
        window.setScene(dashboardScene);
        window.show();
        
    }
    
    
    public void initData(UserProfile profile) throws ClassNotFoundException, SQLException{
        currentUser = profile;
        courseTitle.setText(currentUser.getCourse());
        startYear.setText(Integer.toString(currentUser.getStartingYear()));
        secondYear.setText(Integer.toString(currentUser.getStartingYear()+1));
        thirdYear.setText(Integer.toString(currentUser.getStartingYear()+2));
        establishList();
    }
        
     
    public void establishList() throws ClassNotFoundException, SQLException {
        subjectList.getItems().addAll(this.getSubjectList());
       
    }
    
    private ObservableList<Subject> getSubjectList() throws ClassNotFoundException, SQLException {
        
        ObservableList<Subject> list = FXCollections.observableArrayList(loadCourseDB());
        
        
//        if ("3979 - Information Systems".equals(currentUser.getCourse())) {
//            list.addAll(INFS1602, INFS1603, INFS1609, ACCT1501, MGMT1001, ECON1101, 
//                        ECON12037, INFS2603, INFS2605, INFS2608, ACCT1511, INFS2621,
//                        INFS3603, INFS361, INFS2631, INFS3020, INFS3632, INFS3604,
//                        INFS3873, INFS3634, INFS3830, INFS3605);
//        }
//        else if ("3584 - Commerce / Information Systems".equals(currentUser.getCourse())) {
//            list.addAll(INFS1602, INFS1603, INFS1609, ACCT1501, MGMT1001, ECON1101, 
//                        ECON1203, INFS2603, INFS2605, INFS2608, ACCT1511, INFS2621,
//                        INFS3603, INFS3617, INFS2631, INFS3020, INFS3632, INFS3604,
//                        INFS3873, INFS3634, INFS3830, INFS3605, COMM1000, ECON1102,
//                        FINS1613, MARK1012, MGMT1101, TABL1710); 
//         }
//        else if ("3964 - BIS (Co-op)".equals(currentUser.getCourse())) {
//             list.addAll(INFS1602, INFS1603, INFS1609, ACCT1501, MGMT1001, ECON1101, 
//                        ECON1203, INFS2603, INFS2605, INFS2608, ACCT1511, INFS2621,
//                        INFS3603, INFS3617, INFS2631, INFS3020, INFS3632, INFS3604,
//                        INFS3873, INFS3634, INFS3830, INFS3605, INFS2101, INFS3202,
//                        INFS3303, INFS4800, INFS4801, INFS4802, INFS4886, INFS4887,
//                        INFS4831, INFS4858, INFS4907);
//         }
        
                        
        return list;
    }
      
    
    
    private void dragDetected(MouseEvent event, ListView<Subject> listView) {
        int selectedCount = listView.getSelectionModel().getSelectedIndices().size();
        
        if (selectedCount == 0) {
            event.consume();
            return;
        }
        Subject subject = listView.getSelectionModel().getSelectedItem();
        if (listView == subjectList) {
            tempUnits = subject.getSubjectUnits();
            tempSubject = subject.getSubjectCode(); 
            listViewSource = "subjectList";
            tempFaculty = subject.getFaculty();
        }
        else {
            listViewSource = "trimester";
        }
        subjectOffered = subject.getSubjectCode();
        dragUnits = subject.getSubjectUnits();
        trimesterOffering = subject.getOffering();
        tempPrerequisites = subject.getPrerequisites();
        
        Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
        
        ArrayList<Subject> selectedItems = this.getSelectedSubjects(listView);
        
        ClipboardContent content = new ClipboardContent();
        content.put(SUBJECT_LIST, selectedItems);
        
        dragboard.setContent(content);
        event.consume();
        }
    
    private void dragOver(DragEvent event, ListView<Subject> listView) {
        Dragboard dragboard = event.getDragboard();
        
        if (event.getGestureSource() != listView && dragboard.hasContent(SUBJECT_LIST)) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }
    
    private void dragDropped(DragEvent event, ListView<Subject> listView) {
        boolean dragCompleted = false;
        Dragboard dragboard = event.getDragboard();
        if (listView != subjectList && sumUnits(listView) > 18) {
            alert("Adding subject will exceed maximum trimester load of 18 UOC.");
            listView.getSelectionModel().clearSelection();
            subjectList.getSelectionModel().clearSelection();
            tempSubject = null;
            tempUnits = 0;
            dragCompleted = false;
        }
        else if (listView == subjectList) {
            dragCompleted = false;
        }
        else if (temporaryList.contains(tempSubject)) {
            alert("Subject already contained within plan.");
            tempSubject = null;
            tempUnits = 0;
            dragCompleted = false;
        }
        else if (prerequisiteCheck() == false) {
            dragCompleted = false;
        }
        else {
            if(dragboard.hasContent(SUBJECT_LIST) && trimesterOfferingCheck(listView)) {
                ArrayList<Subject> list = (ArrayList<Subject>)dragboard.getContent(SUBJECT_LIST);
                listView.getItems().addAll(list);
                totalUnitTracker();
                progressionWheelIncrement();
                alert(" ");
                dragCompleted = true;            
            } 
            else {
                dragCompleted = false;
            }
        }
        
        
        event.setDropCompleted(dragCompleted);
        event.consume();  
    }
    
    private void dragDone(DragEvent event, ListView<Subject> listView) {
        TransferMode tm = event.getTransferMode();
        if (tm == TransferMode.MOVE) {
            removeSelectedSubjects(listView);
            if (tempSubject != null) {
                temporaryList.add(tempSubject); //Adds chosen subject to a temporary arraylist
                System.out.println(tempPrerequisites);
                tempSubject = null;
                tempPrerequisites = null;
                tempFaculty = "UNSW Business School";
            }
        }
        event.consume();
    }
    
    private ArrayList<Subject> getSelectedSubjects(ListView<Subject> listView) {
        ArrayList<Subject> list = new ArrayList<>(listView.getSelectionModel().getSelectedItems());
        return list;
    }
    
    private void removeSelectedSubjects(ListView<Subject> listView) {
        List<Subject> selectedList = new ArrayList<>();
        for (Subject subject : listView.getSelectionModel().getSelectedItems()) {
            selectedList.add(subject);
        }
        listView.getSelectionModel().clearSelection();
        listView.getItems().removeAll(selectedList);
    }
    
    private void alert(String text) {
        this.alertField.setText(text);
    }
    
    public void establishHandlers(){
              
        subjectList.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, subjectList);
        });
        subjectList.setOnDragOver((DragEvent event) -> {
            dragOver(event, subjectList);
        });
        subjectList.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, subjectList);
        });
        subjectList.setOnDragDone((DragEvent event) -> {
            dragDone(event, subjectList);
        });
        
        trimester1.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester1);
        });
        trimester1.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester1);
        });
        trimester1.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester1);
        });
        trimester1.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester1);
        });
        
        trimester2.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester2);
        });
        trimester2.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester2);
        });
        trimester2.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester2);
        });
        trimester2.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester2);
        });
        
        trimester3.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester3);
        });
        trimester3.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester3);
        });
        trimester3.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester3);
        });
        trimester3.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester3);
        });
        
        trimester4.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester4);
        });
        trimester4.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester4);
        });
        trimester4.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester4);
        });
        trimester4.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester4);
        });
        
        trimester5.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester5);
        });
        trimester5.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester5);
        });
        trimester5.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester5);
        });
        trimester5.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester5);
        });
        
        trimester6.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester6);
        });
        trimester6.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester6);
        });
        trimester6.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester6);
        });
        trimester6.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester6);
        });
        
        trimester7.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester7);
        });
        trimester7.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester7);
        });
        trimester7.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester7);
        });
        trimester7.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester7);
        });
        
        trimester8.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester8);
        });
        trimester8.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester8);
        });
        trimester8.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester8);
        });
        trimester8.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester8);
        });
        
        trimester9.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester9);
        });
        trimester9.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester9);
        });
        trimester9.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester9);
        });
        trimester9.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester9);
        });
        
        
    }
    
   public void dashboard2Handlers() {
        trimester10.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester10);
        });
        trimester10.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester10);
        });
        trimester10.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester10);
        });
        trimester10.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester10);
        });
        
         trimester11.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester11);
        });
        trimester11.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester11);
        });
        trimester11.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester11);
        });
        trimester11.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester11);
        });
        
         trimester12.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester12);
        });
        trimester12.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester12);
        });
        trimester12.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester12);
        });
        trimester12.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester12);
        });
    }
   
   public void dashboard3Handlers() {
       trimester13.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester13);
        });
        trimester13.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester13);
        });
        trimester13.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester13);
        });
        trimester13.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester13);
        });
        
         trimester14.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester14);
        });
        trimester14.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester14);
        });
        trimester14.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester14);
        });
        trimester14.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester14);
        });
        
         trimester15.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, trimester15);
        });
        trimester15.setOnDragOver((DragEvent event) -> {
            dragOver(event, trimester15);
        });
        trimester15.setOnDragDropped((DragEvent event) -> {
            dragDropped(event, trimester15);
        });
        trimester15.setOnDragDone((DragEvent event) -> {
            dragDone(event, trimester15);
        });
   }
   
   @FXML
   public void recommendedPressed() throws ClassNotFoundException, SQLException {
       
   }
   
   @FXML
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
        temporaryList.clear();
        clearUnitProgress();
        savePlan.setDisable(true);
        establishList();
   }
   
   public void clearUnitProgress() {
       totalUnits = 0;
       totalUnitLabel.setText(Integer.toString(totalUnits));
       unitProgressBar.setProgress(0);
       
       businessCoresUnits = 0;
       ISCoresUnits = 0;
       prescribedElectivesUnits = 0; 
       freeElectivesUnits = 0;
       generalEducationUnits = 0;
       
       businessCoresGauge.setValue(0);
       ISCoresGauge.setValue(0);
       prescribedElectivesGauge.setValue(0);
       freeElectivesGauge.setValue(0);
       generalEducationGauge.setValue(0);
   }
   
   public int sumUnits(ListView<Subject> listView) {
       int total = 0;
       for (int i = 0; i < listView.getItems().size(); i++) {
          Subject subject = listView.getItems().get(i);
          int units = subject.getSubjectUnits();
          total += units;
       }
    return total+dragUnits;
   }
   
   public void totalUnitTracker() {
       
       totalUnits += tempUnits;
       totalUnitLabel.setText(Integer.toString(totalUnits));
       double x = (double) totalUnits;
       double progress = x/144;
       unitProgressBar.setProgress(progress);
       tempUnits = 0;
       if (totalUnits == 144) {
           savePlan.setDisable(false);
       }
   }
   
   public boolean trimesterOfferingCheck(ListView<Subject> listView) {
       
       if (Arrays.asList(trimester1, trimester4, trimester7, trimester10, trimester13).contains(listView) && trimesterOffering.contains("Term 1")){
           return true;
       }
       else if (Arrays.asList(trimester2, trimester5, trimester8, trimester11, trimester14).contains(listView) && trimesterOffering.contains("Term 2")) {
           return true;
       }
       else if (Arrays.asList(trimester3, trimester6, trimester9, trimester12, trimester15).contains(listView) && trimesterOffering.contains("Term 3")) {
           return true;
       }
       else {
           alert(subjectOffered+" not offered in chosen trimester. Term Offering: "+trimesterOffering);
           listView.getSelectionModel().clearSelection();
           subjectList.getSelectionModel().clearSelection();
           return false;
       }
   }
   
   public boolean prerequisiteCheck() {
       if ("null".equals(tempPrerequisites)) {
           return true;
       }
       else if ("INFS2603".equals(subjectOffered) && temporaryList.contains("INFS1602") && temporaryList.contains("INFS1603")) {
           return true; 
       }
       else if ("INFS2101".equals(subjectOffered) && temporaryList.contains("INFS2603")) {
           return true;
       }
       else if ("INFS2605".equals(subjectOffered) && temporaryList.contains("INFS1603") && temporaryList.contains("INFS1609")) {
           return true;
       }
       else if ("INFS2608".equals(subjectOffered) && temporaryList.contains("INFS1603")) {
           return true;
       }
       else if ("INFS2609".equals(subjectOffered) && temporaryList.contains("INFS1602") && temporaryList.contains("INFS1603")) {
           return true;
       }
       else if ("INFS2621".equals(subjectOffered) && temporaryList.contains("INFS1602")) {
           return true;
       }
       else if ("INFS2631".equals(subjectOffered) && temporaryList.contains("INFS1602")) {
           return true;
       }
       else if ("INFS3020".equals(subjectOffered) && totalUnits >= 90) {
           return true;
       }
       else if ("INFS3202".equals(subjectOffered) && temporaryList.contains("INFS2101")) {
           return true;
       }
       else if ("INFS3303".equals(subjectOffered) && temporaryList.contains("INFS3202")) {
           return true;
       }
       else if ("INFS3603".equals(subjectOffered) && temporaryList.contains("INFS1602")) {
           return true;
       }
       else if ("INFS3604".equals(subjectOffered) && temporaryList.contains("INFS2603")) {
           return true;
       }
       else if ("INFS3605".equals(subjectOffered) && temporaryList.contains("INFS3634")) {
           return true;
       }
       else if ("INFS3617".equals(subjectOffered) && temporaryList.contains("INFS1602")) {
           return true;
       }
       else if ("INFS3632".equals(subjectOffered) && temporaryList.contains("INFS1602")) {
           return true;
       }
       else if ("INFS3634".equals(subjectOffered) && temporaryList.contains("INFS2605") && temporaryList.contains("INFS2608")) {
           return true;
       }
       else if ("INFS3830".equals(subjectOffered) && temporaryList.contains("INFS3603")) {
           return true;
       }
       else if ("INFS3873".equals(subjectOffered) && temporaryList.contains("INFS3603")) {
           return true;
       }
       else if (!subjectOffered.contains("INFS")) {
           return true;
       }
       else {
           alert(tempSubject+" prerequisites not fulfilled. "+tempPrerequisites);
           return false;
       }
    
   }
   
   public void progressionWheelArrays() {
        
       
       //Information Systems
        businessCores.add("ACCT1501");
        businessCores.add("MGMT1001");
        businessCores.add("ECON1203"); 
        businessCores.add("MATH1041");
        businessCores.add("ACCT1511");
        businessCores.add("ECON1101"); 
      
        informationSystemsCores.add("INFS1602");
        informationSystemsCores.add("INFS1603");
        informationSystemsCores.add("INFS1609");
        informationSystemsCores.add("INFS2603");
        informationSystemsCores.add("INFS2605");
        informationSystemsCores.add("INFS2608");
        informationSystemsCores.add("INFS2621");
        informationSystemsCores.add("INFS3603");
        informationSystemsCores.add("INFS3604");
        informationSystemsCores.add("INFS3605");
        informationSystemsCores.add("INFS3617");
        informationSystemsCores.add("INFS3634");
        
        prescribedElectives.add("INFS2631");
        prescribedElectives.add("INFS3020");
        prescribedElectives.add("INFS3632");
        prescribedElectives.add("INFS3830");
        prescribedElectives.add("INFS3873");
        
        //Commerce / Information Systems
        flexibleCores.add("ACCT1511");
        flexibleCores.add("COMM1000");
        flexibleCores.add("ECON1102");
        flexibleCores.add("FINS1613");
        flexibleCores.add("INFS1602");
        flexibleCores.add("MARK1012");
        flexibleCores.add("MGMT1101");
        flexibleCores.add("TABL1710");
        
        businessCores2.add("ACCT1501");
        businessCores2.add("ECON1101");
        businessCores2.add("MGMT1001");
        businessCores2.add("ECON1203");
        businessCores2.add("MATH1041");
        
   }
   
   public void progressionWheelIncrement() {
       if (businessCores.contains(tempSubject)) {
           businessCoresUnits += dragUnits;
           businessCoresGauge.setValue(businessCoresUnits);
       }
       else if (informationSystemsCores.contains(tempSubject)) {
           ISCoresUnits += dragUnits;
           ISCoresGauge.setValue(ISCoresUnits);
       }
       else if (prescribedElectives.contains(tempSubject)) {
           if (prescribedElectivesUnits == 12) {
                freeElectivesUnits += dragUnits;
                freeElectivesGauge.setValue(freeElectivesUnits);
           }
           else {
                prescribedElectivesUnits += dragUnits;
                prescribedElectivesGauge.setValue(prescribedElectivesUnits);
           }
           
       }
       else if (!"UNSW Business School".equals(tempFaculty) ) {
           if (generalEducationUnits == 12) {
                freeElectivesUnits += dragUnits;
                freeElectivesGauge.setValue(freeElectivesUnits); 
           }
           else {
                generalEducationUnits += dragUnits;
                generalEducationGauge.setValue(generalEducationUnits);
           }
           
       }
       else {   
           if (temporaryList.contains(subjectOffered)) {
               //Do nothing
           }
           else {
            freeElectivesUnits += dragUnits;
            freeElectivesGauge.setValue(freeElectivesUnits);   
           }
            
       }
   }
  
   
   public void establishToolTip() {
       
       subjectList.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
       trimester1.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
       trimester2.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
       trimester3.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
       trimester4.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
       trimester5.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
       trimester6.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
       trimester7.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
       trimester8.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
       trimester9.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
   }
   
   public void establishToolTip2() {
       
       trimester10.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
       trimester11.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
       trimester12.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
   }
   
   public void establishToolTip3() {
       
       trimester13.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
       trimester14.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
       
       trimester15.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() {
            public ListCell<Subject> call(ListView<Subject> param) {
                Tooltip tooltip = new Tooltip();
                ListCell<Subject> cell = new ListCell<Subject>() {
                    @Override
                    public void updateItem(Subject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSubjectCode());
                            tooltip.setText(item.getSubjectName()+
                                            "\nUnits of credit: "+item.getSubjectUnits()+
                                            "\nPrerequisites: "+item.getPrerequisites()+
                                            "\nTrimester Offering: "+item.getOffering());
                            setTooltip(tooltip);
                        } else {
                            setText("");
                            setTooltip(null);
                        }
                    }
                };
                return cell;
            }
        });
   }
   
   @FXML
   public void loadWebLink() {
       Subject subject = (Subject) subjectList.getSelectionModel().getSelectedItem();
       String courseLink = subject.getURL();
       try {
            Desktop.getDesktop().browse(new URL(courseLink).toURI());
        } catch (IOException e) {
             e.printStackTrace();
        } catch (URISyntaxException e) {
             e.printStackTrace();
        }
       
       
   }
   
   public ArrayList<Subject> loadCourseDB() throws ClassNotFoundException, SQLException {
       Statement stm;
       Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/PrometheusDB", "student", "1234");
       stm = conn.createStatement(); 
       
       ResultSet rst;
       rst = stm.executeQuery(sql);
       ArrayList<Subject> courseList = new ArrayList<>();
       try {
            while (rst.next()) {
                Subject subject = new Subject(rst.getString("COURSE_CODE"),
                                              rst.getInt("UOC"),
                                              rst.getString("COURSE_NAME"),
                                              rst.getString("CONDITIONS_FOR_ENROLMENT"),
                                              rst.getString("OFFERING_TERM"),
                                              rst.getString("FACULTY"),
                                              rst.getString("COURSE_LINK"));
                courseList.add(subject);
            }
       } catch (Exception e) {
           System.out.println("loadCourseDB()");
           e.printStackTrace(); 
       }
       return courseList;
       
    }
   
   @FXML
   public void searchPressed() throws ClassNotFoundException, SQLException {
        String userInput = courseSearch.getText();
        this.sql = "Select DISTINCT * From COURSE WHERE (UPPER(COURSE_CODE) LIKE '%"+userInput.toUpperCase()+"%') "
                 + "OR (UPPER(COURSE_NAME) LIKE '%"+userInput.toUpperCase()+"%') ORDER BY COURSE_CODE";
        subjectList.getItems().clear();
        establishList();
        this.sql = "Select * From COURSE ORDER BY COURSE_CODE";        
   }
   
   @FXML
   public void businessCoresPressed() throws ClassNotFoundException, SQLException {
       this.sql = "SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'ACCT1501' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'MGMT1001' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'ECON1203' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'MATH1041' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'ACCT1511' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'ECON1101'";
        subjectList.getItems().clear();
        establishList();
        this.sql = "Select * From COURSE ORDER BY COURSE_CODE";        
   }
   
   @FXML
   public void ISCoresPressed() throws ClassNotFoundException, SQLException {
        this.sql = "SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS1602' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS1603' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS1609' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS2603' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS2605' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS2608' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS2621' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS3603' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS3604' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS3605' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS3617' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS3634' ORDER BY COURSE_CODE";
        subjectList.getItems().clear();
        establishList();
        this.sql = "Select * From COURSE ORDER BY COURSE_CODE";         
   }
   
   @FXML
   public void prescribedElectivesPressed() throws ClassNotFoundException, SQLException {
       this.sql = "SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS2631' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS3020' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS3632' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS3830' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'INFS3873'";
        subjectList.getItems().clear();
        establishList();
        this.sql = "Select * From COURSE ORDER BY COURSE_CODE";
   }
   
   @FXML
   public void freeElectivesPressed() {
       
   }
   
   @FXML
   public void generalEducationPressed() throws ClassNotFoundException, SQLException {
       this.sql = "SELECT DISTINCT * from COURSE WHERE NOT FACULTY = 'UNSW Business School' ORDER BY COURSE_CODE";
       subjectList.getItems().clear();
        establishList();
        this.sql = "Select * From COURSE ORDER BY COURSE_CODE";
   }
   
   @FXML
   public void businessCores2Pressed() throws ClassNotFoundException, SQLException {
       this.sql = "SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'ACCT1501' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'MGMT1001' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'ECON1203' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'MATH1041' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'ECON1101'";
        subjectList.getItems().clear();
        establishList();
        this.sql = "Select * From COURSE ORDER BY COURSE_CODE";
   }
   
   @FXML
   public void flexibleCoresPressed() throws ClassNotFoundException, SQLException {
       this.sql = "SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'ACCT1511' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'COMM1000' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'ECON1102' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'FINS1613' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'MARK1012' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'MGMT1101' UNION "
                 +"SELECT DISTINCT * from COURSE WHERE COURSE_CODE = 'TABL1710'";
        subjectList.getItems().clear();
        establishList();
        this.sql = "Select * From COURSE ORDER BY COURSE_CODE";
   }
   
   @FXML
   public void businessElectivesPressed() {
       
   }
   
//   @FXML
//   public void saveImage() throws IOException {
//        try {
//            Robot robot = new Robot();
//            Rectangle rectangle = new Rectangle(0, 0, 800, 410);
//            BufferedImage image = robot.createScreenCapture(rectangle);
//            FileChooser fileChooser = new FileChooser();
//            ImageIO.write(image, "png", fileChooser.showSaveDialog(stage));
//        } catch (AWTException ex) {
//            Logger.getLogger(ParentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//   }
   
   @FXML
   public void saveImage() throws IOException {
        WritableImage image = anchorPane.snapshot(new SnapshotParameters(), null);
        FileChooser fileChooser = new FileChooser();
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fileChooser.showSaveDialog(stage));
   }
}

