package co.edu.usbcali.tiendaapp.controller;

import co.edu.usbcali.tiendaapp.dto.CategoriaDTO;
import co.edu.usbcali.tiendaapp.dto.TipoDocumentoDTO;
import co.edu.usbcali.tiendaapp.mapper.TipoDocumentoMapper;
import co.edu.usbcali.tiendaapp.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/nueva")
    CategoriaDTO nuevaCategoria(@RequestBody CategoriaDTO categoriaDTO) throws Exception {
        return categoriaService.guardar(categoriaDTO);
    }

    @GetMapping("/buscarTodas")
    List<CategoriaDTO> buscarTodas() {
        return categoriaService.obtenerTodos();
    }

    @PutMapping("/actualizar")
    CategoriaDTO actualizarCategoria(@RequestBody CategoriaDTO categoriaDTO) throws Exception {
        return categoriaService.actualizar(categoriaDTO);
    }
}
