package co.edu.usbcali.bancaweb.mapper;

import co.edu.usbcali.bancaweb.dto.CuentaDTO;
import co.edu.usbcali.bancaweb.model.Cuenta;

import java.util.List;

public class CuentaMapper {

    public static CuentaDTO modelToDTO(Cuenta cuenta) {
        return CuentaDTO.builder()
                .numero(cuenta.getNumero())
                .clienteId((cuenta.getCliente() != null) ?
                        cuenta.getCliente().getId() : null)
                .saldo(cuenta.getSaldo())
                .activa(cuenta.getActiva())
                .clave(cuenta.getClave())
                .build();
    }

    public static Cuenta dtoToModel(CuentaDTO cuentaDTO) {
        return Cuenta.builder()
                .numero(cuentaDTO.getNumero())
                .saldo(cuentaDTO.getSaldo())
                .activa(cuentaDTO.getActiva())
                .clave(cuentaDTO.getClave())
                .build();
    }

    public static List<CuentaDTO> modelToDtoList(List<Cuenta> cuentas) {
        return cuentas.stream().map(CuentaMapper::modelToDTO).toList();
    }

    public static List<Cuenta> dtoToModelList(List<CuentaDTO> cuentasDTO) {
        return cuentasDTO.stream().map(CuentaMapper::dtoToModel).toList();
    }

}
