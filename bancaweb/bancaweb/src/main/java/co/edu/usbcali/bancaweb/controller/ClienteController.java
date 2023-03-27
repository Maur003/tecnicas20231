package co.edu.usbcali.bancaweb.controller;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.dto.TipoDocumentoDTO;
import co.edu.usbcali.bancaweb.mapper.ClienteMapper;
import co.edu.usbcali.bancaweb.repository.ClienteRepository;
import co.edu.usbcali.bancaweb.service.TipoDocumentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ClienteDTO>> todos(){
        return new ResponseEntity(
                ClienteMapper.modelToDtoList(clienteRepository.findAll())
                , HttpStatus.OK);
    }

}
