package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.CuentaDTO;
import co.edu.usbcali.bancaweb.repository.ClienteRepository;
import co.edu.usbcali.bancaweb.repository.CuentaRepository;
import co.edu.usbcali.bancaweb.service.impl.CuentaServiceImpl;
import co.edu.usbcali.bancaweb.utility.ClienteUtilTest;
import co.edu.usbcali.bancaweb.utility.CuentaUtilTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class CuentaServiceImplTest {

    @InjectMocks
    private CuentaServiceImpl cuentaService;

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    ClienteRepository clienteRepository;

    @Test
    void crearNuevaCuenta() throws Exception {
        given(clienteRepository.existsById(ClienteUtilTest.ID_UNO)).willReturn(true);
        given(clienteRepository.getReferenceById(ClienteUtilTest.ID_UNO)).willReturn(ClienteUtilTest.CLIENTE_UNO);
        given(cuentaRepository.save(CuentaUtilTest.CUENTA_UNO)).willReturn(CuentaUtilTest.CUENTA_UNO);
        CuentaDTO cuentaGuardada = cuentaService.crearNuevaCuenta(CuentaUtilTest.CUENTA_DTO);

        assertEquals(CuentaUtilTest.NUMERO_UNO, cuentaGuardada.getNumero());
        assertEquals(CuentaUtilTest.SALDO, cuentaGuardada.getSaldo());
    }

    @Test
    void modificarCuenta() throws Exception {
        given(clienteRepository.existsById(ClienteUtilTest.ID_DOS)).willReturn(true);
        given(cuentaRepository.existsById(CuentaUtilTest.NUMERO_DOS)).willReturn(true);
        given(clienteRepository.getReferenceById(ClienteUtilTest.ID_DOS)).willReturn(ClienteUtilTest.CLIENTE_DOS);
        given(cuentaRepository.existsCuentaByNumeroAndClienteId(CuentaUtilTest.NUMERO_DOS, ClienteUtilTest.ID_DOS))
                .willReturn(true);
        given(cuentaRepository.getReferenceById(CuentaUtilTest.NUMERO_DOS)).willReturn(CuentaUtilTest.CUENTA_DOS);
        given(cuentaRepository.save(CuentaUtilTest.CUENTA_DOS)).willReturn(CuentaUtilTest.CUENTA_DOS);
        CuentaDTO cuentaModificada = cuentaService.modificarCuenta(CuentaUtilTest.CUENTA_DTO_MODIFICAR);

        assertEquals(CuentaUtilTest.NUMERO_DOS, cuentaModificada.getNumero());
        assertEquals(CuentaUtilTest.SALDO, cuentaModificada.getSaldo());
    }

    @Test
    void modificarCuenta_negativa() {
        Exception exception = assertThrows(Exception.class, () ->
                cuentaService.modificarCuenta(CuentaUtilTest.CUENTA_DTO_MODIFICAR));

        String expectedMessage = "No existe la cuenta con número ".concat(CuentaUtilTest.NUMERO_DOS);
        assertEquals(expectedMessage, exception.getMessage());
    }



}
