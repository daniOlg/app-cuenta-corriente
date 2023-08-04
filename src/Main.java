import cuentas.AdministradorCuentas;
import cuentas.CuentaCorriente;

import java.util.Scanner;

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

        // creamos un mini programa para probar funcionalidades (sin codigo muy legible solo experimental)
        miniPrograma();
    }

    public static void miniPrograma() {
        final int LISTAR_CUENTAS = 1, VER_DETALLE = 2, CREAR_CUENTA = 3, ABONAR = 4, CARGAR = 5, SALIR = 6;

        // creamos un loop infinito
        while(true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Que desea hacer?");
            System.out.println("1. Listar cuentas existentes");
            System.out.println("2. Ver detalle de una cuenta (codigo necesario)");
            System.out.println("3. Crear una cuenta");
            System.out.println("4. Abonar (codigo necesario)");
            System.out.println("5. Cargar (codigo necesario)");
            System.out.println("6. Salir");
            System.out.println();
            System.out.print("Seleccion: ");

            Scanner scanner = new Scanner(System.in);
            int seleccionMenu = scanner.nextInt();

            // menu handling
            if(seleccionMenu == LISTAR_CUENTAS) {
                System.out.println("--------------------------------------------------------");
                System.out.println("Cuentas existentes:");
                for(CuentaCorriente cuenta : AdministradorCuentas.cuentas) {
                    System.out.println("- " + cuenta.datosBasicosToString());
                }
            } else if(seleccionMenu == VER_DETALLE) {
                System.out.print("Ingrese el numero de la cuenta: ");
                int numero = scanner.nextInt();
                for(CuentaCorriente cuenta : AdministradorCuentas.cuentas) {
                    if(cuenta.getNumero() == numero) {
                        System.out.println(cuenta);
                    }
                }
            } else if(seleccionMenu == CREAR_CUENTA) {
                System.out.print("Ingrese el nombre del titular: ");
                scanner.nextLine();
                String titular = scanner.nextLine();
                CuentaCorriente nuevaCuenta = new CuentaCorriente(titular);
                System.out.println("Cuenta creada con los siguientes datos:");
                System.out.println(nuevaCuenta);
            } else if(seleccionMenu == ABONAR) {
                System.out.print("Ingrese el numero de la cuenta: ");
                int numero = scanner.nextInt();
                CuentaCorriente seleccionada = null;
                for(CuentaCorriente cuenta : AdministradorCuentas.cuentas) {
                    if(cuenta.getNumero() == numero) {
                        seleccionada = cuenta;
                    }
                }

                if(seleccionada != null) {
                    System.out.println("Se ha encontrado la cuenta, el saldo actual es de: $" + seleccionada.getSaldo());
                    System.out.print("Cuanto desea abonar?: ");
                    float cantidad = scanner.nextFloat();
                    seleccionada.abonar(cantidad);
                    System.out.println("El nuevo saldo es de: " + seleccionada.getSaldo());
                } else {
                    System.out.println("No se ha encontrado la cuenta");
                }
            } else if(seleccionMenu == CARGAR) {
                System.out.print("Ingrese el numero de la cuenta: ");
                int numero = scanner.nextInt();
                CuentaCorriente seleccionada = null;
                for(CuentaCorriente cuenta : AdministradorCuentas.cuentas) {
                    if(cuenta.getNumero() == numero) {
                        seleccionada = cuenta;
                    }
                }

                if(seleccionada != null) {
                    System.out.println("Se ha encontrado la cuenta, el saldo actual es de: $" + seleccionada.getSaldo());
                    System.out.print("Cuanto desea cargar?: ");
                    float cantidad = scanner.nextFloat();
                    seleccionada.cargar(cantidad);
                    System.out.println("El nuevo saldo es de: " + seleccionada.getSaldo());
                } else {
                    System.out.println("No se ha encontrado la cuenta");
                }
            } else if(seleccionMenu == SALIR) {
                System.exit(0);
            } else {
                System.out.println("El numero ingresado no es valido, intente nuevamente.\n");
            }
        }
    }
}
