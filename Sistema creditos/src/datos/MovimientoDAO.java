package datos;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import datos.interfaces.CrudSimpleInterface;
import entidades.Movimientos;
import database.Conexion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MovimientoDAO implements CrudSimpleInterface <Movimientos> {
    
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public MovimientoDAO() {
        CON=Conexion.getInstancia();
    }

    @Override
    public List<Movimientos> listar(String texto) {
         List<Movimientos> registros= new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM movimientos WHERE fecha LIKE ?");
            ps.setString(1,"%" + texto + "%");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Movimientos(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDouble(5), rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
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
    
        public List<Movimientos> listarPeriodo(String mes,String año) {
         List<Movimientos> registros= new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM movimientos WHERE mes=? AND año=? AND (tipo=? OR tipo=?)");
            ps.setString(1,mes);
            ps.setString(2,año);
            ps.setString(3,"E");
            ps.setString(4,"S");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Movimientos(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDouble(5), rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
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
        
        public List<Movimientos> listarDiario(String dia,String mes,String año) {
         List<Movimientos> registros= new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM movimientos WHERE dia=? AND mes=? AND año=?");
            ps.setString(1,dia);
            ps.setString(2,mes);
            ps.setString(3,año);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Movimientos(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDouble(5), rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
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
    public boolean insertar(Movimientos obj) {
          resp=false;
        try {
            ps=CON.conectar().prepareStatement("INSERT INTO movimientos (idCliente,cliente,descripcion,monto,ganancia,dia,mes,año,tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, obj.getIdCliente());
            ps.setString(2, obj.getCliente());
            ps.setString(3, obj.getDescripcion());
            ps.setDouble(4,obj.getMonto());
            ps.setDouble(5,obj.getSoloGanancia());
            ps.setString(6,obj.getDia());
            ps.setString(7,obj.getMes());
            ps.setString(8,obj.getAño());
            ps.setString(9,obj.getTipo());
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
    public boolean actualizar(Movimientos obj) {
           resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE movimientos SET idCliente=?, cliente=?, descripcion=?, monto=?, ganancia=?, Dia=?, Mes=?, Año=?, tipo=? WHERE idmovimientos=?");
            ps.setInt(1, obj.getIdCliente());
            ps.setString(2, obj.getCliente());
            ps.setString(3, obj.getDescripcion());
            ps.setDouble(4, obj.getMonto());
            ps.setDouble(5, obj.getSoloGanancia());
            ps.setString(6, obj.getDia());
            ps.setString(7, obj.getMes());
            ps.setString(8, obj.getAño());
            ps.setString(9, obj.getTipo());
            ps.setInt(10, obj.getIdMovimiento());
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
    
    public boolean setTipo(int idCliente, double monto,String dia, String mes, String año,String tipo) {
           resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE movimientos SET tipo=? WHERE idCliente=? AND monto=? AND dia=? AND mes=? AND año=?");
            ps.setString(1, tipo);
            ps.setInt(2, idCliente);
            ps.setDouble(3, monto);
            ps.setString(4, dia);
            ps.setString(5, mes);
            ps.setString(6, año);
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
    
    public double getMonto(String tipo) {
         double monto= 0;
        try {
            ps=CON.conectar().prepareStatement("SELECT sum(monto) FROM movimientos WHERE tipo=?");
            ps.setString(1,tipo);
            rs=ps.executeQuery();
            while(rs.next()){
                monto=rs.getDouble(1);
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
        return monto;
    }
    
    public double getMontoPeriodo(String tipo, String mes, String año) {
         double monto= 0;
        try {
            ps=CON.conectar().prepareStatement("SELECT sum(monto) FROM movimientos WHERE mes=? AND año=? AND tipo=?");
            ps.setString(1,mes);
            ps.setString(2,año);
            ps.setString(3,tipo);
            rs=ps.executeQuery();
            while(rs.next()){
                monto=rs.getDouble(1);
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
        return monto;
    }
    
    public double getMontoDiario(String tipo, String dia, String mes, String año) {
         double monto= 0;
        try {
            ps=CON.conectar().prepareStatement("SELECT sum(monto) FROM movimientos WHERE dia=? AND mes=? AND año=? AND tipo=?");
            ps.setString(1,dia);
            ps.setString(2,mes);
            ps.setString(3,año);
            ps.setString(4,tipo);
            rs=ps.executeQuery();
            while(rs.next()){
                monto=rs.getDouble(1);
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
        return monto;
    }
    
    public double getGanancia(String tipo) {
         double monto= 0;
        try {
            ps=CON.conectar().prepareStatement("SELECT sum(ganancia) FROM movimientos WHERE tipo=?");
            ps.setString(1,tipo);
            rs=ps.executeQuery();
            while(rs.next()){
                monto=rs.getDouble(1);
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
        return monto;
    }
    
    public double getGananciaPeriodo(String tipo,String mes, String año) {
         double monto= 0;
        try {
            ps=CON.conectar().prepareStatement("SELECT sum(ganancia) FROM movimientos WHERE mes=? AND año=? AND tipo=?");
            ps.setString(1,mes);
            ps.setString(2,año);
            ps.setString(3,tipo);
            rs=ps.executeQuery();
            while(rs.next()){
                monto=rs.getDouble(1);
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
        return monto;
    }
    
    public double getGananciaDiario(String tipo, String dia, String mes, String año) {
         double monto= 0;
        try {
            ps=CON.conectar().prepareStatement("SELECT sum(ganancia) FROM movimientos WHERE dia=? AND mes=? AND año=? AND tipo=?");
            ps.setString(1,dia);
            ps.setString(2,mes);
            ps.setString(3,año);
            ps.setString(4,tipo);
            rs=ps.executeQuery();
            while(rs.next()){
                monto=rs.getDouble(1);
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
        return monto;
    }
    
    public double getMontoInicial() {
         double monto= 0;
        try {
            ps=CON.conectar().prepareStatement("SELECT monto FROM movimientos WHERE tipo=?");
            ps.setString(1,"I");
            rs=ps.executeQuery();
            while(rs.next()){
                monto=rs.getDouble(1);
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
        return monto;
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
            ps=CON.conectar().prepareStatement("SELECT count(*) as total FROM movimientos");
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
            ps=CON.conectar().prepareStatement("SELECT nombre FROM cliente WHERE nombre=?");
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
            ps=CON.conectar().prepareStatement("DELETE FROM movimientos WHERE idmovimientos=?");
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
