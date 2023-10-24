package datos;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import datos.interfaces.CrudSimpleInterface;
import entidades.Usuario;
import database.Conexion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO implements CrudSimpleInterface <Usuario> {
    
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public UsuarioDAO() {
        CON=Conexion.getInstancia();
    }

    @Override
    public List<Usuario> listar(String texto) {
         List<Usuario> registros= new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM cliente WHERE nombre LIKE ?");
            ps.setString(1,"%" + texto + "%");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Usuario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
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
    
        public List<Usuario> listarP(String texto, int totalPorPag, int numeroPag) {
         List<Usuario> registros= new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM usuario WHERE nombreApe LIKE ? ORDER BY idusuario ASC LIMIT ?,?");
            ps.setString(1,"%" + texto + "%");
            ps.setInt(2, (numeroPag-1)*totalPorPag);
            ps.setInt(3, totalPorPag);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Usuario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
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
        
        public Usuario login(String usuario, String clave){
            Usuario usu = null;
            try {
                 ps=CON.conectar().prepareStatement("SELECT idusuario, usuario, nombreApe FROM usuario WHERE usuario=? AND password=?");
            ps.setString(1, usuario);
            ps.setString(2, clave);
            rs=ps.executeQuery();
            
            if(rs.next()){
                usu=new Usuario(rs.getInt(1),rs.getString(2),rs.getString(3));
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
            return usu;
        }

    @Override
    public boolean insertar(Usuario obj) {
          resp=false;
        try {
            ps=CON.conectar().prepareStatement("INSERT INTO usuario (usuario, nombreApe, password) VALUES (?, ?, ?)");
            ps.setString(1,obj.getUsuario());
            ps.setString(2,obj.getNombreApe());
            ps.setString(3,obj.getPassword());
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
    public boolean actualizar(Usuario obj) {
           resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE usuario SET usuario=?, nombreApe=?, password=? WHERE idusuario=?");
            ps.setString(1,obj.getUsuario());
            ps.setString(2,obj.getNombreApe());
            ps.setString(3,obj.getPassword());
            ps.setInt(4, obj.getIdUsuario());
            
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean activar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int total() {
        int totalReg=0;
        try {
            ps=CON.conectar().prepareStatement("SELECT count(*) as total FROM usuario");
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
            ps=CON.conectar().prepareStatement("SELECT nombreApe FROM usuario WHERE nombreApe=?");
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
            ps=CON.conectar().prepareStatement("DELETE FROM usuario WHERE idusuario=?");
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
