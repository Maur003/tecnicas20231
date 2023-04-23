package co.edu.usbcali.bancaweb.utility;

import co.edu.usbcali.bancaweb.dto.TipoDocumentoDTO;
import co.edu.usbcali.bancaweb.mapper.TipoDocumentoMapper;
import co.edu.usbcali.bancaweb.model.TipoDocumento;

import java.util.Arrays;
import java.util.List;

public class TipoDocumentoUtilTest {
    public static Integer CODIGO_UNO = 1;
    public static String NOMBRE_CEDULA = "Cédula";
    public static Integer CODIGO_DOS = 2;
    public static String NOMBRE_PASAPORTE = "Pasaporte";

    public static TipoDocumento TIPO_DOCUMENTO_CEDULA =
            TipoDocumento.builder().codigo(CODIGO_UNO).nombre(NOMBRE_CEDULA).build();
    public static TipoDocumento TIPO_DOCUMENTO_PASAPORTE =
            TipoDocumento.builder().codigo(CODIGO_DOS).nombre(NOMBRE_PASAPORTE).build();

    public static List<TipoDocumento> TIPOS_DOCUMENTOS = Arrays.asList(TIPO_DOCUMENTO_CEDULA, TIPO_DOCUMENTO_PASAPORTE);

    public static List<TipoDocumentoDTO> TIPOS_DOCUMENTOS_DTO = TipoDocumentoMapper.modelToDtoList(TIPOS_DOCUMENTOS);
}
