package co.edu.usbcali.bancaweb.controller;

import co.edu.usbcali.bancaweb.dto.CuentaDTO;
import co.edu.usbcali.bancaweb.dto.MensajeDTO;
import co.edu.usbcali.bancaweb.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping(path = "/crearNuevaCuenta",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity crearNuevaCuenta(@RequestBody CuentaDTO cuentaDTO) {
        try {
            return ResponseEntity.ok().body(cuentaService.crearNuevaCuenta(cuentaDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MensajeDTO.builder().mensaje(e.getMessage()).build());
        }
    }
}
