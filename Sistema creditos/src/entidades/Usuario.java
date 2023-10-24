package entidades;


public class Usuario {
   private int idUsuario;
   private String usuario;
   private String nombreApe;
   private String password;

    public Usuario() {
    }
//editar // listar
    public Usuario(int idUsuario, String usuario, String nombre, String password) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nombreApe = nombre;
        this.password = password;
    }
//agregar
    public Usuario(String usuario, String nombre, String password) {
        this.usuario = usuario;
        this.nombreApe = nombre;
        this.password = password;
    }
// login
    public Usuario(int idUsuario, String usuario, String nombreApe) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nombreApe = nombreApe;
    }

   
   
   
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreApe() {
        return nombreApe;
    }

    public void setNombreApe(String nombre) {
        this.nombreApe = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", usuario=" + usuario + ", nombreApe=" + nombreApe + ", password=" + password + '}';
    }
   
   
}
