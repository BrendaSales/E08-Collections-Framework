public class ContaUniversitaria extends Conta{
    public void setLimite(int limite) {
        if (limite < 0) {
            System.out.println("Limite inferior ao limite minimo dessa modalidade de conta. Limite minimo = 0 reais.");
        } else if (limite > 500) {
            System.out.println("Limite superior ao limite maximo dessa modalidade de conta. Limite maximo = 500 reais.");
        }else{
            this.limite = limite;
        }
    }

    public double calculaTaxas(){
        return 0;
    }
}