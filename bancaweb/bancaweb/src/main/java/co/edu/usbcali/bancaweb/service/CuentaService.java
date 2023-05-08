package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.CuentaDTO;

public interface CuentaService {
    CuentaDTO crearNuevaCuenta(CuentaDTO cuentaDTO) throws Exception;

    CuentaDTO modificarCuenta(CuentaDTO cuentaDTO) throws Exception;

    CuentaDTO inactivarCuenta(CuentaDTO cuentaDTO) throws Exception;
    CuentaDTO activarCuenta(CuentaDTO cuentaDTO) throws Exception;
}
