package com.nuup.kafkademo.productors;
import org.springframework.stereotype.Component;

@Component
public class StockPriceChangeGenerator {
    public String generateStockPriceChangeMessage(Double messageId) {
        // Aquí puedes implementar la lógica para generar un mensaje de cambio de precio de una acción de bolsa
        // Puedes incluir detalles como el nombre de la acción, el precio anterior y el nuevo precio, etc.
        return "valor de accion" + messageId;
    }
}
