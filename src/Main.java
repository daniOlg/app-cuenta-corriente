import admin.AdministradorCuentas;
import cuentas.CuentaCorriente;
import static admin.AdministradorCuentas.programaUsuario;

public class Main {
    public static void main(String[] args) {
        // Generar datos de prueba
        CuentaCorriente cuentaCorrienteDaniel = new CuentaCorriente("Daniel");

        // Probar más de 10 movimientos
        cuentaCorrienteDaniel.abonar( 150000);   // +150.000     => 150.000

        // aquí se comprueba que no baja de 0
        // a su vez el movimiento queda registrado como -150.000 ya que el
        // usuario no tiene saldo suficiente para realizar el cargo de 200.000
        cuentaCorrienteDaniel.cargar(200000);    // -200.000     => 0

        cuentaCorrienteDaniel.abonar(1000000);   // +1.000.000   => 1.000.000
        cuentaCorrienteDaniel.abonar( 150000);   // +150.000     => 1.150.000
        cuentaCorrienteDaniel.cargar(200000);    // -200.000     => 950.000

        // últimos 10 movimientos reflejados en el print
        cuentaCorrienteDaniel.cargar(100000);    // -100.000     => 850.000
        cuentaCorrienteDaniel.abonar( 500000);   // +500.000     => 1.350.000
        cuentaCorrienteDaniel.abonar(59990);     // +59.990      => 1.409.990
        cuentaCorrienteDaniel.cargar(100000);    // -100.000     => 1.309.990
        cuentaCorrienteDaniel.cargar(2000);      // -2.000       => 1.307.990
        cuentaCorrienteDaniel.abonar(159990);    // +159.990     => 1.467.980
        cuentaCorrienteDaniel.abonar( 399000);   // +399.000     => 1.866.980
        cuentaCorrienteDaniel.cargar(200000);    // -200.000     => 1.666.980
        cuentaCorrienteDaniel.cargar(100000);    // -100.000     => 1.566.980
        cuentaCorrienteDaniel.abonar(700000);    // +700.000     => 2.266.980

        CuentaCorriente cuentaCorrienteRandom = new CuentaCorriente("Random Person");

        // Probar funciones movimientos
        cuentaCorrienteRandom.abonar( 89000);   // +89.000
        cuentaCorrienteRandom.cargar(15000);    // -15.000
        cuentaCorrienteRandom.abonar(30000);    // +30.990
        cuentaCorrienteRandom.abonar(59990);    // +59.990
        cuentaCorrienteRandom.cargar(160000);   // -160.000


        // creamos un programa para probar funcionalidades => en esta función se usan try/catch
        programaUsuario();
    }
}
