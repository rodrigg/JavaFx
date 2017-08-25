package de.saxsys.mvvm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginView {
	LoginViewModel loginViewModel;
	@FXML
	private TextField userNameTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private Button loginButton;

	@FXML
	void initialize() {
		 loginViewModel = new LoginViewModel();
		userNameTextField.textProperty().bindBidirectional(loginViewModel.userNameProperty());
		passwordTextField.textProperty().bindBidirectional(loginViewModel.passwordProperty());
		loginButton.disableProperty().bind(loginViewModel.isLoginPossibleProperty().not());
		passwordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
		    System.out.println("textfield changed from " + oldValue + " to " + newValue);
		});
	}
	
	

	
	
}