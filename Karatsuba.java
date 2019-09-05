/*
Luiz Henrique Mosmann
Trabalho 1 - Karatsuba com Strings
Projeto e Otimização de Algoritmos
*/

public class Karatsuba {
    static String n1 = ""; 
    static String n2 = "";

    public static void main(String[] args) 
    { 
        // Pego os números do terminal
        n1 = args[0];
        n2 = args[1];

        preencheZeros();
        System.out.println(removeZeros(karatsuba(n1, n2)));
    }

        public static String karatsuba (String num1, String num2) {
            int tamanho = num1.length();
            
            if(num2.length() > tamanho) 
                tamanho = num2.length();
            
            int metade = tamanho/2;

            if (tamanho <= 2) {
                int resposta = Integer.parseInt(num1) * Integer.parseInt(num2);
                return String.valueOf(resposta);
            }

            num1 = completaZeros(num1, tamanho);
            num2 = completaZeros(num2, tamanho);

            int aux = tamanho;
            int aux2 = tamanho / 2;
            
            if (tamanho % 2 != 0) {
                aux++;
                aux2++;
            }
            
            String a = num1.substring(0, metade);
            String b = num1.substring(metade, tamanho);
            String c = num2.substring(0, metade);
            String d = num2.substring(metade, tamanho);
            

            String ac = karatsuba(a, c);
            String bd = karatsuba(b, d);
            String ad = subtracao(karatsuba(soma(a, b), soma(c, d)), soma(ac, bd));

            return soma(soma(shift(ac, aux), shift(ad, aux2)), bd);        
        }
        
        public static String soma(String n1, String n2){
            int tam = n1.length();
            if(n2.length() > tam) 
                tam = n2.length();
            n1 = completaZeros(n1, tam);
            n2 = completaZeros(n2, tam);
            
            String resultado = "";
            int carry = 0;
            
            for(int i = tam-1; i >= 0; i--){
                int aux = n1.charAt(i) + n2.charAt(i) - (48*2);
                if(carry == 1){
                    aux++;
                    carry = 0;
                }
                if(aux >= 10){
                    carry = 1;
                    aux -= 10; 
                }else{
                    carry = 0;
                }
                
                resultado = aux + resultado;
                
            }
            if(carry == 1) resultado = "1" + resultado;
            
            return resultado;
        }

        public static String completaZeros(String num, int qtd){
            while(num.length() < qtd) 
                num = "0" + num;
            return num;
        }

        public static String subtracao (String num1, String num2) {
            int n = num1.length();

            if(num2.length() > n) 
                n = num2.length();
            num1 = completaZeros(num1, n);
            num2 = completaZeros(num2, n);
                        
            String resultado = "";
            int carry = 0;
            
            for (int i = n-1; i >= 0; i--) {
                int aux2 = num1.charAt(i) - num2.charAt(i);
                if(carry == 1){
                    aux2--;
                    carry = 0;
                }
                if (aux2 < 0) {
                    carry = 1;
                    aux2 += 10;

                } else {
                    carry = 0;
                }                
                resultado = aux2 + resultado;
            }
            return resultado;
        }

        public static String shift (String num, int qtd) {
            // Shift do numero para a esquerda com a qtd indicada
            int i = 0;
            while (qtd > i) {
                num = num + "0"; 
                i++;
            }
            return num;
        }


        public static void preencheZeros() {
            while (n1.length() > n2.length()) { n2 = "0" + n2; }
            while (n2.length() > n1.length()) { n1 = "0" + n1; }
        }
        
        public static String removeZeros (String num) { 
            // Vai remover os 0's a esquerda do n°
            int aux = 0;
            if (num.length() == 1 && num.equals("0"))
                return "0";
            for(int i = 0; i < num.length(); i++){
                if(num.charAt(i) != '0') 
                    break;
                aux++;
            }
            return num.substring(aux, num.length());
        }

}
