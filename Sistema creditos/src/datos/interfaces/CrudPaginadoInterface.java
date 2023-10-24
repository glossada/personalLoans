package datos.interfaces;

import java.util.List;



public interface CrudPaginadoInterface <T> {
    public List<T> listar(String texto, int totalPorPag, int nomeroDePag);
    public boolean insertar(T obj);
    public boolean actualizar (T obj);
    public boolean desactivar (int id);
    public boolean activar (int id);
    public int total();
    public boolean existe(String texto);
    public boolean eliminar (int id);
}
