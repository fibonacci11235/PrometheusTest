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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author josiah
 */
public class DashboardController implements Initializable {

    private UserProfile currentUser;
    
    @FXML
    private Label courseTitle;
    @FXML
    private Label startYear;
    @FXML
    private Button backButton;
    @FXML
    private ListView subjectList;
    @FXML
    private ListView trimester1;
    @FXML
    private ListView trimester2;
    @FXML
    private ListView trimester3;
    @FXML
    private ListView trimester4;
    @FXML
    private ListView trimester5;
    @FXML
    private ListView trimester6;
    @FXML
    private ListView trimester7;
    @FXML
    private ListView trimester8;
    @FXML
    private ListView trimester9;
    @FXML
    private Label secondYear;
    @FXML
    private Label thirdYear;
    @FXML
    private TextField alertField;
    
    static final DataFormat SUBJECT_LIST = new DataFormat("SubjectList");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //subjectList.getItems().addAll("INFS1602", "INFS1603", "INFS1609", "ACCT1501");
        
        
        subjectList.getItems().addAll(this.getSubjectList());
        
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
    }
    
    private ObservableList<Subject> getSubjectList() {
        ObservableList<Subject> list = FXCollections.observableArrayList();
        
        Subject INFS1602 = new Subject("INFS1602");
        Subject INFS1603 = new Subject("INFS1603");
        Subject INFS1609 = new Subject("INFS1609");
        Subject ACCT1501 = new Subject("ACCT1501");
        Subject MGMT1001 = new Subject("MGMT1001");
        Subject ECON1101 = new Subject("ECON1101");
        Subject ECON1203 = new Subject("ECON1203");
        
        list.addAll(INFS1602, INFS1603, INFS1609, ACCT1501, MGMT1001, ECON1101, ECON1203);
        
        return list;
    }
      
    
    
    private void dragDetected(MouseEvent event, ListView<Subject> listView) {
        int selectedCount = listView.getSelectionModel().getSelectedIndices().size();
        
        if (selectedCount == 0) {
            event.consume();
            return;
        }
        
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
        if (listView != subjectList && listView.getItems().size() >= 3) {
            alert("This trimester already contains a full load.");
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
    
    private void establishHandlers(){
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
   
   
}
