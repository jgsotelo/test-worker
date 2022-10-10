package com.example.intcomex.application.exception;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Builder
public class BinderMessage implements Serializable {

    private String attribute;
    private String message;

    public static BinderMessage from(final ObjectError error) {
        return BinderMessage.builder()
                .attribute(new Gson().toJsonTree(Objects
                                .requireNonNull(error.getArguments())[0])
                        .getAsJsonObject().get("defaultMessage").getAsString())
                .message(error.getDefaultMessage())
                .build();
    }
}
