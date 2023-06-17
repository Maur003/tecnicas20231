package co.edu.usbcali.tiendaapp.controller;

import co.edu.usbcali.tiendaapp.request.CrearProductoRequest;
import co.edu.usbcali.tiendaapp.response.CrearProductoResponse;
import co.edu.usbcali.tiendaapp.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/nuevo")
    CrearProductoResponse nuevoProducto(@RequestBody @Valid CrearProductoRequest crearProductoRequest) throws Exception {
        return productoService.guardarNuevo(crearProductoRequest);
    }

}
