package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.model.Cliente;
import co.edu.usbcali.bancaweb.model.TipoDocumento;
import co.edu.usbcali.bancaweb.repository.ClienteRepository;
import co.edu.usbcali.bancaweb.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClienteServiceImplTest {

    @Autowired
    ClienteServiceImpl clienteService;

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
}