package controllers;

import com.gluonhq.charm.glisten.control.TextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import axis.Axis;
import axis.HorizontalAxis;
import axis.VerticalAxis;

public class MainController implements Initializable {

    public Text verticalAxisPositionDisplay;
    public Text horizontalAxisPositionDisplay;
    public Button upArrow_Btn;
    public Button rightArrow_Btn;
    public Button leftArrow_Btn;
    public Button downArrow_Btn;
    public Button upRightArrow_Btn;
    public Button downRightArrow_Btn;
    public Button upLeftArrow_Btn;
    public Button downLeftArrow_Btn;
    public TextField VerticalAxisPosition_Go;
    public TextField HorizontalAxisPosition_Go;
    String [] rates = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    public ComboBox verticalAxis_rates = null;
    public ComboBox horizontalAxis_rates = null;
    public int verticalAxis_currentPosition;
    public int horizontalAxis_currentPosition;

    public Axis verticalAxis = null;
    public Axis horizontalAxis = null;
    static int vAxisRate;
    static int hAxisRate;

    Timer timer;
    TimerTask timerTask;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        verticalAxis_rates.getItems().removeAll(verticalAxis_rates.getItems());
        verticalAxis_rates.getItems().addAll(FXCollections.observableArrayList(rates));
        verticalAxis_rates.getSelectionModel().selectFirst();

        horizontalAxis_rates.getItems().removeAll(horizontalAxis_rates.getItems());
        horizontalAxis_rates.getItems().addAll(FXCollections.observableArrayList(rates));
        horizontalAxis_rates.getSelectionModel().selectFirst();

        verticalAxis_currentPosition = Integer.parseInt(getVerticalAxis_Pos());
        verticalAxis = new VerticalAxis(verticalAxis_currentPosition);
        horizontalAxis_currentPosition = Integer.parseInt(getHorizontalAxis_Pos());
        horizontalAxis = new HorizontalAxis(horizontalAxis_currentPosition);

    }

    public void onMouse_Pressed_Handler(MouseEvent mouseEvent) {

        if(mouseEvent.getSource() == upArrow_Btn){
            upArrow_Btn_handle(verticalAxis, upArrow_Btn);
        }
        else if(mouseEvent.getSource() == rightArrow_Btn){
            rightArrow_Btn_handle(horizontalAxis, rightArrow_Btn);
        }
        else if(mouseEvent.getSource() == downArrow_Btn){
            downArrow_Btn_handle(verticalAxis, downArrow_Btn);
        }
        else if(mouseEvent.getSource() == leftArrow_Btn){
            leftArrow_Btn_handle(horizontalAxis, leftArrow_Btn);
        }
        else if(mouseEvent.getSource() == upRightArrow_Btn){
            upRightArrow_Btn_handle(verticalAxis, horizontalAxis, upRightArrow_Btn);
        }
        else if(mouseEvent.getSource() == upLeftArrow_Btn){
            upLeftArrow_Btn_handle(verticalAxis, horizontalAxis, upRightArrow_Btn);
        }
        else if(mouseEvent.getSource() == downRightArrow_Btn){
            downRightArrow_Btn_handle(verticalAxis, horizontalAxis, downRightArrow_Btn);
        }
        else if(mouseEvent.getSource() == downLeftArrow_Btn){
            downLeftArrow_Btn_handle(verticalAxis, horizontalAxis, downLeftArrow_Btn);
        }
        else{}

    }

    public void onMouse_Released_Handler(MouseEvent mouseEvent) {
        timerTask.cancel();
        timer.cancel();
        timer.purge();
    }

    public void upArrow_Btn_handle(Axis vAxis, Button button) {
        vAxisRate = Integer.parseInt((String) verticalAxis_rates.getValue());
        MoveAxisPosition(vAxis, vAxisRate, button);

    }

    public void rightArrow_Btn_handle(Axis hAxis, Button button) {
        hAxisRate = Integer.parseInt((String) horizontalAxis_rates.getValue());
        MoveAxisPosition(hAxis, hAxisRate, button);
    }

    public void leftArrow_Btn_handle(Axis hAxis, Button button) {
        hAxisRate = Integer.parseInt((String) horizontalAxis_rates.getValue());
        MoveAxisPosition(hAxis, hAxisRate, button);
    }

    public void downArrow_Btn_handle(Axis vAxis, Button button) {
        vAxisRate = Integer.parseInt((String) verticalAxis_rates.getValue());
        MoveAxisPosition(vAxis, vAxisRate, button);
    }

    public void upRightArrow_Btn_handle(Axis vAxis, Axis hAxis, Button button) {
        vAxisRate = Integer.parseInt((String) verticalAxis_rates.getValue());
        hAxisRate = Integer.parseInt((String) horizontalAxis_rates.getValue());
        MoveAxisPositions(vAxis, hAxis, vAxisRate, hAxisRate, button);
    }

    public void upLeftArrow_Btn_handle(Axis vAxis, Axis hAxis, Button button) {
        vAxisRate = Integer.parseInt((String) verticalAxis_rates.getValue());
        hAxisRate = Integer.parseInt((String) horizontalAxis_rates.getValue());
        MoveAxisPositions(vAxis, hAxis, vAxisRate, hAxisRate, button);
    }

    private void downLeftArrow_Btn_handle(Axis vAxis, Axis hAxis, Button button) {
        vAxisRate = Integer.parseInt((String) verticalAxis_rates.getValue());
        hAxisRate = Integer.parseInt((String) horizontalAxis_rates.getValue());
        MoveAxisPositions(vAxis, hAxis, vAxisRate, hAxisRate, button);
    }

    private void downRightArrow_Btn_handle(Axis vAxis, Axis hAxis, Button button) {
        vAxisRate = Integer.parseInt((String) verticalAxis_rates.getValue());
        hAxisRate = Integer.parseInt((String) horizontalAxis_rates.getValue());
        MoveAxisPositions(vAxis, hAxis, vAxisRate, hAxisRate, button);
    }

    public void MoveAxisPosition(Axis axis, int axisRate, Button button){
        timer = new Timer();
        timerTask= new TimerTask() {
            @Override
            public void run() {
                if(button == upArrow_Btn || button == rightArrow_Btn){

                    axis.AddToAxis(axisRate);
                    SetAxisPosition(axis.GetAxisPos(), button);
                }
                else if(button == downArrow_Btn || button == leftArrow_Btn){
                    axis.SubtractFromAxis(axisRate);
                    SetAxisPosition(axis.GetAxisPos(), button);
                }
                else{}
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    public void SetAxisPosition(int AxisPos, Button button){
        if(button == upArrow_Btn || button == downArrow_Btn){
            this.verticalAxisPositionDisplay.setText(String.valueOf(AxisPos));
        }
        else if(button == rightArrow_Btn || button == leftArrow_Btn){
            this.horizontalAxisPositionDisplay.setText(String.valueOf(AxisPos));
        }
        else{}
    }

    public void MoveAxisPositions(Axis vAxis, Axis hAxis, int vAxisRate, int hAxisRate,Button button){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {

                if(button == upRightArrow_Btn){

                    vAxis.AddToAxis(vAxisRate);
                    hAxis.AddToAxis(hAxisRate);
                    SetAxisPositions(vAxis.GetAxisPos(), hAxis.GetAxisPos());
                }
                else if(button == upLeftArrow_Btn){

                    vAxis.AddToAxis(vAxisRate);
                    hAxis.SubtractFromAxis(hAxisRate);
                    SetAxisPositions(vAxis.GetAxisPos(), hAxis.GetAxisPos());
                }
                else if(button == downLeftArrow_Btn){

                    vAxis.SubtractFromAxis(vAxisRate);
                    hAxis.SubtractFromAxis(hAxisRate);
                    SetAxisPositions(vAxis.GetAxisPos(), hAxis.GetAxisPos());
                }
                else if(button == downRightArrow_Btn){

                    vAxis.SubtractFromAxis(vAxisRate);
                    hAxis.AddToAxis(hAxisRate);
                    SetAxisPositions(vAxis.GetAxisPos(), hAxis.GetAxisPos());
                }
                else {}
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    public void SetAxisPositions(int vAxisPos, int hAxisPos){
        this.verticalAxisPositionDisplay.setText(String.valueOf(vAxisPos));
        this.horizontalAxisPositionDisplay.setText(String.valueOf(hAxisPos));
    }
    
    public String getVerticalAxis_Pos() {
        return verticalAxisPositionDisplay.getText();
    }

    public String getHorizontalAxis_Pos() {
        return horizontalAxisPositionDisplay.getText();
    }

    public void GoBtnHandler(ActionEvent actionEvent) {
        System.out.println("Clicked");
        vAxisRate = Integer.parseInt((String) verticalAxis_rates.getValue());
        hAxisRate = Integer.parseInt((String) horizontalAxis_rates.getValue());

        int VerticalGoToPosition = Integer.parseInt((String) VerticalAxisPosition_Go.getText());
        int HorizontalGoToPosition = Integer.parseInt((String) HorizontalAxisPosition_Go.getText());

        verticalAxis.GoToAxisPosition(vAxisRate, VerticalGoToPosition);
        horizontalAxis.GoToAxisPosition(hAxisRate,HorizontalGoToPosition);
    }
}
