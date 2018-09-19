/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prometheustest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 *
 * @author josiah
 */
public abstract class ParentDashboardController implements Initializable {
    public UserProfile currentUser;
    
    @FXML
    public Label courseTitle;
    @FXML
    public Label startYear;
    @FXML
    public Button backButton;
    @FXML
    public Button recommend;
    @FXML
    public Button clear;
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
    
    private int dragUnits;
    
    //Subjects
    private Subject INFS1602 = new Subject("INFS1602", 6, "Digital Transformation in Business", "", "T1, T2, T3");
    private Subject INFS1603 = new Subject("INFS1603", 6, "Introduction to Business Databases", "", "T1, T2");
    private Subject INFS1609 = new Subject("INFS1609", 6, "Fundamentals of Business Programming", "", "T1, T3");
    private Subject ACCT1501 = new Subject("ACCT1501", 6, "Accounting and Financial Management 1A", "", "T1, T2, T3");
    private Subject MGMT1001 = new Subject("MGMT1001", 6, "Managing Organisations and People", "", "T1, T2, T3");
    private Subject ECON1101 = new Subject("ECON1101", 6, "Microeconomics 1", "", "T1, T2, T3");
    private Subject MATH1041 = new Subject("MATH1041", 6, "Statistics for Life and Social Sciences", "", "T1, T2, T3");
    private Subject ECON1203 = new Subject("ECON1203", 6, "Business and Economic Statistics", "", "T1, T2, T3");
    private Subject ACCT1511 = new Subject("ACCT1511", 6, "Accounting and Financial Management 1B", "ACCT1501", "T1, T2");
    
    private Subject INFS2603 = new Subject("INFS2603", 6, "Business Analysis", "INFS1602, INFS1603", "T3");
    private Subject INFS2605 = new Subject("INFS2605", 6, "Intermediate Business Programming", "INFS1603, INFS1609", "T1, T2");
    private Subject INFS2608 = new Subject("INFS2608", 6, "Database Management & Big Data Infrastructures", "INFS1603", "T1");
    private Subject INFS2621 = new Subject("INFS2621", 6, "Enterprise Systems", "INFS1602", "T2");
    private Subject INFS2631 = new Subject("INFS2631", 6, "Innovation and Technology Management", "INFS1602", "T1");
    
    private Subject INFS3603 = new Subject("INFS3603", 6, "Introduction to Business Analytics", "INFS1602, 72 UOC", "T1, T2");
    private Subject INFS3617 = new Subject("INFS3617", 6, "Networking & Cyber Security", "INFS1602, 72 UOC", "T1");
    private Subject INFS3020 = new Subject("INFS3020", 6, "International IS/IT Practicum", "Minimum WAM of 60, 96 UOC (36UOC INFS)", "T2");
    private Subject INFS3632 = new Subject("INFS3632", 6, "Service and Quality Management", "INFS1602, 72 UOC", "T2");
    private Subject INFS3604 = new Subject("INFS3604", 6, "Business Process Management", "INFS2603, 72 UOC", "T3");
    private Subject INFS3873 = new Subject("INFS3873", 6, "Business Analytics Methods", "INFS3603, 72 UOC", "T3");
    private Subject INFS3634 = new Subject("INFS3634", 6, "Mobile Applications Development", "INFS2605, INFS2608, 72 UOC", "T1, T3");
    private Subject INFS3830 = new Subject("INFS3830", 6, "Social Media and Analytics", "INFS3603, 72 UOC", "T1");
    private Subject INFS3605 = new Subject("INFS3605", 6, "IS Innovation & Transformation", "INFS3634 or INFS3611, 72 UOC", "T1, T2");
    //BIS (Co-op) Exclusive
    private Subject INFS2101 = new Subject("INFS2101", 12, "Industry Placement 1", "INFS2603", "T1");
    private Subject INFS3202 = new Subject("INFS3202", 12, "Industry Pacement 2", "INFS2101", "T1");
    private Subject INFS3303 = new Subject("INFS3303", 12, "Industry Placement 3", "INFS3202", "T2, T3");
    private Subject INFS4800 = new Subject("INFS4800", 6, "Thesis A", "Enrolled in Honours", "T1");
    private Subject INFS4801 = new Subject("INFS4801", 6, "Thesis B", "INFS4800", "T1");
    private Subject INFS4802 = new Subject("INFS4802", 6, "Thesis C", "TBA", "TBA");
    private Subject INFS4886 = new Subject("INFS4886", 6, "Principles of Research Design", "Enrolled in Honours", "T3");
    private Subject INFS4887 = new Subject("INFS4887", 6, "Business Research Methods", "Enrolled in Honours, INFS4886", "T1");
    private Subject INFS4831 = new Subject("INFS4831", 6, "Information Systems Consulting", "Enrolled in Honours", "T1");
    private Subject INFS4858 = new Subject("INFS4858", 6, "Managing Complex Projects", "Enrolled in Honours", "T1");
    private Subject INFS4907 = new Subject("INFS4907", 6, "Managing Security and Ethics in Cyberspace", "Enroleld in Honours", "T2");
    //Comm/InfoSys Exclusive
    private Subject COMM1000 = new Subject("COMM1000", 6, "Creating Social Change", "", "T1, T2, T3");
    private Subject ECON1102 = new Subject("ECON1102", 6, "Macroeconomics 1", "ECON1101", "T1, T2");
    private Subject FINS1613 = new Subject("FINS1613", 6, "Business Finance", "", "T1, T2, T3");
    private Subject MARK1012 = new Subject("MARK1012", 6, "Marketing Fundamentals", "", "T1, T2, T3");
    private Subject MGMT1101 = new Subject("MGMT1101", 6, "Global Business Environment", "", "T1, T2, T3");
    private Subject TABL1710 = new Subject("TABL1710", 6, "Business and the Law", "Not enrolled in Law programs", "T1, T2, T3");
            
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
    
    
    public void initData(UserProfile profile){
        currentUser = profile;
        courseTitle.setText(currentUser.getCourse());
        startYear.setText(Integer.toString(currentUser.getStartingYear()));
        secondYear.setText(Integer.toString(currentUser.getStartingYear()+1));
        thirdYear.setText(Integer.toString(currentUser.getStartingYear()+2));
        establishList();
    }
        
     
    public void establishList() {
        subjectList.getItems().addAll(this.getSubjectList());
    }
    
    private ObservableList<Subject> getSubjectList() {
        ObservableList<Subject> list = FXCollections.observableArrayList();
        
        /*Subject INFS1602 = new Subject("INFS1602");
        Subject INFS1603 = new Subject("INFS1603");
        Subject INFS1609 = new Subject("INFS1609");
        Subject ACCT1501 = new Subject("ACCT1501");
        Subject MGMT1001 = new Subject("MGMT1001");
        Subject ECON1101 = new Subject("ECON1101");
        Subject ECON1203 = new Subject("ECON1203");*/
        if ("3979 - Information Systems".equals(currentUser.getCourse())) {
            list.addAll(INFS1602, INFS1603, INFS1609, ACCT1501, MGMT1001, ECON1101, 
                        ECON1203, INFS2603, INFS2605, INFS2608, ACCT1511, INFS2621,
                        INFS3603, INFS3617, INFS2631, INFS3020, INFS3632, INFS3604,
                        INFS3873, INFS3634, INFS3830, INFS3605);
        }
        else if ("3584 - Commerce / Information Systems".equals(currentUser.getCourse())) {
            list.addAll(INFS1602, INFS1603, INFS1609, ACCT1501, MGMT1001, ECON1101, 
                        ECON1203, INFS2603, INFS2605, INFS2608, ACCT1511, INFS2621,
                        INFS3603, INFS3617, INFS2631, INFS3020, INFS3632, INFS3604,
                        INFS3873, INFS3634, INFS3830, INFS3605, COMM1000, ECON1102,
                        FINS1613, MARK1012, MGMT1101, TABL1710); 
         }
        else if ("3964 - BIS (Co-op)".equals(currentUser.getCourse())) {
             list.addAll(INFS1602, INFS1603, INFS1609, ACCT1501, MGMT1001, ECON1101, 
                        ECON1203, INFS2603, INFS2605, INFS2608, ACCT1511, INFS2621,
                        INFS3603, INFS3617, INFS2631, INFS3020, INFS3632, INFS3604,
                        INFS3873, INFS3634, INFS3830, INFS3605, INFS2101, INFS3202,
                        INFS3303, INFS4800, INFS4801, INFS4802, INFS4886, INFS4887,
                        INFS4831, INFS4858, INFS4907);
         }
        
        return list;
    }
      
    
    
    private void dragDetected(MouseEvent event, ListView<Subject> listView) {
        int selectedCount = listView.getSelectionModel().getSelectedIndices().size();
        
        if (selectedCount == 0) {
            event.consume();
            return;
        }
        Subject subject = listView.getSelectionModel().getSelectedItem();
        dragUnits = subject.getSubjectUnits();
        
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
            alert("Adding subject will exceed trimester load.");
            dragCompleted = false;
        }
        else {
            if(dragboard.hasContent(SUBJECT_LIST)) {
                ArrayList<Subject> list = (ArrayList<Subject>)dragboard.getContent(SUBJECT_LIST);
                listView.getItems().addAll(list);
                alert(" ");
                dragCompleted = true;            
            }   
        }
        
        
        event.setDropCompleted(dragCompleted);
        event.consume();  
    }
    
    private void dragDone(DragEvent event, ListView<Subject> listView) {
        TransferMode tm = event.getTransferMode();
        if (tm == TransferMode.MOVE) {
            removeSelectedSubjects(listView);
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
   public void recommendedPressed() {
        if ("3979 - Information Systems".equals(currentUser.getCourse())) {
            clearTrimesters();
            subjectList.getItems().removeAll(INFS1602, INFS1603, INFS1609,
                                             ACCT1501, MGMT1001, ECON1101,
                                             INFS2603, INFS2605, ECON1203,
                                             INFS2608, INFS3617, INFS2621,
                                             INFS3603, INFS3632, INFS3604,
                                             INFS3873, INFS3634, INFS2631,
                                             INFS3830, INFS3605);
            trimester1.getItems().addAll(INFS1602, INFS1603, INFS1609);
            trimester2.getItems().addAll(ACCT1501, MGMT1001, ECON1101);
            trimester3.getItems().addAll(INFS2603, INFS2605, ECON1203);
            trimester4.getItems().addAll(INFS2608, INFS3617);
            trimester5.getItems().addAll(INFS2621, INFS3603, INFS3632);
            trimester6.getItems().addAll(INFS3604, INFS3873);
            trimester7.getItems().addAll(INFS3634, INFS2631, INFS3830);
            trimester8.getItems().addAll(INFS3605);
            trimester9.getItems().addAll();
       }
        else if ("3584 - Commerce / Information Systems".equals(currentUser.getCourse())) {
            
        }
        else if ("3964 - BIS (Co-op)".equals(currentUser.getCourse())) {
            
        }
   }
   
   @FXML
   public void clearTrimesters() {
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
       establishList();
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
                                            "\nPrequisites: "+item.getPrerequisites()+
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
}

