/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import database.Conexion;
import entidades.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import datos.interfaces.CrudPrestamoInterface;

/**
 *
 * @author Gabriel
 */
public class PrestamoQuinDAO {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public PrestamoQuinDAO() {
        CON = Conexion.getInstancia();
    }
    

    public List<PrestamoQuincenal> listar( int totalPorPagina, int numPagina) {
        List<PrestamoQuincenal> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT p.idprestamo,p.idCliente, p.prestamoNro, c.nombreApe as cliente_nombre, p.dia, p.mes, p.año, p.fechaFin, p.vencimiento, p.monto, p.cuotas, p.estado, p.totalApagar, p.interes, p.proxCuota FROM prestamoq p INNER JOIN cliente c ON p.idCliente=c.idcliente ORDER BY p.idprestamo ASC LIMIT ?,?");
            ps.setInt(1, (numPagina - 1) * totalPorPagina);
            ps.setInt(2, totalPorPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new PrestamoQuincenal(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10), rs.getInt(11), rs.getString(12),rs.getDouble(13),rs.getDouble(14), rs.getInt(15)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }
    
    public List<PrestamoQuincenal> listarBusquedaAño(String año) {
        List<PrestamoQuincenal> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT p.idprestamo,p.idCliente, p.prestamoNro, c.nombreApe as cliente_nombre, p.dia, p.mes, p.año, p.fechaFin, p.vencimiento, p.monto, p.cuotas, p.estado, p.totalApagar, p.interes, p.proxCuota FROM prestamoq p INNER JOIN cliente c ON p.idCliente=c.idcliente WHERE p.año=?");
            ps.setString(1, año);            
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new PrestamoQuincenal(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10), rs.getInt(11), rs.getString(12),rs.getDouble(13),rs.getDouble(14), rs.getInt(15)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }
    
    public List<PrestamoQuincenal> listarBusquedaPeriodo(String mes, String año) {
        List<PrestamoQuincenal> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT p.idprestamo,p.idCliente, p.prestamoNro, c.nombreApe as cliente_nombre, p.dia, p.mes, p.año, p.fechaFin, p.vencimiento, p.monto, p.cuotas, p.estado, p.totalApagar, p.interes, p.proxCuota FROM prestamoq p INNER JOIN cliente c ON p.idCliente=c.idcliente WHERE p.año=? AND mes=?");
            ps.setString(1, año);
            ps.setString(2, mes);            
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new PrestamoQuincenal(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10), rs.getInt(11), rs.getString(12),rs.getDouble(13),rs.getDouble(14), rs.getInt(15)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }
    
    public List<PrestamoQuincenal> listarBusquedaCliente(String cliente) {
        List<PrestamoQuincenal> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT p.idprestamo,p.idCliente, p.prestamoNro, c.nombreApe as cliente_nombre, p.dia, p.mes, p.año, p.fechaFin, p.vencimiento, p.monto, p.cuotas, p.estado, p.totalApagar, p.interes, p.proxCuota FROM prestamoq p INNER JOIN cliente c ON p.idCliente=c.idcliente WHERE clienteNom LIKE ?");
            ps.setString(1,"%" + cliente + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new PrestamoQuincenal(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10), rs.getInt(11), rs.getString(12),rs.getDouble(13),rs.getDouble(14), rs.getInt(15)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }
    
    public List<CuotasQuincenal> listarDetalle(int id) {
        List<CuotasQuincenal> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT iddetalleprestamo, cuotaNro, fechaPagar, fechaPagado,cuotaValor,interes,ganancia,estado,valorEntregado, cuotaPura FROM cuotasquincenales WHERE idPrestamo=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new CuotasQuincenal(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getDouble(9), rs.getDouble(10)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }


    public double valorCuota(int idPrestamo, int nroCuota) {
        double cuotaValor = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT cuotaValor FROM cuotasquincenales WHERE idPrestamo=? AND cuotaNro=?");
            ps.setInt(1, idPrestamo);
            ps.setInt(2, nroCuota);            
            rs = ps.executeQuery();
            while (rs.next()) {
                cuotaValor = rs.getDouble(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return cuotaValor;
    }
    
    public boolean editarCuotaValor(double cuotaValor, int idPrestamo, int nroCuota) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE cuotasquincenales SET cuotaValor=? WHERE idPrestamo=? AND cuotaNro=?");
            ps.setDouble(1, cuotaValor);
            ps.setInt(2, idPrestamo);
            ps.setInt(3, nroCuota);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
    
    public boolean editarCuotaestado(String estadoNuevo, int idPrestamo, String estadoAnt) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE cuotasquincenales SET estado=? WHERE idPrestamo=? AND estado=?");
            ps.setString(1,estadoNuevo);
            ps.setInt(2, idPrestamo);
            ps.setString(3, estadoAnt);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
    
    
public boolean setTotalPagar(double total, int idPrestamo) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE prestamoq SET totalApagar=? WHERE idp"
                    + "restamo=?");
            ps.setDouble(1, total);
            ps.setInt(2, idPrestamo);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

public boolean setProxCuota(int proxcuota, int idPrestamo) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE prestamoq SET proxCuota=? WHERE idprestamo=?");
            ps.setInt(1, proxcuota);
            ps.setInt(2, idPrestamo);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
    
    public int cantCuotasPagadas(int idPrestamo) {
        int cuota = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT count(estado) FROM cuotasquincenales WHERE idPrestamo=? AND estado='Pagado'");            
            ps.setInt(1, idPrestamo);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                cuota = rs.getInt(1);
            }            
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return cuota;
    }
    
    public int cantCuotasApagar(int idPrestamo) {
        int cuota = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT count(estado) FROM cuotasquincenales WHERE idPrestamo=? AND estado='A pagar'");            
            ps.setInt(1, idPrestamo);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                cuota = rs.getInt(1);
            }            
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return cuota;
    }
    
    public int cantCuotas(int idPrestamo) {
        int cuota = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT count(cuotaNro) FROM cuotasquincenales WHERE idPrestamo=?");            
            ps.setInt(1, idPrestamo);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                cuota = rs.getInt(1);
            }            
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return cuota;
    }
    
    public double MontoPrestado(int Prestamo) {
        double monto = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT monto FROM prestamoq WHERE idprestamo=?");            
            ps.setInt(1, Prestamo);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                monto = rs.getDouble(1);
            }            
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return monto;
    }
    
    public String getEstado(int idPrestamo) {
        String fechaPagado = "pitos";
        try {
            ps = CON.conectar().prepareStatement("SELECT estado FROM prestamoq WHERE idprestamo=?");
            ps.setInt(1, idPrestamo);
            rs = ps.executeQuery();
            if (rs.next()) {
                fechaPagado=rs.getString(1);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return fechaPagado;
    }
    
    public double getInteres(int idPrestamo) {
        double fechaPagado = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT interes FROM prestamoq WHERE idprestamo=?");
            ps.setInt(1, idPrestamo);
            rs = ps.executeQuery();
            if (rs.next()) {
                fechaPagado=rs.getDouble(1);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return fechaPagado;
    }
    
    public int ultimaCuota(int Prestamo) {
        int cuota = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT max(cuotaNro) FROM cuotasquincenales WHERE idPrestamo=?");
            ps.setInt(1, Prestamo);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                cuota = rs.getInt(1);
            }            
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return cuota;
    }
    
    public boolean setGanancia(double ganancia, int idPrestamo, int cuotanro) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE cuotasquincenales SET ganancia=? WHERE idPrestamo=? AND cuotaNro=?");
            ps.setDouble(1, ganancia);
            ps.setInt(2, idPrestamo);
            ps.setInt(3, cuotanro);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
    
    
    public Double sumarCuotasEstado(int idPrestamo, String estado) {
        double valorTotal = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT sum(cuotaValor) FROM cuotasquincenales WHERE idPrestamo=? AND estado=?");
            ps.setInt(1, idPrestamo);
            ps.setString(2, estado);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                valorTotal = rs.getDouble(1);
            }            
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return valorTotal;
    }
    
    public Double sumarGananciaEstado(int idPrestamo, String estado) {
        double valorTotal = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT sum(ganancia) FROM cuotasquincenales WHERE idPrestamo=? AND estado=?");
            ps.setInt(1, idPrestamo);
            ps.setString(2, estado);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                valorTotal = rs.getDouble(1);
            }            
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return valorTotal;
    }
    
    public Double sumarCuotas(int idPrestamo) {
        double valorTotal = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT sum(cuotaValor) FROM cuotasquincenales WHERE idPrestamo=?");
            ps.setInt(1, idPrestamo);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                valorTotal = rs.getDouble(1);
            }            
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return valorTotal;
    }
    
    public String ultimoNumero() {
        String numComprobante = "0";
        try {
            ps = CON.conectar().prepareStatement("SELECT prestamoNro FROM prestamoq order by prestamoNro desc limit 1");            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                numComprobante = rs.getString("prestamoNro");
            }            
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return numComprobante;
    }
    //CAMBIAR
    public double getCuotaPura(int idCuota) {
        double cuotaPura = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT cuotaPura FROM cuotasquincenales WHERE iddetalleprestamo=?");
            ps.setInt(1, idCuota);
            rs = ps.executeQuery();
            if (rs.next()) {
                cuotaPura=rs.getDouble(1);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return cuotaPura;
    }
    
    public boolean setCuotaPura(int idPrestamo,int cuotanro, double monto) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE cuotasquincenales SET cuotaPura=? WHERE idPrestamo=? AND cuotaNro=?");
            ps.setDouble(1, monto);
            ps.setInt(2, idPrestamo);
            ps.setInt(3, cuotanro);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
    
    public boolean insertar(PrestamoQuincenal obj) {
        resp = false;
        Connection conn = null;
        try {
            conn = CON.conectar();
            conn.setAutoCommit(false);
            String sqlInsertVenta = "INSERT INTO prestamoq (idCliente,ClienteNom,prestamoNro,dia,mes,año,vencimiento,monto,cuotas,totalApagar,estado,interes,proxCuota) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            ps = conn.prepareStatement(sqlInsertVenta, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, obj.getIdCliente());
            ps.setString(2, obj.getClienteNom());
            ps.setString(3, obj.getPrestamoNro());
            ps.setString(4, obj.getDia());
            ps.setString(5, obj.getMes());
            ps.setString(6, obj.getAño());
            ps.setInt(7, obj.getVencimiento());
            ps.setDouble(8, obj.getMonto());
            ps.setInt(9, obj.getCuotas());
            ps.setDouble(10, obj.getTotalPagar());
            ps.setString(11, "Aceptado");
            ps.setDouble(12, obj.getInteres());
            ps.setInt(13, obj.getProxCuota());
            
            int filasAfectadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            int idGenerado = 0;
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
            
            if (filasAfectadas == 1) {
                String sqlInsertDetalle = "INSERT INTO cuotasquincenales (idPrestamo,cuotaNro,fechaPagar,cuotaValor,interes,ganancia,estado,cuotaPura) VALUES ("
                        + "?,?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sqlInsertDetalle);
                for (CuotasQuincenal item : obj.getDetalles()) {
                    ps.setInt(1, idGenerado);
                    ps.setInt(2, item.getCoutaNro());
                    ps.setString(3, item.getFechaPagar());
                    ps.setDouble(4, item.getCuotaValor());
                    ps.setDouble(5, item.getInteres());
                    ps.setDouble(6, item.getGanancia());
                    ps.setString(7, item.getEstado());
                    ps.setDouble(8, item.getCuotaPura());
                    resp = ps.executeUpdate() > 0;
                }
                conn.commit();                
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(PrestamoQuinDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PrestamoQuinDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resp;
    }
    
    public boolean insertarNuevaCuota(CuotasQuincenal obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO cuotasquincenales (idPrestamo,cuotaNro,fechaPagar,cuotaValor,interes,ganancia,estado) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, obj.getIdPrestamo());
            ps.setInt(2, obj.getCoutaNro());
            ps.setString(3, obj.getFechaPagar());
            ps.setDouble(4, obj.getCuotaValor());
            ps.setDouble(5, obj.getInteres());
            ps.setDouble(6, obj.getGanancia());
            ps.setString(7, obj.getEstado());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        
        return resp;
    }
    
    public boolean anular(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE prestamoq SET estado='Anulado' WHERE idprestamo=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
    
    public boolean montoApagar(int idPrestamo,double monto) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE prestamoq SET totalApagar=? WHERE idprestamo=?");
            ps.setDouble(1, monto);
            ps.setInt(2, idPrestamo);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
    
    public boolean estadoCuota(int idCuota) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE cuotasquincenales SET estado='Pagado' WHERE iddetalleprestamo=?");
            ps.setInt(1, idCuota);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
    
    public boolean setEstadoPrestamo(String estado,int idPrestamo) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE prestamoq SET estado=? WHERE idprestamo=?");
            ps.setString(1, estado);
            ps.setInt(2, idPrestamo);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
    
    public boolean setFechaFinPrestamo(String fechaFin,int idPrestamo) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE prestamoq SET fechaFin=? WHERE idprestamo=?");
            ps.setString(1, fechaFin);
            ps.setInt(2, idPrestamo);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
    
    public boolean setFechaPagadoCuota(String fechaFin, int idCuota) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE cuotasquincenales SET fechaPagado=? WHERE iddetalleprestamo=?");
            ps.setString(1, fechaFin);
            ps.setInt(2, idCuota);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
    
    public String getFechaPagadoCuota(int idPrestamo, int cuota) {
        String fechaPagado = "pitos";
        try {
            ps = CON.conectar().prepareStatement("SELECT fechaPagado FROM cuotasquincenales WHERE idPrestamo=? AND cuotaNro=?");
            ps.setInt(1, idPrestamo);
            ps.setInt(2, cuota);
            rs = ps.executeQuery();
            if (rs.next()) {
                fechaPagado=rs.getString(1);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return fechaPagado;
    }
    
    public int getVencimiento(int idPrestamo) {
        int vencimiento = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT vencimiento FROM prestamoq WHERE idprestamo=?");
            ps.setInt(1, idPrestamo);
            rs = ps.executeQuery();
            if (rs.next()) {
                vencimiento=rs.getInt(1);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return vencimiento;
    }
    
    public boolean cambiarMontoPagar(int id, double monto) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE prestamoq SET totalApagar=? WHERE idprestamo=?");
            ps.setDouble(1, monto);
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
    
    public int total() {
        int totalRegistros = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT COUNT(idprestamo) FROM prestamoq");            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                totalRegistros = rs.getInt("COUNT(idprestamo)");
            }            
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return totalRegistros;
    }
    
    public boolean existe(String texto1) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("SELECT idprestamo FROM prestamoq WHERE prestamoNro=?");
            ps.setString(1, texto1);
            rs = ps.executeQuery();
            if (rs.next()) {
                resp = true;
            }            
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return resp;
    }
    
    public String getFechaCuota(int idPrestamo, int cuota) {
        String fechaCuota = "pitos";
        try {
            ps = CON.conectar().prepareStatement("SELECT fechaPagar FROM cuotasquincenales WHERE idPrestamo=? AND cuotaNro=?");
            ps.setInt(1, idPrestamo);
            ps.setInt(2, cuota);
            rs = ps.executeQuery();
            if (rs.next()) {
                fechaCuota=rs.getString(1);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return fechaCuota;
    }
    
    public List<PrestamoQuincenal> listarVencidos(int fechaHoy) {
        List<PrestamoQuincenal> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT P.idprestamo,p.idCliente, p.prestamoNro, c.nombreApe as cliente_nombre, p.dia, p.mes, p.año, p.fechaFin, p.vencimiento, p.monto, p.cuotas, p.estado, p.totalApagar, p.interes, p.proxCuota FROM prestamoq p INNER JOIN cliente c ON p.idCliente=c.idcliente WHERE (?>proxCuota) AND (p.estado='Aceptado')");
            ps.setInt(1, fechaHoy);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new PrestamoQuincenal(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10), rs.getInt(11), rs.getString(12),rs.getDouble(13),rs.getDouble(14), rs.getInt(15)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }
    
    public List<PrestamoQuincenal> listarPorVencer(int fechaHoy) {
        List<PrestamoQuincenal> registros = new ArrayList();
        int fechaLimite= fechaHoy+5;
        try {
            ps = CON.conectar().prepareStatement("SELECT P.idprestamo,p.idCliente, p.prestamoNro, c.nombreApe as cliente_nombre, p.dia, p.mes, p.año, p.fechaFin, p.vencimiento, p.monto, p.cuotas, p.estado, p.totalApagar, p.interes, p.proxCuota FROM prestamoq p INNER JOIN cliente c ON p.idCliente=c.idcliente WHERE (proxCuota>=?) AND (proxcuota<?)");
            ps.setInt(1, fechaHoy);
            ps.setInt(2, fechaLimite);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new PrestamoQuincenal(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10), rs.getInt(11), rs.getString(12),rs.getDouble(13),rs.getDouble(14), rs.getInt(15)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }
    
    public List<PrestamoQuincenal> listarVencenHoy(int fechaHoy) {
        List<PrestamoQuincenal> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT P.idprestamo,p.idCliente, p.prestamoNro, c.nombreApe as cliente_nombre, p.dia, p.mes, p.año, p.fechaFin, p.vencimiento, p.monto, p.cuotas, p.estado, p.totalApagar, p.interes, p.proxCuota FROM prestamoq p INNER JOIN cliente c ON p.idCliente=c.idcliente WHERE ?=proxCuota");
            ps.setInt(1, fechaHoy);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new PrestamoQuincenal(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10), rs.getInt(11), rs.getString(12),rs.getDouble(13),rs.getDouble(14), rs.getInt(15)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }
    
}
