import cuentas.CuentaCorriente;

public class Main {
    public static void main(String[] args) {
        CuentaCorriente cuentaCorriente = new CuentaCorriente("Daniel Ignacio", 1213);

        // Probar funciones saldo
        cuentaCorriente.setSaldo(100000); // +100.000
        cuentaCorriente.verSaldo(); // Expect: 100.000

        cuentaCorriente.abonar( 150000); // +150.000
        cuentaCorriente.verSaldo(); // Expect: 250.000

        cuentaCorriente.cargar(200000); // -200.000
        cuentaCorriente.verSaldo(); // Expect: 50.000

        cuentaCorriente.cargar(100000); // -100.000 Probar si con negativo queda en 0
        cuentaCorriente.verSaldo(); // Expect: 0

        // Línea para separar
        System.out.println("------------------------------------------------");

        // Probar función toString, antes vamos a ponerle un poco de saldo
        cuentaCorriente.abonar(59999);

        // No ponemos la función toString como tal ya que sería redundante
        System.out.println(cuentaCorriente);
        /* Expect:
            Titular: Daniel Ignacio
            Número: 1213
            Saldo: 0
         */

    }
}
