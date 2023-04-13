package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.model.Cliente;
import co.edu.usbcali.bancaweb.model.TipoDocumento;
import co.edu.usbcali.bancaweb.repository.ClienteRepository;
import co.edu.usbcali.bancaweb.repository.TipoDocumentoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClienteServiceImplTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private TipoDocumentoRepository tipoDocumentoRepository;

    @MockBean
    ClienteRepository clienteRepository;


    @Test
    void buscarPorId() throws Exception {
        TipoDocumento tipoDocumento = TipoDocumento.builder().codigo(1).nombre("Cédula").build();
        Cliente cliente = Cliente.builder()
                .id(1)
                .tipoDocumento(tipoDocumento)
                .nombre("Prueba")
                .direccion("Calle Carrera")
                .telefono("55555")
                .mail("elmail@mail.com")
                .build();

        // Mock de la respuesta del repositorio
        Mockito.when(clienteRepository.existsById(1)).thenReturn(true);
        Mockito.when(clienteRepository.getReferenceById(1)).thenReturn(cliente);

        // Llamado al método a probar
        ClienteDTO clienteDTO = clienteService.buscarPorId(1);

        // Verificación del resultado esperado
        assertEquals(clienteDTO.getId(), 1);
    }


    @Test
    void buscarTodos() throws Exception {
        TipoDocumento tipoDocumento = TipoDocumento.builder().codigo(1).nombre("Cédula").build();
        Cliente.builder()
                .id(1)
                .tipoDocumento(tipoDocumento)
                .nombre("Prueba")
                .direccion("Calle Carrera")
                .telefono("55555")
                .mail("elmail@mail.com")
                .build();

        List<Cliente> clientesRetorno = Arrays.asList(Cliente.builder()
                .id(1)
                .tipoDocumento(tipoDocumento)
                .nombre("Prueba")
                .direccion("Calle Carrera")
                .telefono("55555")
                .mail("elmail@mail.com")
                .build(),
                Cliente.builder()
                        .id(2)
                        .tipoDocumento(tipoDocumento)
                        .nombre("Prueba2")
                        .direccion("Calle Carrera")
                        .telefono("55555")
                        .mail("elmail@mail.com")
                        .build());

        // Mock de la respuesta del repositorio
        Mockito.when(clienteRepository.findAll()).thenReturn(clientesRetorno);

        List<ClienteDTO> clientes = clienteService.buscarTodos();

        assertEquals(2, clientes.size());
        assertEquals("Prueba2", clientes.get(1).getNombre());

    }



}
