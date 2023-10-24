package entidades;

public class Cliente {

    private int idCliente;
    private String nombreApe;
    private int edad;
    private String dniCuit;
    private String direccion;
    private String email;
    private String tel1;
    private String tel2;
    private String estado;

    public Cliente() {
    }
//AGREGAR

    public Cliente(String nombreApe, int edad, String DniCuit, String direccion, String email, String Tel1, String Tel2, String estado) {
        this.nombreApe = nombreApe;
        this.edad = edad;
        this.dniCuit = DniCuit;
        this.direccion = direccion;
        this.email = email;
        this.tel1 = Tel1;
        this.tel2 = Tel2;
        this.estado = estado;
    }
   


//MODIFICAR - listar
    public Cliente(int idCliente, String nombreApe, int edad, String DniCuit, String direccion, String email, String Tel1, String Tel2, String estado) {    
        this.idCliente = idCliente;
        this.nombreApe = nombreApe;
        this.edad = edad;
        this.dniCuit = DniCuit;
        this.direccion = direccion;
        this.email = email;
        this.tel1 = Tel1;
        this.tel2 = Tel2;
        this.estado = estado;
    }

//BUSCAR - anular
    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombreApe;
    }

    public void setNombre(String nombre) {
        this.nombreApe = nombre;
    }


    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDniCuit() {
        return dniCuit;
    }

    public void setDniCuit(String DniCuit) {
        this.dniCuit = DniCuit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreApe() {
        return nombreApe;
    }

    public void setNombreApe(String nombreApe) {
        this.nombreApe = nombreApe;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String Tel1) {
        this.tel1 = Tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String Tel2) {
        this.tel2 = Tel2;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombreApe=" + nombreApe + ", edad=" + edad + ", DniCuit=" + dniCuit + ", direccion=" + direccion + ", email=" + email + ", Tel1=" + tel1 + ", Tel2=" + tel2 + ", estado=" + estado + '}';
    }

    







   

}
