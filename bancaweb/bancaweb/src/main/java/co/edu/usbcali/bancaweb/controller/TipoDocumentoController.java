package co.edu.usbcali.bancaweb.controller;

import co.edu.usbcali.bancaweb.dto.MensajeDTO;
import co.edu.usbcali.bancaweb.dto.TipoDocumentoDTO;
import co.edu.usbcali.bancaweb.service.TipoDocumentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import java.lang.reflect.GenericArrayType;
import java.util.List;

@RestController
@RequestMapping("/tipodocumento")
public class TipoDocumentoController {

    private final TipoDocumentoService tipoDocumentoService;

    public TipoDocumentoController(TipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TipoDocumentoDTO>> todos() {
        return new ResponseEntity(
                tipoDocumentoService.buscarTodos()
                , HttpStatus.OK);
    }

    @GetMapping("/buscarPorCodigo/{codigo}")
    public ResponseEntity buscarPorCodigo(@PathVariable @Min(1) Integer codigo) throws Exception{
        return new ResponseEntity(tipoDocumentoService.buscarTipoDocumentoPorCodigo(codigo), HttpStatus.OK);
    }

}
