package Controller;

import dao.UserDao;
import entity.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.Stack;


public class RegisterFormController {
    @FXML
    private AnchorPane rootNode;
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobileNo;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        UserDao userDao = new UserDao();
       /* String username = txtUserName.getText();

        for(int i=1; i<=Integer.parseInt(txtUserId.getText());i++){
            if(username == userDao.uservalidate(txtUserId.getText())){
                System.out.println("UserService name already taken");
                break;
            }
        }*/

        try{
            UserEntity userEntity = new UserEntity(txtUserId.getText(),
                    txtName.getText(),
                    txtUserName.getText(),
                   txtPassword.getText(),
                    txtEmail.getText(),
                    txtMobileNo.getText());

            userDao.userAccountCreate(userEntity);
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        ClearMethod();

        Parent root = FXMLLoader.load(getClass().getResource("/view/main_form.fxml"));
        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException {
        UserDao userDao = new UserDao();

        try {
            UserEntity userEntity = new UserEntity(txtUserId.getText(),
                    txtName.getText(),
                    txtUserName.getText(),
                    txtPassword.getText(),
                    txtEmail.getText(),
                    txtMobileNo.getText());

            userDao.userDelete(userEntity);
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Successfull").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        ClearMethod();

        Parent root = FXMLLoader.load(getClass().getResource("/view/main_form.fxml"));

        Scene scene = new Scene(root);

        Stage primeryStage = (Stage) this.rootNode.getScene().getWindow();
        primeryStage.setTitle("Main");
        primeryStage.setScene(scene);

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        UserDao userDao = new UserDao();
        String id = txtUserId.getText();
        UserEntity userEntity = userDao.searchUser(id);
        if (userEntity!=null){
            txtUserId.setText(String.valueOf(userEntity.getId()));
            txtName.setText((userEntity.getName()));
            txtUserName.setText(userEntity.getUserName());
            txtPassword.setText(userEntity.getPassword());
            txtEmail.setText(userEntity.getEmail());
            txtMobileNo.setText(userEntity.getMobileNumber());
        }else{
            new Alert(Alert.AlertType.INFORMATION,"UserService Not Found").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        UserDao userDao = new UserDao();

        try{
            UserEntity userEntity = new UserEntity(txtUserId.getText(),
                    txtName.getText(),
                    txtUserName.getText(),
                    txtPassword.getText(),
                    txtEmail.getText(),
                    txtMobileNo.getText());
            userDao.updateUser(userEntity);
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updated").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        ClearMethod();
    }

    public void ClearMethod(){
        txtUserId.setText("");
        txtName.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
        txtEmail.setText("");
        txtMobileNo.setText("");
    }
}
