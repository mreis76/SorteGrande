/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sorteGrande;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.jws.WebService;

@WebService(serviceName = "WS_SorteGrande")
public class WS_SorteGrande {

    private Conexao con;

    private boolean existeElemento(int v[], int elemento) {
        for (int i = 0; i < v.length; i++) {
            if (v[i] == elemento) {
                return true;
            }
        }
        return false;
    }

    public String[] getResultado(String tipo, int quantos) {
        String[] retorno = new String[quantos];
        try {

            int count = 0;
            con.iniciarConexao();
            Statement stm = con.getConnection().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM resultados WHERE TIPO = " + tipo + " ORDER BY id desc");

            while (rs.next()) {
                if (count < quantos) {
                    if (tipo.equals("M")) {
                        retorno[count] = String.format("%d, %d, %d, %d, %d, %d",
                                rs.getInt("num1"), rs.getInt("num2"),
                                rs.getInt("num3"), rs.getInt("num4"),
                                rs.getInt("num5"), rs.getInt("num6"));
                    } else if (tipo.equals("Q")) {
                        retorno[count] = String.format("%d, %d, %d, %d, %d",
                                rs.getInt("num1"), rs.getInt("num2"),
                                rs.getInt("num3"), rs.getInt("num4"),
                                rs.getInt("num5"));
                    }
                    count++;

                } else {
                    break;
                }
            }
            con.fecharConexao();
        } catch (SQLException ex) {
            return null;
        }
        return retorno;
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
