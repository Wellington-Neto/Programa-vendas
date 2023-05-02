package io.github.wellingtonneto.clientes.util;

import java.math.BigDecimal;

public class BigDecimalConverter {
    public BigDecimal converter(String valor){
        if(valor == null){
            return null;
        }

        valor = valor.replace(".", "").replace(",", ".");
        return new BigDecimal(valor);
    }
}
