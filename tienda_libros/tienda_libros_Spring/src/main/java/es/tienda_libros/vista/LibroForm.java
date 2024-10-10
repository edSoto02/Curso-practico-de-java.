package es.tienda_libros.vista;

import es.tienda_libros.modelo.Libro;
import es.tienda_libros.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class LibroForm extends JFrame {

    LibroServicio libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private JLabel Libro;
    private JTextField LibroTexto;
    private JTextField autorTexto;
    private JTextField precioTexto;
    private JLabel autorLibro;
    private JLabel precioLibro;
    private JTextField existenciasTexto;
    private JButton Agregar;
    private JButton Eliminar;
    private JButton Ediatr;
    private JButton Salir;
    private JLabel ExistenciasLibro;
    private JLabel libroTexto;
    private DefaultTableModel tableModelLibros;

    private JTextField idTexto;

    @Autowired
    public LibroForm(LibroServicio libroServicio){

        this.libroServicio = libroServicio;
        iniciarforma();
        Agregar.addActionListener(e -> agregarLibro());

        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarLibroSeleccionado();
            }
        });

        Ediatr.addActionListener( e -> modificarLibro());


        Eliminar.addActionListener( e -> EliminarLibro());

    }//fin libro form

    private void iniciarforma(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth() / 2);
        int y = (tamanioPantalla.height = getHeight() / 2);
        setLocation(x, y);
    }

    private void agregarLibro(){
        if (LibroTexto.getText().equals("")){
            mostrarmensaje("Proporciona el nombre del libro");
            LibroTexto.requestFocusInWindow();
            return;
        }

        var nombreLibro = LibroTexto.getText();
        var autor = autorTexto.getText();
        var precio = Double.parseDouble(precioTexto.getText());
        var existencias = Integer.parseInt(existenciasTexto.getText());

        var libro = new Libro();
        //forma1
        //var libro1 = new Libro(null, nombreLibro, autor,precio,existencias);
        //forma 2
        libro.setNombreLibro(nombreLibro);
        libro.setAutor(autor);
        libro.setPrecio(precio);
        libro.setExistencias(existencias);
        this.libroServicio.guardarLibro(libro);
        mostrarmensaje("Se agrego el Libro");
        limpiarFormulario();
        listarLibros();
    }

    private void limpiarFormulario(){
        LibroTexto.setText("");
        autorTexto.setText("");
        precioTexto.setText("");
        existenciasTexto.setText("");
    }

    private void mostrarmensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        idTexto = new JTextField("");
        idTexto.setVisible(false);

        this.tableModelLibros = new DefaultTableModel(0, 5){
          @Override
          public boolean isCellEditable(int row, int column){
              return false;
          }
        };
        String[] cabcera = {"Id","Libro","Autor","Precio","Existencias"};
        this.tableModelLibros.setColumnIdentifiers(cabcera);
        this.tablaLibros = new JTable(tableModelLibros);
        tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listarLibros();
    }

    private void listarLibros(){
        tableModelLibros.setRowCount(0);
        var libros = libroServicio.listarLibros();
        libros.forEach( (libro)-> {
            Object[] renglonLibro = {
                    libro.getIdLibro(),
                    libro.getNombreLibro(),
                    libro.getAutor(),
                    libro.getPrecio(),
                    libro.getExistencias()
            };
            this.tableModelLibros.addRow(renglonLibro);
          });
        }


    private void cargarLibroSeleccionado(){
        var renglon = tablaLibros.getSelectedRow();
        if(renglon != -1){
            String idLibro = tablaLibros.getModel().getValueAt(renglon, 0).toString();
            idTexto.setText(idLibro);
            String nombreLibro = tablaLibros.getModel().getValueAt(renglon, 1).toString();
            LibroTexto.setText(nombreLibro);
            String autor = tablaLibros.getModel().getValueAt(renglon, 2).toString();
            autorTexto.setText(autor);
            String precio = tablaLibros.getModel().getValueAt(renglon, 3).toString();
            precioTexto.setText(precio);
            String existencias = tablaLibros.getModel().getValueAt(renglon, 4).toString();
            existenciasTexto.setText(existencias);

        }

    }

    private void modificarLibro(){
        if (idTexto.getText().equals("")){
            mostrarmensaje("Selecciona un Registro");
        }else{
            if (libroTexto.getText().equals("")){
                mostrarmensaje("Proporciona el nombre del Libro ");
                libroTexto.requestFocusInWindow();
                return;
            }
            int idLibro = Integer.parseInt(idTexto.getText());
            var nombvreLibro = LibroTexto.getText();
            var autor = autorTexto.getText();
            var precio = Double.parseDouble(precioTexto.getText());
            var existenias = Integer.parseInt(existenciasTexto.getText());
            var libro = new Libro(idLibro, nombvreLibro,autor,precio,existenias);
            libroServicio.guardarLibro(libro);
            mostrarmensaje("Se modifico el Libro");
            limpiarFormulario();
            listarLibros();
        }
    }

    private void EliminarLibro() {
        var renglon = tablaLibros.getSelectedRow();
        if (renglon != -1) {
            String idLibro = tablaLibros.getModel().getValueAt(renglon, 0).toString();
            var libro = new Libro();
            libroServicio.eliminarLibro(libro);
            mostrarmensaje("Se elimino ");
            limpiarFormulario();
            listarLibros();
        } else {
            mostrarmensaje("No se selecciona nada ");
        }
    }






    }//fin clase


