package negocio;

/**
 *
 * @author Gabriel Lossada
 */
public class Variables {
    public static int usuarioId;
    public static String usuario;
    public static String usuarioNom;
    
    
    public static int roundUp(double number, int multiple) {

        int result = multiple;

        if (number % multiple == 0) {
            return (int) number;
        }

        // If not already multiple of given number

        if (number % multiple != 0) {

            int division = (int) ((number / multiple) + 1);

            result = division * multiple;

        }
        return result;

    }
}

