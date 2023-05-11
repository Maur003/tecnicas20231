package co.edu.usbcali.bancaweb.utility;

import co.edu.usbcali.bancaweb.dto.CuentaDTO;
import co.edu.usbcali.bancaweb.model.Cuenta;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CuentaUtilTest {
    public static String NUMERO_UNO = "4008-5305-0010";
    public static String NUMERO_DOS = "4008-5305-0015";
    public static BigDecimal SALDO = BigDecimal.valueOf(100000);
    public static String CLAVE = "1234";
    public static Cuenta CUENTA_UNO = Cuenta.builder()
            .numero(NUMERO_UNO)
            .cliente(ClienteUtilTest.CLIENTE_UNO)
            .saldo(SALDO)
            .clave(CLAVE)
            .activa(ConstantesUtility.ESTADO_CUENTA_ACTIVA)
            .build();
    public static Cuenta CUENTA_DOS = Cuenta.builder()
            .numero(NUMERO_DOS)
            .cliente(ClienteUtilTest.CLIENTE_DOS)
            .saldo(SALDO)
            .clave(CLAVE)
            .activa(ConstantesUtility.ESTADO_CUENTA_ACTIVA)
            .build();

    public static List<Cuenta> CUENTAS = Arrays.asList(CUENTA_UNO, CUENTA_DOS);

    public static CuentaDTO CUENTA_DTO = CuentaDTO.builder()
            .numero(NUMERO_UNO)
            .clienteId(ClienteUtilTest.ID_UNO)
            .saldo(SALDO)
            .clave(CLAVE)
            .build();

    public static CuentaDTO CUENTA_DTO_MODIFICAR = CuentaDTO.builder()
            .numero(NUMERO_DOS)
            .clienteId(ClienteUtilTest.ID_DOS)
            .saldo(SALDO)
            .clave(CLAVE)
            .build();

}
