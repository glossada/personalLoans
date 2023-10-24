/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;


import database.Conexion;
import datos.*;
import entidades.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 */
public class PrestamoControl {
    private final PrestamoMenDAO DATOS;
    private PrestamoMensual obj;
    private CuotasMensual objc;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public PrestamoControl(){
        this.DATOS=new PrestamoMenDAO();
        this.obj=new PrestamoMensual();
        this.objc=new CuotasMensual();
        this.registrosMostrados=0;
    }
    
    public int fechaStringToInt (String fecha){
        int fechaInt=0;
     String[] part=fecha.split("/");
     String dia=part[0];
     if(dia.length()<2){
         dia="0"+dia;
     }
     String mes=part[1];
     if(mes.length()<2){
         mes="0"+mes;
     }
     String año=part[2];
     fecha=año+mes+dia;
     fechaInt=Integer.parseInt(fecha);
     return fechaInt;
    }
    
    public DefaultTableModel listar(String mes, String año,int totalPorPagina,int numPagina){
        List<PrestamoMensual> lista=new ArrayList();
        if(mes.equals("0") && año.equals("0")){
            lista.addAll(DATOS.listar(totalPorPagina,numPagina));    
        }else if(mes.equals("0")){
            lista.addAll(DATOS.listarBusquedaAño(año));
        }else {
            lista.addAll(DATOS.listarBusquedaPeriodo(mes,año));
        }
        
        String[] titulos={"Id","Id Cliente","Prestamo Nro","Cliente","fecha inicio","Proxima cuota" ,"fecha fin","monto Prestado","monto a pagar","cuotas", "estado"};
        this.modeloTabla=new DefaultTableModel(null,titulos);        
        String fechaInicio;
        String proxCuota;
        String[] registro = new String[11];
        
        this.registrosMostrados=0;
        for (PrestamoMensual item:lista){
            registro[0]=Integer.toString(item.getIdPrestamo());
            registro[1]=Integer.toString(item.getIdCliente());
            registro[2]=item.getPrestamoNro();
            registro[3]=item.getClienteNom();
            fechaInicio=item.getDia()+"/"+item.getMes()+"/"+item.getAño();
            registro[4]=fechaInicio;
            proxCuota=Integer.toString(item.getProxCuota());
             if(proxCuota.equals("0")){
                registro[5]="-";
            }else if(proxCuota.equals("1")){
                registro[5]="Finalizado";
            }else{
            String añoP=proxCuota.substring(0, 2);
            String mesP=proxCuota.substring(2, 4);
            String diaP=proxCuota.substring(4, 6);
            registro[5]=diaP+"/"+mesP+"/"+añoP;
            }
            registro[6]=item.getFechaFin();
            registro[7]=Double.toString(item.getMonto());
            registro[8]=Double.toString(item.getTotalPagar());
            registro[9]=Integer.toString(item.getCuotas());
            registro[10]=item.getEstado();

            
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
    
    public DefaultTableModel listarBuscarCliente(String cliente){
        List<PrestamoMensual> lista=new ArrayList();
        lista.addAll(DATOS.listarBusquedaCliente(cliente));    
        
        String[] titulos={"Id","Id Cliente","Prestamo Nro","Cliente","fecha inicio","Proxima cuota" ,"fecha fin","monto Prestado","monto a pagar","cuotas", "estado"};
        this.modeloTabla=new DefaultTableModel(null,titulos);        
        String fechaInicio;
        String proxCuota;
        String[] registro = new String[11];
        
        this.registrosMostrados=0;
        for (PrestamoMensual item:lista){
            registro[0]=Integer.toString(item.getIdPrestamo());
            registro[1]=Integer.toString(item.getIdCliente());
            registro[2]=item.getPrestamoNro();
            registro[3]=item.getClienteNom();
            fechaInicio=item.getDia()+"/"+item.getMes()+"/"+item.getAño();
            registro[4]=fechaInicio;
            proxCuota=Integer.toString(item.getProxCuota());
             if(proxCuota.equals("0")){
                registro[5]="-";
            }else if(proxCuota.equals("1")){
                registro[5]="Finalizado";
            }else{
            String añoP=proxCuota.substring(0, 2);
            String mesP=proxCuota.substring(2, 4);
            String diaP=proxCuota.substring(4, 6);
            registro[5]=diaP+"/"+mesP+"/"+añoP;
            }
            registro[6]=item.getFechaFin();
            registro[7]=Double.toString(item.getMonto());
            registro[8]=Double.toString(item.getTotalPagar());
            registro[9]=Integer.toString(item.getCuotas());
            registro[10]=item.getEstado();

            
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
    
     public DefaultTableModel listarVencimientos(int fechaHoy, String tipo){
        List<PrestamoMensual> lista=new ArrayList();
        if(tipo.equals("vencido")){
            lista.addAll(DATOS.listarVencidos(fechaHoy));    
        }else if(tipo.equals("por vencer")){
            lista.addAll(DATOS.listarPorVencer(fechaHoy));
        }else {
            lista.addAll(DATOS.listarVencenHoy(fechaHoy));
        }
        
        String[] titulos={"Id","Id Cliente","Prestamo Nro","Cliente","fecha inicio","Proxima cuota" ,"fecha fin","monto Prestado","monto a pagar","cuotas", "estado"};
        this.modeloTabla=new DefaultTableModel(null,titulos);        
        String fechaInicio;
        String proxCuota;
        String[] registro = new String[11];
        
        this.registrosMostrados=0;
        for (PrestamoMensual item:lista){
            registro[0]=Integer.toString(item.getIdPrestamo());
            registro[1]=Integer.toString(item.getIdCliente());
            registro[2]=item.getPrestamoNro();
            registro[3]=item.getClienteNom();
            fechaInicio=item.getDia()+"/"+item.getMes()+"/"+item.getAño();
            registro[4]=fechaInicio;
            proxCuota=Integer.toString(item.getProxCuota());
             if(proxCuota.equals("0")){
                registro[5]="-";
            }else if(proxCuota.equals("1")){
                registro[5]="Finalizado";
            }else{
            String añoP=proxCuota.substring(0, 2);
            String mesP=proxCuota.substring(2, 4);
            String diaP=proxCuota.substring(4, 6);
            registro[5]=diaP+"/"+mesP+"/"+añoP;
            }
            registro[6]=item.getFechaFin();
            registro[7]=Double.toString(item.getMonto());
            registro[8]=Double.toString(item.getTotalPagar());
            registro[9]=Integer.toString(item.getCuotas());
            registro[10]=item.getEstado();

            
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
    
public int vencimientosCantidad (String fechaHoy, String tipo){
    String part[]=fechaHoy.split("/");
    String dia=part[0];
    if(dia.length()<2){
        dia="0"+dia;
    }
    String mes=part[1];
    if(mes.length()<2){
        mes="0"+mes;
    }
    String año=part[2];
    int fechaInt=Integer.parseInt(año+mes+dia);
     List<PrestamoMensual> lista=new ArrayList();
     int cantidad;
     
        if(tipo.equals("vencido")){
            lista.addAll(DATOS.listarVencidos(fechaInt));
            cantidad=lista.size();
        }else if(tipo.equals("por vencer")){
            lista.addAll(DATOS.listarPorVencer(fechaInt));
            cantidad=lista.size();
        }else {
            lista.addAll(DATOS.listarVencenHoy(fechaInt));
            cantidad=lista.size();
        }
        return cantidad;
}
    
    public DefaultTableModel listarDetalle(int id){
        List<CuotasMensual> lista=new ArrayList();
        lista.addAll(DATOS.listarDetalle(id));
        
        String[] titulos={"id cuota","Cuota Nro","Fecha a pagar","Fecha pagado","Valor cuota","ganancia","Interes","Valor entregado","Estado"};
        this.modeloTabla=new DefaultTableModel(null,titulos);        
        
        String[] registro = new String[9];
        
        for (CuotasMensual item:lista){
            registro[0]=Integer.toString(item.getIdDetallePrestamo());
            registro[1]=Integer.toString(item.getCoutaNro());
            registro[2]=item.getFechaPagar();
            registro[3]=item.getFechaPagado();
            registro[4]=Double.toString(item.getCuotaValor());
            registro[5]=Double.toString(item.getGanancia());
            registro[6]=Double.toString(item.getInteres());
            registro[7]=Double.toString(item.getValorEntregado());
            registro[8]=item.getEstado();            
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }
    
    
    public String ultimoNumero() {
        return this.DATOS.ultimoNumero();
    }
    
    public List calcularCuota(int cantidadC, double montoT,double interes){
        double ganancia=Math.ceil((montoT*interes)/100);
        double cuotaPura=Math.ceil(montoT/cantidadC);
        double valorCuotaFinal=cuotaPura+ganancia;
        List<Double> lista = new ArrayList<Double>();
        lista.add(ganancia);
        lista.add(valorCuotaFinal);
        lista.add(cuotaPura);
        return lista;
    }
    
    public String calcularFechaCuota(String fecha, String fechaVenc){
        String[] part=fecha.split("/");
        String fechaFinal;
        String dia = fechaVenc;
        int mes = Integer.parseInt(part[1]);
        int año = Integer.parseInt(part[2]);
        
        if(mes==12){
            año=año+1;
            fecha=dia+"/"+"1"+"/"+año;
        }else{
            mes=mes+1;
            fecha=dia+"/"+mes+"/"+año;
        }
        
        
        return fecha;
    }
            
    
    public String insertar(int clienteId, String cliente, String prestamoNro, String dia, String mes, String año,double monto, int cuotas, double totalApagar,String estado, double interesP, int proxCuota,DefaultTableModel modeloDetalles){
        if (DATOS.existe(prestamoNro)){
            return "El registro ya existe.";
        }else{
            obj.setIdCliente(clienteId);
            obj.setClienteNom(cliente);
            obj.setPrestamoNro(prestamoNro);
            obj.setDia(dia);
            obj.setMes(mes);
            obj.setAño(año);
            obj.setMonto(monto);
            obj.setCuotas(cuotas);
            obj.setTotalPagar(totalApagar);
            obj.setEstado(estado);
            obj.setInteres(interesP);
            obj.setProxCuota(proxCuota);
                        
            List<CuotasMensual> detalles = new ArrayList();
            int cuotaNro;
            String fechaPagar;
            String fechaPagado;
            double valorCuota;
            double interes;
            String estadoDet;
            double ganancia;
            double valorEntregado;
            double cuotaPura;
            
            for (int i=0;i<modeloDetalles.getRowCount();i++){
                cuotaNro=Integer.parseInt(String.valueOf(modeloDetalles.getValueAt(i, 0)));
                fechaPagar=String.valueOf(modeloDetalles.getValueAt(i, 1));
                fechaPagado=String.valueOf(modeloDetalles.getValueAt(i, 2));
                valorCuota=Double.parseDouble(String.valueOf(modeloDetalles.getValueAt(i, 3)));
                interes=Double.parseDouble(String.valueOf(modeloDetalles.getValueAt(i, 4)));
                estadoDet=String.valueOf(modeloDetalles.getValueAt(i, 7));
                ganancia=Double.parseDouble(String.valueOf(modeloDetalles.getValueAt(i, 6)));
                valorEntregado=Double.parseDouble(String.valueOf(modeloDetalles.getValueAt(i, 5)));
                cuotaPura=Double.parseDouble(String.valueOf(modeloDetalles.getValueAt(i, 8)));
                
                
                detalles.add(new CuotasMensual(cuotaNro,fechaPagar,valorCuota,interes,ganancia,estadoDet,cuotaPura));
                
                
            }
            
            obj.setDetalles(detalles);
            
            if (DATOS.insertar(obj)){
                return "OK";
            }else{
                return "Error en el registro.";
            }
        }
    }
    
    public String insertarNuevaCuota(int idPrestamo,int cuotaNro,String fechaPagar,double cuotaValor,double interes,double ganancia,String estado) {
            objc.setIdPrestamo(idPrestamo);
            objc.setCoutaNro(cuotaNro);
            objc.setFechaPagar(fechaPagar);
            objc.setCuotaValor(cuotaValor);
            objc.setInteres(interes);
            objc.setGanancia(ganancia);
            objc.setEstado(estado);

            if (DATOS.insertarNuevaCuota(objc)) {
                return "OK";
            } else {
                return "Error en el registro.";
            }
    }
    
    
    
    public String anular(int id,String estado){
        if (DATOS.anular(id,estado)){
            return "OK";
        }else{
            return "No se puede anular el registro";
        }
    }
    
    public void fechaCancelado(){
        
    }
    
    
    public void setEstadoPrestamo(String estado, int idPrestamo){
        DATOS.setEstadoPrestamo(estado, idPrestamo);
    }
    
    public String getFechaPagadoCuota(int idPrestamo, int cuota){
        return DATOS.getFechaPagadoCuota(idPrestamo, cuota);
    }    
    
    public double getInteres(int idPrestamo){
        return DATOS.getInteres(idPrestamo);
    }
    
    public void setFechaFinPrestamoCancelar(String fechaFin,int idPrestamo){
        DATOS.setFechaFinPrestamo(fechaFin, idPrestamo);
    }
    
    public void FechaPagadoCuota(String fechaFin,int idPrestamo){
        DATOS.setFechaPagadoCuota(fechaFin, idPrestamo);
    }
    
    public void montoApagar (int idPrestamo, double motno){
        DATOS.montoApagar(idPrestamo, motno);
    }
    
    public int cantCuotasPagadas(int idPrestamo){
        return DATOS.cantCuotasPagadas(idPrestamo);
    }
    
    public int cantCuotasAPagar(int idPrestamo){
        return DATOS.cantCuotasApagar(idPrestamo);
    }
    
    public int cantCuotas(int idPrestamo){
        return DATOS.cantCuotas(idPrestamo);
    }
    
    public double montoPrestado(int idPrestamo){
        return DATOS.MontoPrestado(idPrestamo);
    }
    
    public double sumarCuotas(int idPrestamo){
        return DATOS.sumarCuotas(idPrestamo);
    }
    public double sumarCuotasEstado(int idPrestamo,String estado){
        return DATOS.sumarCuotasEstado(idPrestamo,estado);
    }
    
    public void estadoCuota(int id){
       DATOS.estadoCuota(id);
    }
    
    public double valorCuota(int idPrestamo, int nroCuota){
       return DATOS.valorCuota(idPrestamo, nroCuota);
    }
    
    public void editarCuotaValor(double cuotaValor,int idPrestamo, int nroCuota){
        DATOS.editarCuotaValor(cuotaValor, idPrestamo, nroCuota);
    }
    
    public int totalReg(){
        return DATOS.total();
    }
    public double getCuotaPura(int idCuota){
        return DATOS.getCuotaPura(idCuota);
    }
    
    public double getCuotaPuraCuotaNo(int idPrestamo, int cuotanro){
        return DATOS.getCuotaPuraCuotaNo(idPrestamo,cuotanro);
    }
    
    public int totalMostrados(){
        return this.registrosMostrados;
    }
    
    public String getEstado(int idPrestamo){
        return DATOS.getEstado(idPrestamo);
    }
    
    public String setTotalPagar(int id,double total){
        if (DATOS.setTotalPagar(total,id)){
            return "OK";
        }else{
            return "No se actualizar el total a pagar";
        }
    }
    
    public String setGanancia(int id, int cuotanro, double ganancia){
        if (DATOS.setGanancia(ganancia,id,cuotanro)){
            return "OK";
        }else{
            return "No se actualizo ganancia";
        }
    }
    
    public String setCuotaPura(int idPrestamo, int cuotanro,double monto){
        if (DATOS.setCuotaPura(idPrestamo,cuotanro,monto)){
            return "OK";
        }else{
            return "No se actualizo cuota pura";
        }
    }
    
   public double sumarGananciaEstado(int idPrestamo,String estado){
        return DATOS.sumarGananciaEstado(idPrestamo,estado);
    }
   
   public String editarCuotaEstado(String estadoNuevo, int idPrestamo,String estadoAnt){
        if (DATOS.editarCuotaestado(estadoNuevo,idPrestamo,estadoAnt)){
            return "OK";
        }else{
            return "No se actualizo estado de la cuota";
        }
    }
   
   public String setProxCuota(String proxcuota, int idPrestamo){
        int proxcuotaInt;
        if(proxcuota.length()>1){
        String part[]=proxcuota.split("/");
        String dia = part[0];
        if(dia.length()<2){
            dia="0"+dia;
        }
        String mes = part[1];
        if(mes.length()<2){
            mes="0"+mes;
        }
        String año = part[2];
        proxcuota=año+mes+dia;
        proxcuotaInt = Integer.parseInt(proxcuota);
        }else{
        proxcuotaInt=1;
        }
        if (DATOS.setProxCuota(proxcuotaInt,idPrestamo)){
            return "OK";
        }else{
            return "No se actualizo proxima cuota";
        }
   }
   
   public String getFechaCuota(int idprestamo, int nrocuota){
       String fecha=DATOS.getFechaCuota(idprestamo,nrocuota); 
       if (fecha.equals("pitos")){
            return "nada";
        }else{
            return fecha;
        }
   }
    
    public void reporteReciboVenta(String idVenta){
        Map p=new HashMap();
        p.put("idVenta", idVenta);
        JasperReport report;
        JasperPrint print;
        
        Conexion cnn=Conexion.getInstancia();
        
        try {
            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+
                    "/src/reportes/ReporteVentas.jrxml");
            print=JasperFillManager.fillReport(report, p,cnn.conectar());
            JasperViewer view=new JasperViewer(print,false);
            view.setTitle("Reporte de Artículos");
            view.setVisible(true);
        } catch (JRException e) {
            e.getMessage();
        }
    }
}
