package co.edu.usbcali.bancaweb.controller;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.mapper.ClienteMapper;
import co.edu.usbcali.bancaweb.repository.ClienteRepository;
import co.edu.usbcali.bancaweb.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ClienteDTO>> todos(){
        return new ResponseEntity(
                clienteService.buscarTodos()
                , HttpStatus.OK);
    }

    //Generar método crearNuevoCliente el cual se conecte con ClienteService y retorne un ResponseEntity de ClienteDTO
    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> crearNuevoCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            return ResponseEntity.ok().body(clienteService.crearNuevoCliente(clienteDTO)); // retornar ResponseEntity con ClienteDTO convertido a JSON
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
