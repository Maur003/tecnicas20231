package co.edu.usbcali.bancaweb.mapper;

import co.edu.usbcali.bancaweb.dto.ConsignacionDTO;
import co.edu.usbcali.bancaweb.model.Consignacion;

import java.util.List;

public class ConsignacionMapper {

    public static ConsignacionDTO modelToDTO(Consignacion consignacion) {
        return ConsignacionDTO.builder()
                .codigo(consignacion.getCodigo())
                .cuentaNumero((consignacion.getCuenta() != null) ?
                        consignacion.getCuenta().getNumero() : null)
                .usuarioCedula((consignacion.getUsuario() != null) ?
                        consignacion.getUsuario().getCedula() : null)
                .valor(consignacion.getValor())
                .fecha(consignacion.getFecha())
                .descripcion(consignacion.getDescripcion())
                .build();
    }

    public static Consignacion dtoToModel(ConsignacionDTO consignacionDTO) {
        return Consignacion.builder()
                .codigo(consignacionDTO.getCodigo())
                .valor(consignacionDTO.getValor())
                .fecha(consignacionDTO.getFecha())
                .descripcion(consignacionDTO.getDescripcion())
                .build();
    }

    public static List<ConsignacionDTO> modelToDtoList(List<Consignacion> consignaciones) {
        return consignaciones.stream().map(ConsignacionMapper::modelToDTO).toList();
    }

    public static List<Consignacion> dtoToModelList(List<ConsignacionDTO> consignacionesDTO) {
        return consignacionesDTO.stream().map(ConsignacionMapper::dtoToModel).toList();
    }

}
