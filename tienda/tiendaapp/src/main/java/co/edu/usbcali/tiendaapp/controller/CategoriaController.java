package co.edu.usbcali.tiendaapp.controller;

import co.edu.usbcali.tiendaapp.dto.CategoriaDTO;
import co.edu.usbcali.tiendaapp.dto.TipoDocumentoDTO;
import co.edu.usbcali.tiendaapp.mapper.TipoDocumentoMapper;
import co.edu.usbcali.tiendaapp.service.CategoriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/categoria")
    CategoriaDTO nuevaCategoria(@RequestBody CategoriaDTO categoriaDTO) throws Exception {
        return categoriaService.guardar(categoriaDTO);
    }

    @GetMapping("/categoria")
    List<CategoriaDTO> buscarTodas() {
        return categoriaService.obtenerTodos();
    }
}
