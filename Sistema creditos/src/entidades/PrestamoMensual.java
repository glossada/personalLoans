package entidades;

import java.util.List;

public class PrestamoMensual {
    private int idPrestamo;
    private int idCliente;
    private String prestamoNro;
    private String clienteNom;
    private String dia;
    private String mes;
    private String año;
    private String fechaFin;
    private double monto;
    private double totalPagar;
    private double interes;
    private int cuotas;
    private String estado;
    private int proxCuota;
    private List<CuotasMensual> detalles;

    public PrestamoMensual() {
    }
//agregar
    public PrestamoMensual(int idCliente, String prestamoNro, String clienteNom, String dia, String mes, String año, String fechaFin, double monto, int cuotas, double totalPagar,String estado, double interes, int proxCuota) {
        this.idCliente = idCliente;
        this.prestamoNro=prestamoNro;
        this.clienteNom = clienteNom;
        this.dia=dia;
        this.mes=mes;
        this.año=año;
        this.fechaFin = fechaFin;
        this.monto = monto;
        this.cuotas = cuotas;
        this.totalPagar=totalPagar;
        this.estado = estado;
        this.interes=interes;
        this.proxCuota=proxCuota;
    }
//completo modificar
    public PrestamoMensual(int idPrestamo, int idCliente, String prestamoNro, String clienteNom, String dia, String mes, String año, String fechaFin, double monto, int cuotas, String estado, double interes, int proxCuota, List<CuotasMensual> detalles) {
        this.idPrestamo = idPrestamo;
        this.idCliente = idCliente;
        this.prestamoNro=prestamoNro;
        this.clienteNom = clienteNom;
        this.dia=dia;
        this.mes=mes;
        this.año=año;
        this.fechaFin = fechaFin;
        this.monto = monto;
        this.cuotas = cuotas;
        this.estado = estado;
        this.interes=interes;
        this.proxCuota=proxCuota;
        this.detalles = detalles;
    }
//listar
    public PrestamoMensual(int idPrestamo, int idCliente, String prestamoNro,String clienteNom, String dia, String mes, String año, String fechaFin, double monto, int cuotas, String estado,double totalPagar, double interes, int proxCuota) {
        this.idPrestamo = idPrestamo;
        this.idCliente = idCliente;
        this.prestamoNro=prestamoNro;
        this.clienteNom = clienteNom;
        this.dia=dia;
        this.mes=mes;
        this.año=año;
        this.fechaFin = fechaFin;
        this.monto = monto;
        this.cuotas = cuotas;
        this.estado = estado;
        this.totalPagar=totalPagar;
        this.interes=interes;
        this.proxCuota=proxCuota;
    }
    
    

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getPrestamoNro() {
        return prestamoNro;
    }

    public void setPrestamoNro(String prestamoNro) {
        this.prestamoNro = prestamoNro;
    }
    
    

    public String getClienteNom() {
        return clienteNom;
    }

    public void setClienteNom(String clienteNom) {
        this.clienteNom = clienteNom;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }


    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

  

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<CuotasMensual> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<CuotasMensual> detalles) {
        this.detalles = detalles;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public int getProxCuota() {
        return proxCuota;
    }

    public void setProxCuota(int proxCuota) {
        this.proxCuota = proxCuota;
    }

    @Override
    public String toString() {
        return "PrestamoMensual{" + "idPrestamo=" + idPrestamo + ", idCliente=" + idCliente + ", prestamoNro=" + prestamoNro + ", clienteNom=" + clienteNom + ", dia=" + dia + ", mes=" + mes + ", a\u00f1o=" + año + ", fechaFin=" + fechaFin + ", monto=" + monto + ", totalPagar=" + totalPagar + ", interes=" + interes + ", cuotas=" + cuotas + ", estado=" + estado + ", proxCuota=" + proxCuota + ", detalles=" + detalles + '}';
    }
    
}
