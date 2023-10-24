package datos;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import datos.interfaces.CrudSimpleInterface;
import entidades.Cliente;
import database.Conexion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO implements CrudSimpleInterface <Cliente> {
    
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public ClienteDAO() {
        CON=Conexion.getInstancia();
    }

    @Override
    public List<Cliente> listar(String texto) {
         List<Cliente> registros= new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM cliente WHERE nombreApe LIKE ?");
            ps.setString(1,"%" + texto + "%");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Cliente(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }
    
        public List<Cliente> listarPag(String texto, int totalPorPag, int numeroPag) {
         List<Cliente> registros= new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM cliente WHERE nombreApe LIKE ? ORDER BY idcliente ASC LIMIT ?,?");
            ps.setString(1,"%" + texto + "%");
            ps.setInt(2, (numeroPag-1)*totalPorPag);
            ps.setInt(3, totalPorPag);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Cliente(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }
        
       public List<Cliente> listarActivos(String texto, int totalPorPag, int numeroPag) {
         List<Cliente> registros= new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM cliente WHERE nombreApe LIKE ? AND estado=? ORDER BY idcliente ASC LIMIT ?,?");
            ps.setString(1,"%" + texto + "%");
            ps.setString(2, "Activo");
            ps.setInt(3, (numeroPag-1)*totalPorPag);
            ps.setInt(4, totalPorPag);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Cliente(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    } 

    @Override
    public boolean insertar(Cliente obj) {
          resp=false;
        try {
            ps=CON.conectar().prepareStatement("INSERT INTO cliente (nombreApe, edad, dniCuit, direccion, email, tel1, tel2, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1,obj.getNombre());
            ps.setInt(2,obj.getEdad());
            ps.setString(3,obj.getDniCuit());
            ps.setString(4,obj.getDireccion());
            ps.setString(5,obj.getEmail());
            ps.setString(6,obj.getTel1());
            ps.setString(7,obj.getTel2());
            ps.setString(8,obj.getEstado());
            if (ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            CON.desconectar();
        }
        
        return resp;
    }

    @Override
    public boolean actualizar(Cliente obj) {
           resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE cliente SET nombreApe=?, edad=?, dniCuit=?, direccion=?, email=?, tel1=?, estado=? WHERE idcliente=?");
            ps.setString(1, obj.getNombre());
            ps.setInt(2,obj.getEdad());
            ps.setString(3, obj.getDniCuit());
            ps.setString(4, obj.getDireccion());
            ps.setString(5,obj.getEmail());
            ps.setString(6,obj.getTel1());
            ps.setString(7,obj.getEstado());
            ps.setInt(8, obj.getIdCliente());
            if (ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            CON.desconectar();
        }
        
        return resp;
    }

    @Override
    public boolean desactivar(int id) {
           resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE cliente SET estado=? WHERE idCliente=?");
            ps.setInt(1, id);
            if (ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            CON.desconectar();
        }
        
        return resp;
    }

    @Override
    public boolean activar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int total() {
        int totalReg=0;
        try {
            ps=CON.conectar().prepareStatement("SELECT count(*) as total FROM cliente");
            rs=ps.executeQuery();
            if (rs.next()){
                totalReg=Integer.parseInt(rs.getString("total"));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return totalReg;
    }

    @Override
    public boolean existe(String texto) {
          resp=false;
        try {
            ps=CON.conectar().prepareStatement("SELECT nombreApe FROM cliente WHERE nombreApe=?");
            ps.setString(1, texto);
            rs=ps.executeQuery();
            if(rs.next()){
                resp=true;
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean eliminar(int id) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("DELETE FROM cliente WHERE idCliente=?");
            ps.setInt(1, id);
            if (ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            CON.desconectar();
        }
        
        return resp;
    }
    
}
