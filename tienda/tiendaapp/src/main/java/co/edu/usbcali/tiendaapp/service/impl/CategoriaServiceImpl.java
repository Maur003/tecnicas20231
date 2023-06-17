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
        return CategoriaMapper.domainToDtoList(categoriaRepository.findAll());
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
    public Categoria buscarTipoDocumentoPorId(Integer id) throws Exception {
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
        Categoria categoria = CategoriaMapper.dtoToDomain(categoriaDTO);
        return CategoriaMapper.domainToDto(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaDTO actualizar(CategoriaDTO categoriaDTO) throws Exception {
        validarCategoria(categoriaDTO, false);
        Categoria categoria = CategoriaMapper.dtoToDomain(categoriaDTO);
        return CategoriaMapper.domainToDto(categoriaRepository.save(categoria));
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
