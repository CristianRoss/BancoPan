package br.fiap.DAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LimparDados {

    public static void salvarARQ(List<String> lista) {

        try {
            FileWriter writer = new FileWriter("tabelasuja.txt");
            for(String str: lista) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
        }catch (IOException e) {
            System.out.println("erro ao escrever o aquivo: "+e);
        }


    }

}
