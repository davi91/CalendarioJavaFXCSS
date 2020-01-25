package dad.javafx.calendario;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dad.javafx.componentes.MonthCalendarCSS;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

public class CalendarController implements Initializable {

	// FXML : View
	//--------------------------------------------
	
    @FXML
    private GridPane view;

    @FXML
    private Button backBt;

    @FXML
    private Label yearLbl;

    @FXML
    private Button nextBt;
    
    @FXML
    private ArrayList<MonthCalendarCSS> monthList = new ArrayList<>();
    
    //--------------------------------------------
    
    // Model
    //--------------------------------------------
    
    private IntegerProperty year = new SimpleIntegerProperty();
    
    //--------------------------------------------
    
	public CalendarController() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalendarView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	/**
	 * Ajustamos las fechas
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		year.set(LocalDate.now().getYear());
		
		yearLbl.textProperty().bind(year.asString());
		
		int j = 1;
		for( MonthCalendarCSS cal : monthList ) {
			cal.yearPropertyProperty().bind(year);
			cal.setMonthProperty(j);
			j++;
		}
	}
	
    @FXML
    void goBackAction(ActionEvent event) {
    	year.set(year.get()-1);  	
    }

    @FXML
    void goNextAction(ActionEvent event) {
    	year.set(year.get()+1); 
    }
    
    public GridPane getRootView() {
		return view;
	}

}
