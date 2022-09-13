package br.fiap.Servicos;

public abstract class Servicos {

    protected int id_conta_fisica;
    protected int id_conta_jur;

    public int getIdFisica() {
        return id_conta_fisica;
    }

    public void setIdFisica(int id_conta_fisica) {
        this.id_conta_fisica = id_conta_fisica;
    }

    public int getIdJur() {
        return id_conta_jur;
    }

    public void setIdJur(int id_conta_jur) {
        this.id_conta_jur = id_conta_jur;
    }

    @Override
    public String toString(){
        String aux="";
        return aux;
    }

}
