package co.edu.usbcali.bancaweb.controller;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.dto.MensajeDTO;
import co.edu.usbcali.bancaweb.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;

    }

    @GetMapping("/todos")
    public ResponseEntity<List<ClienteDTO>> todos() {
        return new ResponseEntity(
                clienteService.buscarTodos()
                , HttpStatus.OK);
    }


    @PostMapping(path = "/crearNuevoCliente",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity crearNuevoCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            return ResponseEntity.ok().body(clienteService.crearNuevoCliente(clienteDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MensajeDTO.builder().mensaje(e.getMessage()).build());
        }
    }

    @PutMapping(path = "/modificarCliente",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity modificarCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            return ResponseEntity.ok().body(clienteService.modificarCliente(clienteDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MensajeDTO.builder().mensaje(e.getMessage()).build());
        }
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity buscarPorId(@PathVariable @Min(1) Integer id) throws Exception {
        try {
            return ResponseEntity.ok().body(clienteService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MensajeDTO.builder().mensaje(e.getMessage()).build());
        }
    }

}
