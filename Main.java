package sample;

import java.io.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.*;

public class Main extends Application {
    Label titleLb,dateLb,markLb;
    private DatePicker datePicker;
    private TextField marktf;
    Button btn;

    @Override
    public void start(Stage primaryStage) {
        datePicker=new DatePicker();
        marktf=new TextField();
        marktf.setPrefWidth(220);
        datePicker.setPrefWidth(220);
        titleLb=new Label("My CP Tracker");
        titleLb.setFont(new Font("Arial",22));
        dateLb=new Label("Date:");
        dateLb.setFont(new Font(16));
        markLb=new Label("Marks:");
        markLb.setFont(new Font(16));

        HBox dBox=new HBox(70);
        dBox.getChildren().addAll(dateLb,datePicker);
        dBox.setAlignment(Pos.CENTER);
        dBox.setPadding(new Insets(20,20,10,0));
        HBox mBox=new HBox(70);
        mBox.getChildren().addAll(markLb,marktf);
        mBox.setAlignment(Pos.CENTER);
        mBox.setPadding(new Insets(20,20,10,0));
        btn = new Button("Save Data");
        btn.setFont(new Font(16));
        btn.setOnAction((ActionEvent event) -> {
            save();
        });

        HBox bBox=new HBox();
        bBox.getChildren().add(btn);
        bBox.setAlignment(Pos.CENTER_RIGHT);
        bBox.setPadding(new Insets(20,20,10,0));
        FlowPane f=new FlowPane();
        f.getChildren().addAll(titleLb,dBox,mBox,bBox);
        f.setAlignment(Pos.CENTER);

        Scene scene = new Scene(f, 350, 300);
        primaryStage.setTitle("RollNumber CP Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void save(){
        try(PrintWriter writer=new PrintWriter(new FileWriter("cp.txt",true))){
            String data="----- CP Marks on "+datePicker.getValue().toString()+" ----------\n";
            data+=" Marks: "+marktf.getText();
            writer.println(data);
            alert(data);
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    private void alert(String data){
        Alert a=new Alert(AlertType.INFORMATION);
        a.setTitle("CP Data Saved");
        a.setHeaderText("Your CP data is saved successfully");
        a.setContentText(data);
        a.showAndWait();
    }

    public static void main(String[] args) {

        launch(args);
    }

}



