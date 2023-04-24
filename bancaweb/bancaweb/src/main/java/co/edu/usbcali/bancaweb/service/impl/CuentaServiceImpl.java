package co.edu.usbcali.bancaweb.service.impl;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.dto.CuentaDTO;
import co.edu.usbcali.bancaweb.mapper.CuentaMapper;
import co.edu.usbcali.bancaweb.model.Cuenta;
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

        //Validar si llega la información del cliente
        ValidationUtility.integerIsNullOrLessZero(cuentaDTO.getClienteId(), "Debe ingresar el id del cliente");

        //Validar saldo
        ValidationUtility.bigDecimalIsNullOrLessZero(cuentaDTO.getSaldo(), "El saldo inicial debe ser mayor a cero");

        //Validar clave
        ValidationUtility.stringIsNullOrBlank(cuentaDTO.getClave(), "Debe ingresar la clave");

        //Convertir CuentaDTO a Cuenta
        Cuenta cuenta = CuentaMapper.dtoToModel(cuentaDTO);
    }
}
