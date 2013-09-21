/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sorteGrande;

import java.util.Arrays;
import javax.jws.WebService;

@WebService(serviceName = "WS_SorteGrande")
public class WS_SorteGrande {

    private boolean existeElemento(int v[], int elemento) {
        for (int i = 0; i < v.length; i++) {
            if (v[i] == elemento) {
                return true;
            }
        }
        return false;
    }

    public String quina(int qtNumeros) {
        int v[] = new int[qtNumeros];
        int valorMax = 80, valorSorteado, i = 0;
        String resp = "";
        if (!(qtNumeros == 5 || qtNumeros == 6 || qtNumeros == 7)) {
            return "Erro, na quina deve-se marcar 5, 6 ou 7 numeros "
                    + "(sao 80 disponiveis).";
        }
        do {
            valorSorteado = 1 + (int) (Math.random() * valorMax);
            if (!existeElemento(v, valorSorteado)) {
                v[i] = valorSorteado;
                i++;
            }
        } while (i < qtNumeros);
        Arrays.sort(v);
        for (i = 0; i < qtNumeros; i++) {
            resp += v[i] + " ";
        }
        return "Os numeros da sorte sao: " + resp;
    }

    public String megaSena(int qtNumeros) {
        int v[] = new int[qtNumeros];
        int valorMax = 60, valorSorteado, i = 0;
        String resp = "";
        if (qtNumeros < 6) {
            return "Erro, na mega sena deve-se marcar de 6 a 15 numeros "
                    + " (sao 60 disponiveis).";
        }
        if (qtNumeros > 15) {
            return "Erro, quantidade de numeros marcados deve ser inferior a 15";
        }
        do {
            valorSorteado = 1 + (int) (Math.random() * valorMax);
            if (!existeElemento(v, valorSorteado)) {
                v[i] = valorSorteado;
                i++;
            }
        } while (i < qtNumeros);
        Arrays.sort(v);
        for (i = 0; i < qtNumeros; i++) {
            resp += v[i] + " ";
        }
        return "****** Os numeros da sorte sao: " + resp;
    }
}
