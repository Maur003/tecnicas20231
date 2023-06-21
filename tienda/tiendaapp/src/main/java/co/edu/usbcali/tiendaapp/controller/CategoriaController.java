package co.edu.usbcali.tiendaapp.controller;

import co.edu.usbcali.tiendaapp.dto.CategoriaDTO;
import co.edu.usbcali.tiendaapp.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping(value = "/nueva")
    CategoriaDTO nuevaCategoria(@RequestBody CategoriaDTO categoriaDTO) throws Exception {
        return categoriaService.guardar(categoriaDTO);
    }

    @GetMapping(value = "/buscarTodas")
    List<CategoriaDTO> buscarTodas() {
        return categoriaService.obtenerTodos();
    }

    @PutMapping(value = "/actualizar")
    CategoriaDTO actualizarCategoria(@RequestBody CategoriaDTO categoriaDTO) throws Exception {
        return categoriaService.actualizar(categoriaDTO);
    }

    @GetMapping(value = "/buscarPorNombre/")
    List<CategoriaDTO> buscarPorNombre(@RequestParam("nombre") String nombre) throws Exception {
        return categoriaService.buscarPorNombreLike(nombre);
    }

}
