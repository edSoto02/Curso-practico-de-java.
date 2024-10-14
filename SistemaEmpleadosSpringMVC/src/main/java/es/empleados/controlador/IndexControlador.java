package es.empleados.controlador;

import es.empleados.modelo.Empleado;
import es.empleados.servicio.EmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexControlador {

    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);

    @Autowired
    EmpleadoServicio empleadosServicio;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String iniciar(ModelMap modelo){
        List<Empleado> empleados = empleadosServicio.listarEmpleados();
        empleados.forEach((empleado) -> logger.info(empleado.toString()));
        modelo.put("empleados", empleados);
        return "index";
    }

    @RequestMapping(value="/agregar", method = RequestMethod.GET)
    public String mostrarAgregar(){
        return "agregar";
    }

    @RequestMapping(value="/agregar", method = RequestMethod.POST)
    public String agregar(@ModelAttribute("empleadoForma") Empleado empleado){
        logger.info("Empleado a agregar: " + empleado);
        empleadosServicio.guardarEmpleado(empleado);
        return "redirect:/";
    }


    @RequestMapping(value="/editar", method = RequestMethod.GET)
    public String mostrarEditar(@RequestParam int idEmpleado, ModelMap modelo){
        Empleado empleado = empleadosServicio.buscarEmpleadoPorId(idEmpleado);
        logger.info("Empleado a Editar: " + empleado);
        modelo.put("empledado", empleado);
        return "editar";
    }

    @RequestMapping(value="/editar", method = RequestMethod.POST)
    public String editar(@ModelAttribute("empleadoForma") Empleado empleado){
        logger.info("Empleado a guardar (ediatr)" + empleado);
        empleadosServicio.guardarEmpleado(empleado);
        return "redirect:/";
    }

    @RequestMapping(value="/eliminar", method = RequestMethod.GET)
    public String eliminar(@RequestParam int idEmpleado){
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(idEmpleado);
        empleadosServicio.eliminarEmpleado(empleado);
        return "redirect:/";
    }



}
