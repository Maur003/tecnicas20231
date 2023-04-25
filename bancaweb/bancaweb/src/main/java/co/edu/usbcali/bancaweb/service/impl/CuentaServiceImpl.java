package co.edu.usbcali.bancaweb.service.impl;

import co.edu.usbcali.bancaweb.dto.CuentaDTO;
import co.edu.usbcali.bancaweb.mapper.CuentaMapper;
import co.edu.usbcali.bancaweb.model.Cliente;
import co.edu.usbcali.bancaweb.model.Cuenta;
import co.edu.usbcali.bancaweb.repository.ClienteRepository;
import co.edu.usbcali.bancaweb.repository.CuentaRepository;
import co.edu.usbcali.bancaweb.service.CuentaService;
import co.edu.usbcali.bancaweb.utility.ConstantesUtility;
import co.edu.usbcali.bancaweb.utility.ValidationUtility;
import org.springframework.stereotype.Service;

@Service
public class CuentaServiceImpl implements CuentaService {
    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public CuentaDTO crearNuevaCuenta(CuentaDTO cuentaDTO) throws Exception {
        validarCuentaDTO(cuentaDTO, true);
        //Convertir CuentaDTO a Cuenta
        return crearOModificarCuenta(cuentaDTO);
    }

    private void validarCuentaDTO(CuentaDTO cuentaDTO, boolean esCreacion) throws Exception{
        //Validar cuentaDTO
        ValidationUtility.isNull(cuentaDTO, "Debe llegar información sobre la cuenta");

        //Validar numeroCuenta
        ValidationUtility.stringIsNullOrBlank(cuentaDTO.getNumero(), "Debe ingresar información del número de la cuenta");

        //Validar datos para creación o actualización
        if(esCreacion) {
            if(cuentaRepository.existsById(cuentaDTO.getNumero())) {
                throw new Exception("Ya existe un número de cuenta ".concat(cuentaDTO.getNumero()));
            }
        } else {
            if(!cuentaRepository.existsById(cuentaDTO.getNumero())) {
                throw new Exception("No existe la cuenta con número ".concat(cuentaDTO.getNumero()));
            }
        }

        //Validar si llega la información del cliente
        ValidationUtility.integerIsNullOrLessZero(cuentaDTO.getClienteId(), "Debe ingresar el id del cliente");

        if(!clienteRepository.existsById(cuentaDTO.getClienteId())) {
            throw new Exception("No existe el cliente con id ".concat(cuentaDTO.getClienteId().toString()));
        }

        //Validar saldo
        ValidationUtility.bigDecimalIsNullOrLessZero(cuentaDTO.getSaldo(), "El saldo inicial debe ser mayor a cero");

        //Validar clave
        ValidationUtility.stringIsNullOrBlank(cuentaDTO.getClave(), "Debe ingresar la clave");
    }

    private CuentaDTO crearOModificarCuenta(CuentaDTO cuentaDTO) {
        // Mapeo la cuenta hacia el Model
        Cuenta cuenta = CuentaMapper.dtoToModel(cuentaDTO);

        //Consulto el cliente
        Cliente cliente =
                clienteRepository.getReferenceById(cuentaDTO.getClienteId());

        //Asigno el cliente a la cuenta
        cuenta.setCliente(cliente);
        //Activo la cuenta
        cuenta.setActiva(ConstantesUtility.ESTADO_CUENTA_ACTIVA);

        return CuentaMapper.modelToDTO(cuentaRepository.save(cuenta));
    }
}
