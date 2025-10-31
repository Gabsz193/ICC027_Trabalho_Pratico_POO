package br.edu.ufam.icomp.ru_digital.shared.exceptions;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String message) {
        super(message);
    }

    public SaldoInsuficienteException(Long saldoAtual, Long valorNecessario) {
        super(String.format("Saldo insuficiente. Saldo atual: R$ %.2f, Valor necessário: R$ %.2f",
                saldoAtual / 100.0, valorNecessario / 100.0));
    }
}
