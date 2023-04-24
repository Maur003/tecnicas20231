package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.dto.CuentaDTO;

public interface CuentaService {
    ClienteDTO crearNuevaCuenta(CuentaDTO cuentaDTO) throws Exception;
}
