package br.fiap;

import br.fiap.Cliente.ClienteFisico;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.DAO.Servicos.Contas.ContasDAO;
import br.fiap.Servicos.Contas.ContaCorrente;

import java.sql.Date;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        ClienteFisico roberto = new ClienteFisico(4, "Rafael","sda@gmail.com","Alameda Y","15","Beltrano",new Date(1989,12,12));

        ClienteDAO dao = new ClienteDAO();

        dao.inserirCliente(roberto);
//
//        System.out.println(dao.listarClientes().size());

        ContasDAO cdao= new ContasDAO();

        cdao.inserir(new ContaCorrente(4,3,new Random().nextInt(99999),32123.13,new Date(1989,12,12),
                0.5,"999999"));

        System.out.println(cdao.listarContas(4).size());



    }

}
