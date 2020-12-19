package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Slider;


public class MainController implements Initializable{
	@FXML private MediaView mv;
	private MediaPlayer mp;
	private Media me;
	
	@FXML Slider volumeslider;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String path=new File("src/media/Star Size Comparison 2.mp4").getAbsolutePath();
		me= new Media(new File(path).toURI().toString());
		mp=new MediaPlayer(me);
		mv.setMediaPlayer(mp);
		DoubleProperty width=mv.fitWidthProperty();
		DoubleProperty height=mv.fitHeightProperty();	
		width.bind(Bindings.selectDouble(mv.sceneProperty(),"width"));
		height.bind(Bindings.selectDouble(mv.sceneProperty(),"height"));
		volumeslider.setValue(mp.getVolume()*100);
		volumeslider.valueProperty().addListener(new InvalidationListener(){
			@Override
			public void invalidated(Observable observable) {
				mp.setVolume(volumeslider.getValue()/100);
			}
		});
	}
	
	//for loading file 
		public void loadfile(ActionEvent event) {
		    FileChooser fc = new FileChooser();
		    fc.setInitialDirectory(new File ("src/media"));
		    fc.getExtensionFilters().addAll(new ExtensionFilter("Video, Audio Files", "*.mp4","*mp3","*flv"));
		    File selectedFile = fc.showOpenDialog(null);
		    if(selectedFile != null) {
		       me = new Media(selectedFile.toURI().toString());
		       mp = new MediaPlayer(me);
		       mv.setMediaPlayer(mp);
		       mp.play();
		    }

		    else {
		        System.out.println("file is not valid");
		    }

		}
	
	//buttons
	public void play(ActionEvent event) {
		mp.play();
		mp.setRate(1);
	}
	public void pause(ActionEvent event) {
		mp.pause();
	}
	public void fast(ActionEvent event) {
		mp.setRate(2);
	}
	public void slow(ActionEvent event) {
		mp.setRate(.5);
	}
	public void reload(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.play();
	}
	public void start(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.stop();
	}
	
	
	
	public void last(ActionEvent event) {
		mp.seek(mp.getTotalDuration());
	}
	
	
	
	
	
}
