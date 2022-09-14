package br.fiap;

import br.fiap.Clientes.ContaFisica;
import br.fiap.DAO.ClientesDAO;
import br.fiap.DAO.ContaFisicaDAO;
import br.fiap.DAO.LimparDados;
import br.fiap.DAO.LimparDadosDAO;

import java.sql.Date;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ContaFisicaDAO dao=new ContaFisicaDAO();
//
//        ContaFisica conta=dao.pesquisarConta(1);
//        System.out.println(conta);
//
        ContaFisica c=new ContaFisica(1, "Ana","Feminino",9999,"ana@gmail.com","Rua Bernardo",1,350.3,new Date(2012,9,15),1,1,"Silva","777");
//        System.out.println(c);
//        dao.inserirContaFisica(c);

//        TreeMap lista=new ClientesDAO().listarContas();
//
//        Set keys = lista.keySet();
//        for (Iterator i = keys.iterator(); i.hasNext();) {
//            Integer key = (Integer) i.next();
//            ContaFisica value = (ContaFisica) lista.get(key);
//            System.out.println(key + " = " + value);
//        }


    }

}
