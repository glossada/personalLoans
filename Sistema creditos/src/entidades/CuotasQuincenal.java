
package entidades;


public class CuotasQuincenal {
    private int idDetallePrestamo;
    private int idPrestamo;
    private int coutaNro;
    private String fechaPagar;
    private String fechaPagado;
    private double cuotaValor;
    private double interes;
    private double ganancia;
    private String estado;
    private double valorEntregado;
    private double cuotaPura;
    

    public CuotasQuincenal() {
    }
    
//    public CuotasMensual(int idPrestamo, int coutaNro, String fechaPagar, String fechaPagado, double cuotaValor, double interes, double ganancia,String estado,double valorEntregado) {
//        this.idPrestamo=idPrestamo;
//        this.coutaNro = coutaNro;
//        this.fechaPagar = fechaPagar;
//        this.fechaPagado = fechaPagado;
//        this.cuotaValor = cuotaValor;
//        this.interes = interes;
//        this.ganancia=ganancia;
//        this.estado = estado;
//        this.valorEntregado=valorEntregado;
//    }
    
    
//listar
    public CuotasQuincenal(int idDetallePrestamo,int coutaNro, String fechaPagar, String fechaPagado,double cuotaValor, double interes, double ganancia,String estado, double valorEntregado,double cuotaPura) {
        this.idDetallePrestamo=idDetallePrestamo;
        this.coutaNro = coutaNro;
        this.fechaPagar = fechaPagar;
        this.fechaPagado = fechaPagado;
        this.cuotaValor = cuotaValor;
        this.interes = interes;
        this.ganancia=ganancia;
        this.estado = estado;
        this.valorEntregado=valorEntregado;
        this.cuotaPura=cuotaPura;
    }

    public CuotasQuincenal(int coutaNro, String fechaPagar, double cuotaValor, double interes, double ganancia ,String estado, double cuotaPura) {
        this.coutaNro = coutaNro;
        this.fechaPagar = fechaPagar;
        this.cuotaValor = cuotaValor;
        this.interes = interes;
        this.estado = estado;
        this.cuotaPura=cuotaPura;
        this.ganancia=ganancia;
    }
    
    

    public int getIdDetallePrestamo() {
        return idDetallePrestamo;
    }

    public void setIdDetallePrestamo(int idDetallePrestamo) {
        this.idDetallePrestamo = idDetallePrestamo;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getCoutaNro() {
        return coutaNro;
    }

    public void setCoutaNro(int coutaNro) {
        this.coutaNro = coutaNro;
    }

    public String getFechaPagar() {
        return fechaPagar;
    }

    public void setFechaPagar(String fechaPagar) {
        this.fechaPagar = fechaPagar;
    }

    public String getFechaPagado() {
        return fechaPagado;
    }

    public void setFechaPagado(String fechaPagado) {
        this.fechaPagado = fechaPagado;
    }

    public double getCuotaValor() {
        return cuotaValor;
    }

    public void setCuotaValor(double cuotaValor) {
        this.cuotaValor = cuotaValor;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public double getValorEntregado() {
        return valorEntregado;
    }

    public void setValorEntregado(double valorEntregado) {
        this.valorEntregado = valorEntregado;
    }

    public double getCuotaPura() {
        return cuotaPura;
    }

    public void setCuotaPura(double cuotaPura) {
        this.cuotaPura = cuotaPura;
    }

    @Override
    public String toString() {
        return "CuotasQuincenal{" + "idDetallePrestamo=" + idDetallePrestamo + ", idPrestamo=" + idPrestamo + ", coutaNro=" + coutaNro + ", fechaPagar=" + fechaPagar + ", fechaPagado=" + fechaPagado + ", cuotaValor=" + cuotaValor + ", interes=" + interes + ", ganancia=" + ganancia + ", estado=" + estado + ", valorEntregado=" + valorEntregado + ", cuotaPura=" + cuotaPura + '}';
    }
    
    

    

   
    
    
    
}
