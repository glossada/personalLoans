package entidades;

// * @author Gabriel Lossada
public class Movimientos {
    private int idMovimiento;
    private int idCliente;
    private String cliente;
    private String descripcion;
    private double monto;
    private double soloGanancia;
    private String dia;
    private String mes;
    private String año;
    private String tipo;

    public Movimientos() {
    }
    //agregar

    public Movimientos(int idCliente, String cliente, String descripcion, double monto, double soloGanancia, String dia, String mes, String año, String tipo) {
        this.idCliente = idCliente;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.monto = monto;
        this.soloGanancia = soloGanancia;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.tipo = tipo;
    }

   
    //listar

    public Movimientos(int idMovimiento, int idCliente, String cliente, String descripcion, double monto, double soloGanancia, String dia, String mes, String año, String tipo) {
        this.idMovimiento = idMovimiento;
        this.idCliente = idCliente;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.monto = monto;
        this.soloGanancia = soloGanancia;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.tipo = tipo;
    }

    
    
    
    
    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getSoloGanancia() {
        return soloGanancia;
    }

    public void setSoloGanancia(double soloGanancia) {
        this.soloGanancia = soloGanancia;
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
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Movimientos{" + "idMovimiento=" + idMovimiento + ", idCliente=" + idCliente + ", cliente=" + cliente + ", descripcion=" + descripcion + ", monto=" + monto + ", soloGanancia=" + soloGanancia + ", dia=" + dia + ", mes=" + mes + ", a\u00f1o=" + año + ", tipo=" + tipo + '}';
    }
    
    


    
    

    

   

   
    
}
