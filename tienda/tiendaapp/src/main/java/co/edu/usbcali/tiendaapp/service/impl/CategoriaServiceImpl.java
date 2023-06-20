package co.edu.usbcali.tiendaapp.service.impl;

import co.edu.usbcali.tiendaapp.domain.Categoria;
import co.edu.usbcali.tiendaapp.dto.CategoriaDTO;
import co.edu.usbcali.tiendaapp.exceptions.CategoriaException;
import co.edu.usbcali.tiendaapp.mapper.CategoriaMapper;
import co.edu.usbcali.tiendaapp.repository.CategoriaRepository;
import co.edu.usbcali.tiendaapp.service.CategoriaService;
import co.edu.usbcali.tiendaapp.util.ValidationsUtil;
import co.edu.usbcali.tiendaapp.util.messages.CategoriaServiceMessages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CategoriaDTO> obtenerTodos() {
        return CategoriaMapper.domainToDtoList(categoriaRepository.findAllByOrderByNombreAsc());
    }

    @Override
    public CategoriaDTO buscarPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, CategoriaServiceMessages.ID_VALIDO_MSG);
        return categoriaRepository.findById(id).map(CategoriaMapper::domainToDto).orElseThrow(
                () -> new CategoriaException
                        (String.format(
                                CategoriaServiceMessages.CATEGORIA_NO_ENCONTRADA_POR_ID, id))

        );
    }

    @Override
    public Categoria buscarCategoriaPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, CategoriaServiceMessages.ID_VALIDO_MSG);
        return categoriaRepository.findById(id).orElseThrow(
                () -> new CategoriaException
                        (String.format(
                                CategoriaServiceMessages.CATEGORIA_NO_ENCONTRADA_POR_ID, id))

        );
    }

    @Override
    public CategoriaDTO guardar(CategoriaDTO categoriaDTO) throws Exception {
        validarCategoria(categoriaDTO, true);

        //Valiar si existe la categoria con ese nombre
        boolean existePorNombre = categoriaRepository.existsByNombreIgnoreCase(categoriaDTO.getNombre());
        if(existePorNombre) throw new Exception(
                String.format(CategoriaServiceMessages.EXISTE_POR_NOMBRE, categoriaDTO.getNombre()));

        Categoria categoria = CategoriaMapper.dtoToDomain(categoriaDTO);
        return CategoriaMapper.domainToDto(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaDTO actualizar(CategoriaDTO categoriaDTO) throws Exception {
        validarCategoria(categoriaDTO, false);

        //Valiar si existe la categoria con ese nombre y otro Id
        boolean existePorNombreYOtroId = categoriaRepository
                .existsByNombreIgnoreCaseAndIdNot(categoriaDTO.getNombre(), categoriaDTO.getId());
        if(existePorNombreYOtroId) throw new Exception(
                String.format(CategoriaServiceMessages.EXISTE_POR_NOMBRE, categoriaDTO.getNombre()));

        Categoria categoria = buscarCategoriaPorId(categoriaDTO.getId());

        //Modificar atributos
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());

        return CategoriaMapper.domainToDto(categoriaRepository.save(categoria));
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        //Esto lo deben implementar para el miércoles 21 de junio del 2023, hasta las 5 pm
    }

    @Override
    public List<CategoriaDTO> buscarPorNombreLike(String nombre) throws Exception {
        ValidationsUtil.stringIsNullOrBlank(nombre, CategoriaServiceMessages.NOMBRE_REQUERIDO);
        return CategoriaMapper.domainToDtoList(categoriaRepository.findByNombreLikeIgnoreCase("%"+nombre+"%"));
    }

    private void validarCategoria(CategoriaDTO categoriaDTO, boolean esGuardado) throws Exception {
        if(!esGuardado) {
            ValidationsUtil.isNull(categoriaDTO.getId(), CategoriaServiceMessages.ID_REQUERIDO);
        }
        ValidationsUtil.isNull(categoriaDTO, CategoriaServiceMessages.CATEGORIA_NULA);
        ValidationsUtil.stringIsNullOrBlank(categoriaDTO.getNombre(), CategoriaServiceMessages.NOMBRE_REQUERIDO);
        ValidationsUtil.stringIsNullOrBlank(categoriaDTO.getDescripcion(), CategoriaServiceMessages.DESCRIPCION_REQUERIDA);
    }
}
