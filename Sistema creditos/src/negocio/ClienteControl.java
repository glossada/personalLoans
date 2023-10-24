/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.ClienteDAO;
import entidades.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel Lossada
 */
public class ClienteControl {

    private final ClienteDAO DATOS;
    private Cliente obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public ClienteControl() {
        this.DATOS = new ClienteDAO();
        this.obj = new Cliente();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto, int totalPorPagina, int numPagina) {
        List<Cliente> lista = new ArrayList();
        lista.addAll(DATOS.listarPag(texto, totalPorPagina, numPagina));

        String[] titulos = {"Id", "Cliente", "edad", "CUIT-DNI", "Direccion", "Email", "telefono 1", "estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[8];

        this.registrosMostrados = 0;
        for (Cliente item : lista) {
            registro[0] = Integer.toString(item.getIdCliente());
            registro[1] = item.getNombre();
            registro[2] = Integer.toString(item.getEdad());
            registro[3] = item.getDniCuit();
            registro[4] = item.getDireccion();
            registro[5] = item.getEmail();
            registro[6] = item.getTel1();
            registro[7] = item.getEstado();
            
            
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }


    
    public String insertar(String nombre, int edad, String cuitDni, String direccion, String email, String tel1) {
        if (DATOS.existe(nombre)) {
            return "El registro ya existe.";
        } else {
            obj.setNombre(nombre);
            obj.setEdad(edad);
            obj.setDniCuit(cuitDni);
            obj.setDireccion(direccion);
            obj.setEmail(email);
            obj.setTel1(tel1);
            obj.setEstado("Inactivo");

            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro.";
            }
        }
    }

    public String actualizar(int idCliente, String nombre, String nombreAnt, int edad, String cuitDni, String direccion, String email,String tel1) {
        if (nombre.equals(nombreAnt)) {
            obj.setNombre(nombre);
            obj.setEdad(edad);
            obj.setIdCliente(idCliente);
            obj.setDniCuit(cuitDni);
            obj.setDireccion(direccion);
            obj.setEmail(email);
            obj.setTel1(tel1);
            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "Error en la actualizacion.";
            }
        } else {
            if (DATOS.existe(nombre)) {
                return "El registro ya existe.";
            } else {
                obj.setNombre(nombre);
                obj.setEdad(edad);
                obj.setIdCliente(idCliente);
                obj.setDniCuit(cuitDni);
                obj.setDireccion(direccion);
                obj.setEmail(email);
                obj.setTel1(tel1);
                if (DATOS.actualizar(obj)) {
                    return "OK";
                } else {
                    return "Error en la actualizacion.";
                }
            }
        }
    }


    public int totalReg() {
        return DATOS.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

    public String eliminar(int id) {
        if (DATOS.eliminar(id)) {
            return "OK";
        } else {
            return "No se puede eliminar el registro nadie sabe porque, estamos todos confundidos y agobiados";
        }
    }
}
