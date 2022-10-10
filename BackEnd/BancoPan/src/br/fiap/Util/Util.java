package br.fiap.Util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Util {
    public LocalDate formatarData(String dataString) {
        final String[] dataAux = dataString.split("-");
        dataString = "";
        dataString = String.valueOf(dataAux[2]) + "/" + dataAux[1] + "/" + dataAux[0];
        final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        final LocalDate data = LocalDate.parse(dataString, formato);
        return data;
    }

    public String criptografar(final String senha) {
        String senhaCriptografada = null;
        try {
            final MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
            final byte[] aux = algoritmo.digest(senha.getBytes("UTF-8"));
            final StringBuilder senhaHex = new StringBuilder();
            byte[] array;
            for (int length = (array = aux).length, i = 0; i < length; ++i) {
                final byte b = array[i];
                senhaHex.append(String.format("%02X", 0xFF & b));
            }
            senhaCriptografada = senhaHex.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return senhaCriptografada;
    }
}