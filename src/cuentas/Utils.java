package cuentas;

import java.util.Random;

public class Utils {
    // generar nuevo id, comprobando antes que no exista
    public static int generarIdRandom() {
        int id;

        Random random = new Random();

        // genera un nuevo numero si este ya existe en los id ocupados
        do {
            id = random.nextInt(0, 1000000);
        } while(AdministradorCuentas.identificadoresOcupados.contains(id));

        return id;
    }
}
