package cuentas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

public class CuentaCorriente {
    public static ArrayList<CuentaCorriente> cuentas = new ArrayList<>();
    public static HashSet<Integer> identificadoresOcupados = new HashSet<>();

    private String titular;
    private ArrayList<Movimiento> movimientos = new ArrayList<>(10);
    private int numero;
    private float saldo;


    // imprimir una cuenta
    @Override
    public String toString() {
        String movimientosString = "\n";

        // si es un movimiento (último movimiento) o si son mas (últimos x movimientos)
        if (movimientos.size() == 0) {
            movimientosString += "Aun no hay movimientos";
        } else if (movimientos.size() == 1)
            movimientosString += "Ultimo movimiento:";
        else
            movimientosString += "Ultimos " + movimientos.size() + " movimientos:";

        // añadir los movimientos al string final
        for(Movimiento movimiento : movimientos)
            movimientosString +=  "\n" + movimiento.toString();

        String separador = "--------------------------------------------------------";
        return separador + "\nTitular: " + this.titular + "\nNúmero: " + this.numero + "\nSaldo: $" + this.saldo + movimientosString;
    }

    public String datosBasicos() {
        return "Titular: " + this.titular + ", Número: " + this.numero;
    }

    // abonar y agregar a los movimientos
    public void abonar(float cantidad) {
        if(cantidad < 0) return;
        this.saldo += cantidad;

        addMovimiento(TipoMovimiento.ABONO, cantidad);
    }

    // cargar y agregar a los movimientos
    public void cargar(float cantidad) {
        this.saldo -= cantidad;

        if(this.saldo < 0) {
            cantidad += saldo; // calcular monto exacto que se carga de la cuenta, no puede extraer mas del saldo
            this.saldo = 0;
        }

        // Añadir cargo al array de movimientos
        addMovimiento(TipoMovimiento.CARGO, cantidad);
    }


    // añadir movimiento a lista de movimientos
    public void addMovimiento(TipoMovimiento tipo, float cantidad) {
        // comprueba si existen 10 movimientos, si existen elimina el primero añadido
        if(movimientos.size() >= 10) {
            movimientos.remove(0);
        }

        this.movimientos.add(new Movimiento(tipo, new Date(), cantidad));
    }

    // constructores (se borró el otro y se reemplazó por un generador de número aleatorio y saldo en cero)
    public CuentaCorriente(String titular) {
        this.titular = titular;

        // cuando se abre una cuenta corriente el saldo inicia en cero
        this.saldo = 0;

        // genera un id random y lo asigna a numero
        this.numero = generarIdRandom();

        // añade el id de esta cuenta corriente a los id ocupados
        CuentaCorriente.identificadoresOcupados.add(this.numero);

        // añadir la cuenta al listado de cuentas
        cuentas.add(this);
    }

    // generar nuevo id, comprobando antes que no exista
    public int generarIdRandom() {
        int id;

        Random random = new Random();

        // genera un nuevo numero si este ya existe en los id ocupados
        do {
            id = random.nextInt(0, 1000000);
        } while(CuentaCorriente.identificadoresOcupados.contains(id));

        return id;
    }

    // getters & setters
    public String getTitular() {
        return titular;
    }
    public int getNumero() {
        return numero;
    }
    public float getSaldo() {
        return saldo;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
}
