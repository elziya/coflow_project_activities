package com.technokratos.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Сообщение описывающее возникшую исключительную ситуацию.
 */
@Data
@SuperBuilder
public class ExceptionMessage {

    /** Описание пути, по которому возникла исключительная ситуация */
    private String endpoint;

    /** Сообщение исключения */
    private String message;

    /** Детальное сообщение исключения */
    private String detailMessage;

    /** Наименование исключения */
    private String exceptionName;
}

