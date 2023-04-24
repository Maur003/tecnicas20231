package co.edu.usbcali.bancaweb.service.impl;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.dto.CuentaDTO;
import co.edu.usbcali.bancaweb.repository.CuentaRepository;
import co.edu.usbcali.bancaweb.service.CuentaService;
import co.edu.usbcali.bancaweb.utility.ValidationUtility;
import org.springframework.stereotype.Service;

@Service
public class CuentaServiceImpl implements CuentaService {
    private final CuentaRepository cuentaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public ClienteDTO crearNuevaCuenta(CuentaDTO cuentaDTO) throws Exception {
        validarCuentaDTO(cuentaDTO, true);
        return null;
    }

    private void validarCuentaDTO(CuentaDTO cuentaDTO, boolean esCreacion) throws Exception{
        //Validar cuentaDTO
        ValidationUtility.isNull(cuentaDTO, "Debe llegar información sobre la cuenta");

        //Validar numeroCuenta
        ValidationUtility.stringIsNullOrBlank(cuentaDTO.getNumero(), "Debe ingresar información del número de la cuenta");
    }
}
