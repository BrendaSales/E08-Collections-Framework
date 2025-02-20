import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Collection;

public abstract class Conta implements ITaxas {
    private int numeroDaConta;
    private Cliente dono;
    private double saldo;
    protected double limite;
    static int totalDeContas = 0;
    private List<Operacao> operacoes;

//    construtores

    public Conta (){
        totalDeContas++;
        this.operacoes = new ArrayList<>();
    }

//     getters

    public int getNumeroDaConta(){
        return this.numeroDaConta;
    }

    public Cliente getDono(){
        return this.dono;
    }

    public double getSaldo(){
        return this.saldo;
    }

    public double getLimite(){
        return this.limite;
    }
// setters

    public void setDono(Cliente dono){
        this.dono = dono;
    }

    public void setNumeroDaConta(int numeroDaConta){
        this.numeroDaConta = numeroDaConta;
    }

    public abstract void setLimite(int limite);

    //    Métodos

    public String toString(){
        return String.format("%s \nNumero da conta: %d\nSaldo atual da conta: R$ %f \nLimite: R$ %f", this.dono.toString(), this.numeroDaConta, this.saldo, this.limite);
    }

    boolean sacar(double valor) {
        Operacao opr;
        if (valor <= this.saldo) {
            this.saldo -= valor;
            opr = new OperacaoSaque (valor);
            this.operacoes.add(opr);
            return true;
        } else {
            return false;
        }
    }

    void depositar(double valor) {
        Operacao opr;
        this.saldo += valor;
        opr = new OperacaoDeposito (valor);
        this.operacoes.add(opr);
    }

    boolean transferir(Conta destino, double valor) {
        boolean saqueRealizado;
        saqueRealizado = this.sacar(valor);
        if (saqueRealizado) {
            destino.depositar(valor);
            return true;
        } else {
            return false;
        }
    }

    public boolean equals(Object obj){
        if (obj instanceof Conta){
            Conta that = (Conta) obj;
            return (this.numeroDaConta == that.numeroDaConta);
        }else{
            return false;
        }
    }

    void imprimirExtrato (){

        System.out.println("\t //Extrato de trasaçoes da conta " + getDono().nome + " // \n");
        System.out.println("Por data da transacao:\n");
        for (Operacao atual: this.operacoes){
            System.out.println(atual);
        }
        System.out.println("Por tipo de transacao:\n");

        Collections.sort(operacoes);
        for (Operacao atual: this.operacoes){
            System.out.println(atual);
        }
    }

    void imprimirExtratoTaxas (){
        int i;
        double totalTaxas = 0;

        System.out.println("\t //Extrato de taxas " + getDono().nome + " // \n");
        System.out.println("Manutencao da conta \nR$" + calculaTaxas() + "\n");
        System.out.println("Operacoes");

        for (Operacao operacaoAtual : this.operacoes){
            System.out.println(operacaoAtual.getTipo() + ": " + operacaoAtual.calculaTaxas());
            totalTaxas = totalTaxas + operacaoAtual.calculaTaxas();
        }

        totalTaxas = totalTaxas + calculaTaxas();
        System.out.println("\n");
        System.out.println("Total de taxas: R$ " + totalTaxas);
    }
}