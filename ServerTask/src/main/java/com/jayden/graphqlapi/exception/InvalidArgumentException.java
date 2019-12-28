package com.jayden.graphqlapi.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvalidArgumentException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    public InvalidArgumentException(String message, Object value) {
        super(message);
        extensions.put("invalidValue", value);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}