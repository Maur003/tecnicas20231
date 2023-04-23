package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.model.Cliente;
import co.edu.usbcali.bancaweb.model.TipoDocumento;
import co.edu.usbcali.bancaweb.repository.ClienteRepository;
import co.edu.usbcali.bancaweb.repository.TipoDocumentoRepository;
import co.edu.usbcali.bancaweb.service.impl.ClienteServiceImpl;
import co.edu.usbcali.bancaweb.utility.ClienteUtilTest;
import co.edu.usbcali.bancaweb.utility.TipoDocumentoUtilTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Mock
    ClienteRepository clienteRepository;


    @Test
    void buscarPorId() throws Exception {
        tipoDocumentoRepository.save(TipoDocumentoUtilTest.TIPO_DOCUMENTO_CEDULA);
        clienteRepository.save(ClienteUtilTest.CLIENTE_UNO);

        // Mock de la respuesta del repositorio
        given(clienteRepository.existsById(ClienteUtilTest.ID_UNO)).willReturn(true);
        given(clienteRepository.getReferenceById(ClienteUtilTest.ID_UNO)).willReturn(ClienteUtilTest.CLIENTE_UNO);

        // Llamado al método a probar
        ClienteDTO clienteDTO = clienteService.buscarPorId(ClienteUtilTest.ID_UNO);

        // Verificación del resultado esperado
        assertEquals(clienteDTO.getId(), ClienteUtilTest.ID_UNO);
        assertEquals(clienteDTO.getNombre(), ClienteUtilTest.NOMBRE_UNO);
    }


    @Test
    void buscarTodos() {
        // Mock de la respuesta del repositorio
        given(clienteRepository.findAll()).willReturn(ClienteUtilTest.CLIENTES);

        List<ClienteDTO> clientes = clienteService.buscarTodos();

        assertEquals(2, clientes.size());
        assertEquals(ClienteUtilTest.NOMBRE_DOS, clientes.get(1).getNombre());

    }

    @Test
    void crearNuevoCliente() throws Exception {
        given(tipoDocumentoRepository.existsById(TipoDocumentoUtilTest.CODIGO_UNO)).willReturn(true);
        given(tipoDocumentoRepository.getReferenceById(TipoDocumentoUtilTest.CODIGO_UNO)).willReturn(TipoDocumentoUtilTest.TIPO_DOCUMENTO_CEDULA);
        given(clienteRepository.save(ClienteUtilTest.CLIENTE_UNO)).willReturn(ClienteUtilTest.CLIENTE_UNO);
        ClienteDTO clienteGuardado = clienteService.crearNuevoCliente(ClienteUtilTest.CLIENTEDTO_UNO);

        assertEquals(ClienteUtilTest.ID_UNO, clienteGuardado.getId());
        assertEquals(ClienteUtilTest.MAIL_UNO, clienteGuardado.getMail());

    }



}
