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

    @Override
    public CuentaDTO modificarCuenta(CuentaDTO cuentaDTO) throws Exception {
        validarCuentaDTO(cuentaDTO, false);

        Cuenta cuenta = cuentaRepository.getReferenceById(cuentaDTO.getNumero());

        // Solo se podría modificar la clave de la cuenta, el resto de datos por seguridad no se modifica.
        cuenta.setClave(cuentaDTO.getClave());

        return CuentaMapper.modelToDTO(cuentaRepository.save(cuenta));
    }

    @Override
    public CuentaDTO inactivarCuenta(CuentaDTO cuentaDTO) throws Exception {
        validarActivacionInactivacionCuenta(cuentaDTO);

        validarCuentaDTOSiExiste(cuentaDTO, true);

        Cuenta cuenta = cuentaRepository.getReferenceById(cuentaDTO.getNumero());

        cuenta.setActiva(ConstantesUtility.ESTADO_CUENTA_INACTIVA);

        return retornarCuentaDTOActivaOInactiva(cuentaRepository.save(cuenta));
    }

    @Override
    public CuentaDTO activarCuenta(CuentaDTO cuentaDTO) throws Exception {
        validarActivacionInactivacionCuenta(cuentaDTO);

        validarCuentaDTOSiExiste(cuentaDTO, false);

        Cuenta cuenta = cuentaRepository.getReferenceById(cuentaDTO.getNumero());

        cuenta.setActiva(ConstantesUtility.ESTADO_CUENTA_ACTIVA);

        return retornarCuentaDTOActivaOInactiva(cuentaRepository.save(cuenta));
    }

    private void validarActivacionInactivacionCuenta(CuentaDTO cuentaDTO) throws Exception{
        //Validar cuentaDTO
        ValidationUtility.isNull(cuentaDTO, "Debe llegar información sobre la cuenta");

        //Validar numeroCuenta
        ValidationUtility.stringIsNullOrBlank(cuentaDTO.getNumero(), "Debe ingresar información del número de la cuenta");

        //Validar si llega la información del cliente
        ValidationUtility.integerIsNullOrLessZero(cuentaDTO.getClienteId(), "Debe ingresar el id del cliente");
    }

    public CuentaDTO retornarCuentaDTOActivaOInactiva(Cuenta cuenta) {
        return CuentaDTO.builder()
                .numero(cuenta.getNumero())
                .clienteId((cuenta.getCliente() != null) ?
                        cuenta.getCliente().getId() : null)
                .activa(cuenta.getActiva())
                .build();
    }

    private void validarCuentaDTO(CuentaDTO cuentaDTO, boolean esCreacion) throws Exception{
        //Validar cuentaDTO
        ValidationUtility.isNull(cuentaDTO, "Debe llegar información sobre la cuenta");

        //Validar numeroCuenta
        ValidationUtility.stringIsNullOrBlank(cuentaDTO.getNumero(), "Debe ingresar información del número de la cuenta");

        //Validar si llega la información del cliente
        ValidationUtility.integerIsNullOrLessZero(cuentaDTO.getClienteId(), "Debe ingresar el id del cliente");

        //Validar datos para creación o actualización
        if(esCreacion) {
            if(cuentaRepository.existsById(cuentaDTO.getNumero())) {
                throw new Exception("Ya existe un número de cuenta ".concat(cuentaDTO.getNumero()));
            }
        } else {
            validarCuentaDTOSiExiste(cuentaDTO, true);
        }

        if(!clienteRepository.existsById(cuentaDTO.getClienteId())) {
            throw new Exception("No existe el cliente con id ".concat(cuentaDTO.getClienteId().toString()));
        }

        //Validar saldo
        ValidationUtility.bigDecimalIsNullOrLessZero(cuentaDTO.getSaldo(), "El saldo inicial debe ser mayor a cero");

        //Validar clave
        ValidationUtility.stringIsNullOrBlank(cuentaDTO.getClave(), "Debe ingresar la clave");
    }

    private void validarCuentaDTOSiExiste(CuentaDTO cuentaDTO, boolean activa) throws Exception {
        if(!cuentaRepository.existsById(cuentaDTO.getNumero())) {
            throw new Exception("No existe la cuenta con número ".concat(cuentaDTO.getNumero()));
        }

        // Si está activa, se valida que se pueda inactivar
        if(activa && cuentaRepository.existsCuentaByActivaAndNumero(ConstantesUtility.ESTADO_CUENTA_INACTIVA, cuentaDTO.getNumero())) {
            throw new Exception("La cuenta con número "+cuentaDTO.getNumero()+" no se encuentra activa");
        }

        // Si no está activa, se valida que se pueda activar
        if(!activa && cuentaRepository.existsCuentaByActivaAndNumero(ConstantesUtility.ESTADO_CUENTA_ACTIVA, cuentaDTO.getNumero())) {
            throw new Exception("La cuenta con número "+cuentaDTO.getNumero()+" se encuentra activa");
        }

        if(!cuentaRepository.existsCuentaByNumeroAndClienteId(cuentaDTO.getNumero(), cuentaDTO.getClienteId())) {
            throw new Exception("La cuenta con número "+cuentaDTO.getNumero()+" no pertenece al cliente con identificación "+cuentaDTO.getClienteId());
        }
    }
}
