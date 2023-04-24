package co.edu.usbcali.bancaweb.utility;

import io.micrometer.common.util.StringUtils;

import java.util.regex.Pattern;

public class ValidationUtility {

    public static void isNull(Object valor, String mensaje) throws Exception {
        if(valor == null) {
            throw new Exception(mensaje);
        }
    }

    public static void stringIsNullOrBlank(String valor, String mensaje) throws Exception {
        isNull(valor, mensaje);
        if(valor.isBlank()) {
            throw new Exception(mensaje);
        }
    }

    public static void integerIsNullOrZero(Integer valor, String mensaje) throws Exception {
        isNull(valor, mensaje);
        if(valor.compareTo(0) == 0) {
            throw new Exception(mensaje);
        }
    }

    public static void stringMailValidatePattern(String valor, String mensaje) throws Exception {
        stringIsNullOrBlank(valor, mensaje);
        if (!Pattern.matches(ConstantesUtility.PATTERN_MAIL_REGEX, valor)) {
            throw new Exception(mensaje);
        }
    }

    public static void integerIsNullOrLessZero(Integer valor, String mensaje) throws Exception {
        isNull(valor, mensaje);
        if(valor.compareTo(0) <= 0) {
            throw new Exception(mensaje);
        }
    }
}
