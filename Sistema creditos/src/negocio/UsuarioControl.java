/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.UsuarioDAO;
import entidades.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel Lossada
 */
public class UsuarioControl {

    private final UsuarioDAO DATOS;
    private Usuario obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public UsuarioControl() {
        this.DATOS = new UsuarioDAO();
        this.obj = new Usuario();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto, int totalPorPagina, int numPagina) {
        List<Usuario> lista = new ArrayList();
        lista.addAll(DATOS.listarP(texto, totalPorPagina, numPagina));

        String[] titulos = {"Id", "Usuario", "Nombre y apellido", "clave"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[4];

        this.registrosMostrados = 0;
        for (Usuario item : lista) {
            registro[0] = Integer.toString(item.getIdUsuario());
            registro[1] = item.getUsuario();
            registro[2] = item.getNombreApe();
            registro[3] = item.getPassword();

            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }
    
    public String login(String usuario, String clave){
        String resp="0";
        Usuario usu=this.DATOS.login(usuario, this.encriptar(clave));
        if(usu!=null){
            Variables.usuarioId=usu.getIdUsuario();
            Variables.usuario=usu.getUsuario();
            Variables.usuarioNom=usu.getNombreApe();
            resp="1";
        }
        return resp;
    }

    private static String encriptar(String valor) {
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
        byte[] hash = md.digest(valor.getBytes());
        StringBuilder sb = new StringBuilder();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    public String insertar(String usuario, String nombreApe, String password) {
        if (DATOS.existe(nombreApe)) {
            return "El registro ya existe.";
        } else {
            obj.setUsuario(usuario);
            obj.setNombreApe(nombreApe);
            obj.setPassword(this.encriptar(password));

            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro.";
            }
        }
    }

    public String actualizar(int idUsuario, String usuario, String nombreApe, String usuarioAnt, String password) {
        if (usuario.equals(usuarioAnt)) {
            obj.setIdUsuario(idUsuario);
            obj.setUsuario(usuario);
            obj.setNombreApe(nombreApe);

            String encriptado;
            if (password.length() == 64) {
                encriptado = password;
            } else {
                encriptado = this.encriptar(password);
            }

            obj.setPassword(encriptado);
            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "Error en la actualizacion.";
            }
        } else {
            if (DATOS.existe(usuario)) {
                return "El registro ya existe.";
            } else {
                obj.setIdUsuario(idUsuario);
                obj.setUsuario(usuario);
                obj.setNombreApe(nombreApe);

                String encriptado;
                if (password.length() == 64) {
                    encriptado = password;
                } else {
                    encriptado = this.encriptar(password);
                }

                obj.setPassword(encriptado);
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
