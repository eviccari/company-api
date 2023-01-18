package br.com.my_corp.company.company_api.controllers;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import br.com.my_corp.company.company_api.app.exceptions.BadRequestException;
import br.com.my_corp.company.company_api.app.exceptions.HTTPException;
import br.com.my_corp.company.company_api.app.exceptions.InternalServerErrorException;
import br.com.my_corp.company.company_api.app.exceptions.NotFoundException;
import br.com.my_corp.company.company_api.app.exceptions.UnprocessableEntityException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ControllerConfiguration {

    @ExceptionHandler({
        BadRequestException.class, 
        InternalServerErrorException.class,
        NotFoundException.class,
        UnprocessableEntityException.class        
    })
    public ResponseEntity<ErrorPayload> errorHandler(HTTPException<Integer> exception) {
        return new ResponseEntity<>(
            ErrorPayload.builder()
                .message(exception.getMessage())
                .errorCode(exception.getErrorCode())
            .build(), 
            HttpStatus.valueOf(exception.getErrorCode())
        );
    }

    @AllArgsConstructor
    @Builder
    public static class ErrorPayload implements Serializable{
        @Getter
        @Setter
        private int errorCode;

        @Getter
        @Setter
        private String message;
    }

    @AllArgsConstructor
    @Builder
    public static class UpdatePayload implements Serializable {
        @Getter
        @Setter
        private String companyId;

        @Getter
        @Setter
        private int updatedRowCount;

    }
}
