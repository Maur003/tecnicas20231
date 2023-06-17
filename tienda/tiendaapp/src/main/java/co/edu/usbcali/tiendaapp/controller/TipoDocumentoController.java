package co.edu.usbcali.tiendaapp.controller;

import co.edu.usbcali.tiendaapp.dto.MensajeDTO;
import co.edu.usbcali.tiendaapp.dto.TipoDocumentoDTO;
import co.edu.usbcali.tiendaapp.mapper.TipoDocumentoMapper;
import co.edu.usbcali.tiendaapp.repository.TipoDocumentoRepository;
import co.edu.usbcali.tiendaapp.service.TipoDocumentoService;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoDocumento")
public class TipoDocumentoController {

    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoDocumentoService tipoDocumentoService;

    public TipoDocumentoController(TipoDocumentoRepository tipoDocumentoRepository,
                                   TipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @GetMapping("/buscarTodos")
    List<TipoDocumentoDTO> buscarTodos() {
        return TipoDocumentoMapper.domainToDtoList(tipoDocumentoRepository.findAll());
    }

    @GetMapping("/buscarPorId/{id}")
    ResponseEntity<TipoDocumentoDTO> buscarPorId(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<TipoDocumentoDTO>(tipoDocumentoService.buscarPorId(id),
                HttpStatus.OK);
    }

}
