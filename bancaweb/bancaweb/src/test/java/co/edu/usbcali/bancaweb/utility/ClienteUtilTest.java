package co.edu.usbcali.bancaweb.utility;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.model.Cliente;

import java.util.Arrays;
import java.util.List;

public class ClienteUtilTest {
    public static Integer ID_UNO = 1;
    public static String NOMBRE_UNO = "Jhon Pérez";
    public static String DIRECCION_UNO = "Calle Carrera";
    public static String TELEFONO_UNO = "4444444";
    public static String MAIL_UNO = "jp@mail.com";
    public static Integer ID_DOS = 2;
    public static String NOMBRE_DOS = "Hugo Flórez";
    public static String DIRECCION_DOS = "Calle Carrera";
    public static String TELEFONO_DOS = "4444444";
    public static String MAIL_DOS = "hf@mail.com";

    public static Cliente CLIENTE_UNO =
            Cliente.builder()
                    .id(ID_UNO)
                    .tipoDocumento(TipoDocumentoUtilTest.TIPO_DOCUMENTO_CEDULA)
                    .nombre(NOMBRE_UNO)
                    .direccion(DIRECCION_UNO)
                    .telefono(TELEFONO_UNO)
                    .mail(MAIL_UNO)
                    .build();
    public static Cliente CLIENTE_DOS =
            Cliente.builder()
                    .id(ID_DOS)
                    .tipoDocumento(TipoDocumentoUtilTest.TIPO_DOCUMENTO_CEDULA)
                    .nombre(NOMBRE_DOS)
                    .direccion(DIRECCION_DOS)
                    .telefono(TELEFONO_DOS)
                    .mail(MAIL_DOS)
                    .build();

    public static List<Cliente> CLIENTES =
            Arrays.asList(CLIENTE_UNO, CLIENTE_DOS);

    public static ClienteDTO CLIENTEDTO_UNO =
            ClienteDTO.builder()
                    .id(ID_UNO)
                    .tipoDocumentoCodigo(TipoDocumentoUtilTest.CODIGO_UNO)
                    .nombre(NOMBRE_UNO)
                    .direccion(DIRECCION_UNO)
                    .telefono(TELEFONO_UNO)
                    .mail(MAIL_UNO)
                    .build();

    public static ClienteDTO CLIENTEDTO_UNO_NO_ID =
            ClienteDTO.builder()
                    .tipoDocumentoCodigo(TipoDocumentoUtilTest.CODIGO_UNO)
                    .nombre(NOMBRE_UNO)
                    .direccion(DIRECCION_UNO)
                    .telefono(TELEFONO_UNO)
                    .mail(MAIL_UNO)
                    .build();
}
