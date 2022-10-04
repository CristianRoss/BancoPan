package br.fiap;

import br.fiap.Cliente.Cliente;
import br.fiap.Cliente.ClienteFisico;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.DAO.LimpezaDados.LDDAO;
import br.fiap.DAO.Servicos.Contas.ContasDAO;
import br.fiap.Servicos.Contas.ContaCorrente;

import java.sql.Date;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Map<String, Cliente> lista=new LDDAO().getClientes("SQL_TESTE");

        for (Map.Entry<String,Cliente> clientes:lista.entrySet()) {
            System.out.println(clientes.getKey());
            System.out.println("\n"+clientes.getValue());
        }


    }

}
