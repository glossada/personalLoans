/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.MovimientoDAO;
import entidades.Movimientos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel Lossada
 */
public class MovimientosControl {

    private final MovimientoDAO DATOS;
    private Movimientos obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public MovimientosControl() {
        this.DATOS = new MovimientoDAO();
        this.obj = new Movimientos();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listarPeriodo(String mes, String año) {
        List<Movimientos> lista = new ArrayList();
        lista.addAll(DATOS.listarPeriodo(mes,año));
        String monto;
        String tipo;

        String[] titulos = {"Id","id cliente","Cliente", "Descripcion", "fecha", "monto", "tipo"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[7];

        this.registrosMostrados = 0;
        for (Movimientos item : lista) {
            registro[0] = Integer.toString(item.getIdMovimiento());
            registro[1]=Integer.toString(item.getIdCliente());
            registro[2]=item.getCliente();
            registro[3]=item.getDescripcion();
            tipo = item.getTipo();
            if (tipo.equals("S")) {
                monto = "-" + Double.toString(item.getMonto());
            } else {
                monto = Double.toString(item.getMonto());
            }
            registro[4] = item.getDia()+"/"+item.getMes()+"/"+item.getAño();
            registro[5] = monto;
            registro[6] = tipo;

            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }
    
    public DefaultTableModel listarDiario(String dia,String mes, String año) {
        List<Movimientos> lista = new ArrayList();
        lista.addAll(DATOS.listarDiario(dia,mes,año));
        String monto;
        String tipo;

        String[] titulos = {"Id", "id cliente","Cliente","Descripcion","fecha", "monto", "tipo"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[7];

        this.registrosMostrados = 0;
        for (Movimientos item : lista) {
            registro[0] = Integer.toString(item.getIdMovimiento());
            registro[1]=Integer.toString(item.getIdCliente());
            registro[2]=item.getCliente();
            registro[3]=item.getDescripcion();
            tipo = item.getTipo();
            if (tipo.equals("S")) {
                monto = "-" + Double.toString(item.getMonto());
            } else {
                monto = Double.toString(item.getMonto());
            }
            registro[4] = item.getDia()+"/"+item.getMes()+"/"+item.getAño();
            registro[5] = monto;
            registro[6] = tipo;

            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

//    
    public String insertar(int idCliente,String cliente,String descripcion,double monto, double ganancia, String dia, String mes, String año, String tipo) {
        obj.setIdCliente(idCliente);
        obj.setCliente(cliente);
        obj.setDescripcion(descripcion);
        obj.setMonto(monto);
        obj.setSoloGanancia(ganancia);
        obj.setDia(dia);
        obj.setMes(mes);
        obj.setAño(año);
        obj.setTipo(tipo);

        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el registro.";
        }
    }
    
    public double sumGanancia(String tipo){
        return DATOS.getGanancia(tipo);
    }
    
    public void setTipo(int idCliente, double monto,String dia, String mes, String año,String tipo){
        DATOS.setTipo(idCliente, monto, dia, mes, año, tipo);
    }
    
    public double sumMonto(String tipo){
        return DATOS.getMonto(tipo);
    }
    
     public double getMontoPD(String tipo, String dia, String mes, String año){
         if(dia.equals("0")){
             return DATOS.getMontoPeriodo(tipo, mes, año);
         }else{
             return DATOS.getMontoDiario(tipo, dia, mes, año);
         }
     }
     public double getGananciaPD(String tipo, String dia, String mes, String año){
         if(dia.equals("0")){
             return DATOS.getGananciaPeriodo(tipo, mes, año);
         }else{
             return DATOS.getGananciaDiario(tipo, dia, mes, año);
         }
     }



    public int totalReg() {
        return DATOS.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }
    
    public double getMontoInicial(){
        return DATOS.getMontoInicial();
    }

    public String eliminar(int id) {
        if (DATOS.eliminar(id)) {
            return "OK";
        } else {
            return "No se puede eliminar el registro nadie sabe porque, estamos todos confundidos y agobiados";
        }
    }
}
