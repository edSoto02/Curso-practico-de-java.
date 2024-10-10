package es.tareas.controlador;

import es.tareas.TareasApplication;
import es.tareas.modelo.Tarea;
import es.tareas.servicio.TareaServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.scene.control.*;

@Component
public class IndexControlador implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);

    @Autowired
    private TareaServicio tareaServicio;

    @FXML
    private TableView<Tarea> tareaTabla;
    @FXML
    private TableColumn<Tarea, Integer> idTareaColumna;
    @FXML
    private TableColumn<Tarea, String> tareaColumna;
    @FXML
    private TableColumn<Tarea, String> responsableColumna;
    @FXML
    private TableColumn<Tarea, String> estatusColumna;
    @FXML
    private TextField nombreTareatxt;
    @FXML
    private TextField responsabletxt;
    @FXML
    private TextField estatustxt;

    private Integer idTareaInterna;

    private final ObservableList<Tarea> tareaLista = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareaTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnas();
        listarTareas();

    }

    private void configurarColumnas(){
        idTareaColumna.setCellValueFactory(new PropertyValueFactory<>("idTarea"));
        tareaColumna.setCellValueFactory(new PropertyValueFactory<>("nombreTarea"));
        responsableColumna.setCellValueFactory(new PropertyValueFactory<>("responsable"));
        estatusColumna.setCellValueFactory(new PropertyValueFactory<>("estatus"));
    }

    private void listarTareas(){
        tareaLista.clear();
        tareaLista.addAll(tareaServicio.listarTareas());
        tareaTabla.setItems(tareaLista);
    }

    public void agregarTarea(){
        if(nombreTareatxt.getText().isEmpty()){
            mostrarMensaje("Error", "Debe proporcionar una tarea");
            nombreTareatxt.requestFocus();
            return;
        }
        else{
            var tarea = new Tarea();
            recolectarDAtosFormulario(tarea);
            tarea.setIdTarea(null);
            tareaServicio.guardarTarea(tarea);
            mostrarMensaje("Informacion","Tarea guardada");
            limpiarFormulario();
            listarTareas();
        }
    }

    private void recolectarDAtosFormulario(Tarea tarea){
        if(idTareaInterna != null)
            tarea.setIdTarea(idTareaInterna);

        tarea.setNombreTarea(nombreTareatxt.getText());
        tarea.setResponsable(responsabletxt.getText());
        tarea.setEstatus(estatustxt.getText());
    }

    private void mostrarMensaje(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void limpiarFormulario(){
        idTareaInterna = null;
        nombreTareatxt.clear();
        responsabletxt.clear();
        estatustxt.clear();
    }

    public void caragarTareaFormulario(){
        var tarea = tareaTabla.getSelectionModel().getSelectedItem();
        if (tarea != null){
            idTareaInterna = tarea.getIdTarea();
            nombreTareatxt.setText(tarea.getNombreTarea());
            responsabletxt.setText(tarea.getResponsable());
            estatustxt.setText(tarea.getEstatus());
        }
    }

    public void modificarTarea(){
        if (idTareaInterna == null){
            mostrarMensaje("Informacion","Debe seleccionar una tarea");
            return;
        }
        if (nombreTareatxt.getText().isEmpty()){
            mostrarMensaje("Error validacion","Debe proporcionar una tarea");
            nombreTareatxt.requestFocus();
            return;
        }
        var tarea = new Tarea();
        recolectarDAtosFormulario(tarea);
        tareaServicio.guardarTarea(tarea);
        mostrarMensaje("Informacion","Tarea modificada");
        limpiarFormulario();
        listarTareas();

    }

    public  void  eliminarTarea(){
        var tarea = tareaTabla.getSelectionModel().getSelectedItem();
        if (tarea != null){
            tareaServicio.eliminarTarea(tarea);
            mostrarMensaje("Informacion", "Tarea eliminada");
            limpiarFormulario();
            listarTareas();
        }else{
            mostrarMensaje("Error","No se selecciono ninguna tarea");
        }

    }

}
