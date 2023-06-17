package co.edu.usbcali.tiendaapp.mapper;

import co.edu.usbcali.tiendaapp.domain.Categoria;
import co.edu.usbcali.tiendaapp.dto.CategoriaDTO;

import java.util.List;

public class CategoriaMapper {

    public static CategoriaDTO domainToDto(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }

    public static Categoria dtoToDomain(CategoriaDTO categoriaDTO) {
        return Categoria.builder()
                .id(categoriaDTO.getId())
                .nombre(categoriaDTO.getNombre())
                .descripcion(categoriaDTO.getDescripcion())
                .build();
    }

    public static List<CategoriaDTO> domainToDtoList(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaMapper::domainToDto).toList();

        /* List<CategoriaDTO> lasCategoriasDto = new ArrayList<>();
        for (Categoria categoria: categorias) {
            lasCategoriasDto.add(domainToDto(categoria));
        }
        return lasCategoriasDto;
        */
    }

    public static List<Categoria> dtoToDomainList(List<CategoriaDTO> categoriasDtos) {
        return categoriasDtos.stream().map(CategoriaMapper::dtoToDomain).toList();
    }

}
