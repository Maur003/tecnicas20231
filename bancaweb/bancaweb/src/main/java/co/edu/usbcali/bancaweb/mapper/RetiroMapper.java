package co.edu.usbcali.bancaweb.mapper;

import co.edu.usbcali.bancaweb.dto.RetiroDTO;
import co.edu.usbcali.bancaweb.model.Retiro;

import java.util.List;

public class RetiroMapper {

    public static RetiroDTO modelToDTO(Retiro retiro) {
        return RetiroDTO.builder()
                .codigo(retiro.getCodigo())
                .cuentaNumero((retiro.getCuenta() != null) ?
                        retiro.getCuenta().getNumero() : null)
                .usuarioCedula((retiro.getUsuario() != null) ?
                        retiro.getUsuario().getCedula() : null)
                .valor(retiro.getValor())
                .fecha(retiro.getFecha())
                .descripcion(retiro.getDescripcion())
                .build();
    }

    public static Retiro dtoToModel(RetiroDTO retiroDTO) {
        return Retiro.builder()
                .codigo(retiroDTO.getCodigo())
                .valor(retiroDTO.getValor())
                .fecha(retiroDTO.getFecha())
                .descripcion(retiroDTO.getDescripcion())
                .build();
    }

    public static List<RetiroDTO> modelToDtoList(List<Retiro> retiros) {
        return retiros.stream().map(RetiroMapper::modelToDTO).toList();
    }

    public static List<Retiro> dtoToModelList(List<RetiroDTO> retirosDTO) {
        return retirosDTO.stream().map(RetiroMapper::dtoToModel).toList();
    }

}
