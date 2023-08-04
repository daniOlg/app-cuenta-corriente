package cuentas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class AdministradorCuentas {
    public static ArrayList<CuentaCorriente> cuentas = new ArrayList<>();
    public static HashSet<Integer> identificadoresOcupados = new HashSet<>();

    // opciones posibles para el usuario
    private static final int LISTAR_CUENTAS = 1, VER_DETALLE = 2, CREAR_CUENTA = 3, ABONAR = 4, CARGAR = 5, SALIR = 6;

    // inicializar scanner
    private static final Scanner scanner = new Scanner(System.in);

    public static void programaUsuario() {

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
            int seleccion = scanner.nextInt();


            // selección con switch
            switch (seleccion) {
                case LISTAR_CUENTAS -> listarCuentas();
                case VER_DETALLE    -> verDetalle();
                case CREAR_CUENTA   -> crearCuenta();
                case ABONAR         -> movimiento(ABONAR);
                case CARGAR         -> movimiento(CARGAR);
                case SALIR          -> salir();
                default -> System.out.println("El numero ingresado no es valido, intente nuevamente.\n");
            }
        }
    }

    private static void listarCuentas() {
        // decoracion
        System.out.println("--------------------------------------------------------");
        System.out.println("Cuentas existentes:");

        // recorrer array y mostrar datos basicos
        for(CuentaCorriente cuenta : AdministradorCuentas.cuentas) {
            System.out.println("- " + cuenta.datosBasicosToString());
        }
    }

    private static void verDetalle() {
        // solicita el numero de cuenta
        System.out.print("Ingrese el numero de la cuenta: ");
        int numero = scanner.nextInt();

        // busca la cuenta
        for(CuentaCorriente cuenta : AdministradorCuentas.cuentas) {
            if(cuenta.getNumero() == numero) {
                System.out.println(cuenta);
            }
        }
    }

    private static void crearCuenta() {
        // solicita nombre del titular
        System.out.print("Ingrese el nombre del titular: ");
        scanner.nextLine();
        String titular = scanner.nextLine();

        // crea una cuenta
        CuentaCorriente nuevaCuenta = new CuentaCorriente(titular);

        // imprime los datos de la nueva cuenta
        System.out.println("Cuenta creada con los siguientes datos:");
        System.out.println(nuevaCuenta);
    }

    private static void movimiento(int tipoMovimiento) {
        System.out.print("Ingrese el numero de la cuenta: ");
        int numero = scanner.nextInt();

        // busca la cuenta en el array de cuentas
        CuentaCorriente seleccionada = null;
        for(CuentaCorriente cuenta : AdministradorCuentas.cuentas)
            if(cuenta.getNumero() == numero) seleccionada = cuenta;

        // si no se encuentra la cuenta da feedback al usuario y retorna para no seguir ejecutando la función
        if(seleccionada == null) {
            System.out.println("No se ha encontrado la cuenta");
            return;
        }

        // si se encuentra la cuenta se muestra el saldo actual
        System.out.println("Se ha encontrado la cuenta, el saldo actual es de: $" + seleccionada.getSaldo());

        // imprime lo que el usuario debe ingresar dependiendo de la acción
        if(tipoMovimiento == ABONAR)
            System.out.print("Cuanto desea abonar?: ");
        else
            System.out.print("Cuanto desea cargar?: ");

        // lee la cantidad que el usuario ingresa
        float cantidad = scanner.nextFloat();

        // ejecuta la acción que el usuario desea
        if(tipoMovimiento == ABONAR)
            seleccionada.abonar(cantidad);
        else
            seleccionada.cargar(cantidad);

        // se imprime el nuevo saldo de la cuenta
        System.out.println("El nuevo saldo es de: " + seleccionada.getSaldo());
    }

    private static void salir() {
        System.exit(0);
    }
}
