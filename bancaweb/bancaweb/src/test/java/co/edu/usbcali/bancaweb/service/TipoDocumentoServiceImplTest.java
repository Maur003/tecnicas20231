package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.TipoDocumentoDTO;
import co.edu.usbcali.bancaweb.repository.TipoDocumentoRepository;
import co.edu.usbcali.bancaweb.service.impl.TipoDocumentoServiceImpl;
import co.edu.usbcali.bancaweb.utility.TipoDocumentoUtilTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class TipoDocumentoServiceImplTest {

    @InjectMocks
    private TipoDocumentoServiceImpl tipoDocumentoService;

    @Mock
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Test
    void buscarTodos(){
        // Mock de la respuesta del repositorio
        given(tipoDocumentoRepository.findAll()).willReturn(TipoDocumentoUtilTest.TIPOS_DOCUMENTOS);

        List<TipoDocumentoDTO> tiposDocumentosEsperado = tipoDocumentoService.buscarTodos();
        // Verificación del resultado esperado
        assertEquals(tiposDocumentosEsperado.size(), 2);
    }

    @Test
    void buscarTipoDocumentoPorCodigo() throws Exception {
        // Mock de la respuesta del repositorio
        given(tipoDocumentoRepository.findById(TipoDocumentoUtilTest.CODIGO_UNO)).willReturn(Optional.of(TipoDocumentoUtilTest.TIPO_DOCUMENTO_CEDULA));

        TipoDocumentoDTO tipoDocumentoDTOEsperado = tipoDocumentoService.buscarTipoDocumentoPorCodigo(TipoDocumentoUtilTest.CODIGO_UNO);
        // Verificación del resultado esperado
        assertEquals(TipoDocumentoUtilTest.NOMBRE_CEDULA, tipoDocumentoDTOEsperado.getNombre());
    }
}
