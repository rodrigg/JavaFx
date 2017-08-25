package ch.makery.address;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vbox = new VBox();

        TextField firstNameFilterTextField = new TextField();
        TextField lastNameFilterTextField = new TextField();

        TableView<Persona> tableView = new TableView<>();

        ObservableList<Persona> list = FXCollections.observableArrayList(new Persona("Peter", "Schmidt"),
                new Persona("Hans-Peter", "Schmidt"), new Persona("Hans", "Mustermann"));
        FilteredList<Persona> filterList = new FilteredList<>(list);
        tableView.setItems(filterList);

        TableColumn<Persona, String> firstNameCol = new TableColumn<>("FirstName");
        TableColumn<Persona, String> lastNameCol = new TableColumn<>("LastName");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        filterList.predicateProperty().bind(Bindings.createObjectBinding(() ->
        person -> person.getFirstName().contains(firstNameFilterTextField.getText())
               && person.getLastName().contains(lastNameFilterTextField.getText()),

        firstNameFilterTextField.textProperty(),
        lastNameFilterTextField.textProperty()

    ));

        tableView.getColumns().addAll(firstNameCol, lastNameCol);

        vbox.getChildren().addAll(firstNameFilterTextField, lastNameFilterTextField, tableView);

        Scene scene = new Scene(vbox, 250, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class Persona {

        private String firstName;
        private String lastName;

        public Persona(String firstName, String lastName) {
            super();
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}