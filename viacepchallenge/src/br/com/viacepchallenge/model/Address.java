package br.com.viacepchallenge.model;

public record Address(String logradouro, String bairro, String localidade, String uf, String cep) {
    @Override
    public String toString() {
        return """
                Street: %s
                District: %s
                City: %s
                State: %s
                ZIP Code: %s""".formatted(logradouro, bairro, localidade, uf, cep);
    }
}
