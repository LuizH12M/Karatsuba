import java.math.BigInteger;

public class Calc {

    public static void main (String [] args) {

        BigInteger n1 = new BigInteger("9351165441299297656676479974612556133585061842871649783242236086"); 
        BigInteger n2 = new BigInteger("708751921474615196842932891953949029794027540569417106170049764984403");

        BigInteger resultado = n1.multiply(n2);

        System.out.println("RESULTADO: " + resultado);
    }
}