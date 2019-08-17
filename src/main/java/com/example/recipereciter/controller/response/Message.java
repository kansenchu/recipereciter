package com.example.recipereciter.controller.response;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 各種の返せるメッセージを表現するEnum。
 */
public enum Message {
    READ("Recipe details by id"),
    CREATED("Recipe successfully created!"),
    EDITED("Recipe successfully updated!"),
    DELETED("Recipe successfully removed!");

    private String message;

    Message(String msg) {
        message = msg;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }
}
