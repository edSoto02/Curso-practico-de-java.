package es.SistemaCuentasSpringCU.controlador;

import es.SistemaCuentasSpringCU.modelo.Cuenta;
import es.SistemaCuentasSpringCU.servicio.CuentaServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Data
@ViewScoped
public class indexControlador {
    @Autowired
    CuentaServicio cuentaServicio;
    private List<Cuenta> cuentas;
    private Cuenta cuentaSeleccionada;

    private static final Logger logger = LoggerFactory.getLogger(indexControlador.class);
    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.cuentas = cuentaServicio.listarCuentas();
        cuentas.forEach((cuenta) -> logger.info(cuenta.toString()));
    }

    public void agregarCuenta(){
        logger.info("Se crea un objeto cuentaSeleccionada para el caso de agregar");
        this.cuentaSeleccionada = new Cuenta();
    }


    public void guardarCuenta(){
        logger.info("Cuenta a guardar: " + this.cuentaSeleccionada);
        //parte paara agregar -> insert a la base de datos
        if (this.cuentaSeleccionada.getIdCuenta() == null){
            this.cuentaServicio.guardarCuenta(this.cuentaSeleccionada);
            this.cuentas.add(this.cuentaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta Agregada: "));

        }
        else{// parte para modificar alguna cuenta -> update a la base de datos
            this.cuentaServicio.guardarCuenta(this.cuentaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta Actualizada: "));
        }
        // oculatamos la ventana
        PrimeFaces.current().executeScript("PF('ventanaModalCuenta'.hide()");
        // Actualizamos la tabla
        PrimeFaces.current().ajax().update("forma-cuentas:mensajes", "forma-cuentas:cuentas-tabla");
        // reset
        this.cuentaSeleccionada = null;
    }

    public void eliminarCuenta(){
        logger.info("Cuenta a Eliminar: " + this.cuentaSeleccionada);
        this.cuentaServicio.eliminarCuenta(this.cuentaSeleccionada);
        // vamos a eliminar el registro de la lista de las cuentas
        this.cuentas.remove(this.cuentaSeleccionada);
        // reset del objeto seleccionado de la tabla
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta Eliminada"));
        PrimeFaces.current().ajax().update("forma-cuentas:mensajes", "forma-cuentas:cuentas-tabla");
    }
}
