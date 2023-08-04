import cuentas.CuentaCorriente;


import static cuentas.AdministradorCuentas.programaUsuario;

public class Main {
    public static void main(String[] args) {
        CuentaCorriente cuentaCorrienteDanielIgnacio = new CuentaCorriente("Daniel Ignacio");

        // Probar mas de 10 movimientos
        cuentaCorrienteDanielIgnacio.abonar( 150000);   // +150.000     => 150.000

        // aquí se comprueba que no baja de 0
        // a su vez el movimiento queda registrado como -150.000 ya que el
        // usuario no tiene saldo suficiente para realizar el cargo de 200.000
        cuentaCorrienteDanielIgnacio.cargar(200000);    // -200.000     => 0

        cuentaCorrienteDanielIgnacio.abonar(1000000);   // +1.000.000   => 1.000.000
        cuentaCorrienteDanielIgnacio.abonar( 150000);   // +150.000     => 1.150.000
        cuentaCorrienteDanielIgnacio.cargar(200000);    // -200.000     => 950.000

        // últimos 10 movimientos reflejados en el print
        cuentaCorrienteDanielIgnacio.cargar(100000);    // -100.000     => 850.000
        cuentaCorrienteDanielIgnacio.abonar( 500000);   // +500.000     => 1.350.000
        cuentaCorrienteDanielIgnacio.abonar(59990);     // +59.990      => 1.409.990
        cuentaCorrienteDanielIgnacio.cargar(100000);    // -100.000     => 1.309.990
        cuentaCorrienteDanielIgnacio.cargar(2000);      // -2.000       => 1.307.990
        cuentaCorrienteDanielIgnacio.abonar(159990);    // +159.990     => 1.467.980
        cuentaCorrienteDanielIgnacio.abonar( 399000);   // +399.000     => 1.866.980
        cuentaCorrienteDanielIgnacio.cargar(200000);    // -200.000     => 1.666.980
        cuentaCorrienteDanielIgnacio.cargar(100000);    // -100.000     => 1.566.980
        cuentaCorrienteDanielIgnacio.abonar(700000);    // +700.000     => 2.266.980

        CuentaCorriente cuentaCorrienteRandom = new CuentaCorriente("Random Person");

        // Probar funciones movimientos
        cuentaCorrienteRandom.abonar( 89000);   // +89.000
        cuentaCorrienteRandom.cargar(15000);    // -15.000
        cuentaCorrienteRandom.abonar(30000);    // +30.990
        cuentaCorrienteRandom.abonar(59990);    // +59.990
        cuentaCorrienteRandom.cargar(160000);   // -160.000

        // creamos un mini programa para probar funcionalidades
        programaUsuario();
    }
}
