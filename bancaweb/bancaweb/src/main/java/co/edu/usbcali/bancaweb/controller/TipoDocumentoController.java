package co.edu.usbcali.bancaweb.controller;

import co.edu.usbcali.bancaweb.dto.MensajeDTO;
import co.edu.usbcali.bancaweb.dto.TipoDocumentoDTO;
import co.edu.usbcali.bancaweb.service.TipoDocumentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipodocumento")
public class TipoDocumentoController {

    private final TipoDocumentoService tipoDocumentoService;

    public TipoDocumentoController(TipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TipoDocumentoDTO>> todos(){
        return new ResponseEntity(
                tipoDocumentoService.buscarTodos()
                , HttpStatus.OK);
    }

    @PostMapping(path = "/guardarNuevoTipoDocumento",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarNuevoTipoDocumento(@RequestBody TipoDocumentoDTO tipoDocumentoDTO) {
        try {
            return new ResponseEntity(
                    tipoDocumentoService.guardarNuevoTipoDocumento(tipoDocumentoDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

}
