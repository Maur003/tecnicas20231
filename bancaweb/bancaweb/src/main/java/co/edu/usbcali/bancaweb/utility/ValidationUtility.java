package co.edu.usbcali.bancaweb.utility;

import org.springframework.util.StringUtils;

public class ValidationUtility {

    public static void isNull(Object valor, String mensaje) throws Exception {
        if(valor == null) {
            throw new Exception(mensaje);
        }
    }

    public static void isNullOrBlank(Object valor, String mensaje) throws Exception {
        isNull(valor, mensaje);
        if(valor.toString().isBlank()) {
            throw new Exception(mensaje);
        }
    }
}
