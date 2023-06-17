package co.edu.usbcali.tiendaapp.service;

import co.edu.usbcali.tiendaapp.request.CrearProductoRequest;
import co.edu.usbcali.tiendaapp.response.CrearProductoResponse;

public interface ProductoService {
    CrearProductoResponse guardarNuevo(CrearProductoRequest crearProductoRequest) throws Exception;
}
