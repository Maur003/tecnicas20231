package co.edu.usbcali.bancaweb.controller;

import co.edu.usbcali.bancaweb.dto.CuentaDTO;
import co.edu.usbcali.bancaweb.dto.MensajeDTO;
import co.edu.usbcali.bancaweb.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(path = "/modificarCuenta")
    public ResponseEntity modificarCuenta(@RequestBody CuentaDTO cuentaDTO) {
        try {
            return ResponseEntity.ok().body(cuentaService.modificarCuenta(cuentaDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MensajeDTO.builder().mensaje(e.getMessage()).build());
        }
    }

    @PutMapping(path = "/inactivarCuenta")
    public ResponseEntity inactivarCuenta(@RequestBody CuentaDTO cuentaDTO) {
        try {
            return ResponseEntity.ok().body(cuentaService.inactivarCuenta(cuentaDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MensajeDTO.builder().mensaje(e.getMessage()).build());
        }
    }

    @PutMapping(path = "/activarCuenta")
    public ResponseEntity activarCuenta(@RequestBody CuentaDTO cuentaDTO) {
        try {
            return ResponseEntity.ok().body(cuentaService.activarCuenta(cuentaDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MensajeDTO.builder().mensaje(e.getMessage()).build());
        }
    }
}
