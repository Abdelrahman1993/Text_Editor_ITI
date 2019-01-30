package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		
		
		
		try {
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			////////////////////////////////////
			
			MenuItem new1 = new MenuItem("New");
			new1.setAccelerator(KeyCombination.keyCombination("ctrl+n"));
			MenuItem open = new MenuItem("Open");
			open.setAccelerator(KeyCombination.keyCombination("ctrl+o"));
			MenuItem save = new MenuItem("Save");
			save.setAccelerator(KeyCombination.keyCombination("ctrl+s"));
			MenuItem exit = new MenuItem("Exit");
			exit.setAccelerator(KeyCombination.keyCombination("alt+f4"));
			
			Menu file = new Menu("File");
			file.getItems().addAll(new1,open,save,exit);
			
			/////////////////////////////////////
			
			////////////////////////////////////
			
			MenuItem undo = new MenuItem("Undo");
			undo.setAccelerator(KeyCombination.keyCombination("ctrl+z"));
			MenuItem cut = new MenuItem("Cut");
			cut.setAccelerator(KeyCombination.keyCombination("ctrl+x"));
			MenuItem copy = new MenuItem("Copy");
			copy.setAccelerator(KeyCombination.keyCombination("ctrl+c"));
			MenuItem paste = new MenuItem("Paste");
			paste.setAccelerator(KeyCombination.keyCombination("ctrl+v"));
			MenuItem delete = new MenuItem("Delete");
			delete.setAccelerator(KeyCombination.keyCombination("back"));
			MenuItem selectAll = new MenuItem("Select All");
			selectAll.setAccelerator(KeyCombination.keyCombination("ctrl+a"));
		
			Menu edit = new Menu("Edit");
			edit.getItems().addAll(undo,cut,copy,paste,delete,selectAll);
				
			/////////////////////////////////////
			
			/////////////////////////////////////
			MenuItem about = new MenuItem("About");
			Menu help = new Menu("Help");
			help.getItems().addAll(about);
			//////////////////////////////////////
				
			MenuBar bar = new MenuBar();
			bar.getMenus().addAll(file,edit,help);
			
			/////////////////////////////////////////////////
			
			TextArea txtArea=new TextArea(); 
			/////////////////////////////////////////////////
			root.setTop(bar);
			root.setCenter(txtArea);
			/////////////////////////////////////////////////
			
			/////////////////////////////////////////////////
			
			new1.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					txtArea.clear();
				}
			});
			open.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {

					FileChooser fileChooser = new FileChooser();  
					fileChooser.setTitle("Open File");  
					File file = fileChooser.showOpenDialog(primaryStage); 
			        txtArea.clear();
			        
			        
			        FileReader freader=null;
					try {
						freader = new FileReader(file.toString());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					BufferedReader br = new BufferedReader(freader);
					String s;
					try {
						while((s = br.readLine()) != null) {
							txtArea.appendText(s+"\n");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						freader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			save.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {

		            FileChooser fileChooser = new FileChooser();  
		            fileChooser.setTitle("Open File");  
		            File file = fileChooser.showSaveDialog(primaryStage);
					if(file != null)
					{
						try {
							FileWriter fileWriter = new FileWriter(file.toString());
							fileWriter.write(txtArea.getText());
							fileWriter.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				}
			});
			exit.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					primaryStage.close();
					
				}
			});
			
			///////////////////////////////////////////////////
			
			undo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					txtArea.undo();
					
				}
			});
			cut.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					txtArea.cut();
					
				}
			});
			copy.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					txtArea.copy();
					
				}
			});
			paste.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					txtArea.paste();
				}
			});
			delete.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					txtArea.deleteText(txtArea.getSelection().getStart(), txtArea.getSelection().getEnd());					
				}
			});
			selectAll.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					txtArea.selectAll();
					
				}
			});
			
			/////////////////////////////////////////////////////
			about.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					
					 Alert alert = new Alert(Alert.AlertType.INFORMATION);
			        alert.setTitle("About us");
			        alert.setHeaderText("Abdelrahman Ahmed");
			        alert.setContentText("Email : AbdelrahmanAbdelfatah@yahoo.com");
			        alert.showAndWait();
					
					/*Stage stage2 = new Stage();
					stage2.initModality(Modality.APPLICATION_MODAL);
					stage2.initOwner(primaryStage);
					VBox vpane = new VBox();
					Text tt = new Text("ggggggggggggggggggggggggggggggggggggggggg");
					vpane.getChildren().add(tt);
					Scene dialogScene = new Scene(vpane,300,300);
					stage2.setScene(dialogScene);
					stage2.show();*/
				}
			});
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
