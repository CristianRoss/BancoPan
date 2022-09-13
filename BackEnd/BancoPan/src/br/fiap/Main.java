package br.fiap;

import br.fiap.Clientes.ContaFisica;
import br.fiap.DAO.ContaFisicaDAO;

import java.sql.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ContaFisicaDAO dao=new ContaFisicaDAO();

        ContaFisica conta=dao.pesquisarConta(1);
        System.out.println(conta);

        ContaFisica c=new ContaFisica(3, "Rafael","Masculino",9999,"ra@gmail.com","Rua Bernardo",1477,350.3,new Date(2019,6,13),1,3,"Daciolo","555");
        System.out.println(c);
        dao.inserirContaFisica(c);
    }

}
