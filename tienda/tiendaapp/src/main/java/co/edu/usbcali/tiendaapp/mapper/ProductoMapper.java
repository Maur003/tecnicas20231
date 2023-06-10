package co.edu.usbcali.tiendaapp.mapper;

import co.edu.usbcali.tiendaapp.domain.Producto;
import co.edu.usbcali.tiendaapp.dto.ProductoDTO;

import java.util.List;

public class ProductoMapper {

    public static ProductoDTO domainToDto(Producto producto) {
        return ProductoDTO.builder()
                .id(producto.getId())
                .referencia(producto.getReferencia())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precioUnitario(producto.getPrecioUnitario())
                .unidadesDisponibles(producto.getUnidadesDisponibles())
                .categoriaId((producto.getCategoria() == null) ?
                        null : producto.getCategoria().getId())
                .build();
    }

    public static Producto dtoToDomain(ProductoDTO productoDTO) {
        return Producto.builder()
                .id(productoDTO.getId())
                .referencia(productoDTO.getReferencia())
                .nombre(productoDTO.getNombre())
                .descripcion(productoDTO.getDescripcion())
                .precioUnitario(productoDTO.getPrecioUnitario())
                .unidadesDisponibles(productoDTO.getUnidadesDisponibles())
                .build();
    }

    public static List<ProductoDTO> domainToDtoList(List<Producto> productos) {
        return productos.stream().map(ProductoMapper::domainToDto).toList();
    }

    public static List<Producto> dtoToDomainList(List<ProductoDTO> productosDtos) {
        return productosDtos.stream().map(ProductoMapper::dtoToDomain).toList();
    }

}
