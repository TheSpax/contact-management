package com.example.contactsproject.controller.interfaces;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Method invocation succeeded"),
        @ApiResponse(responseCode = "400", description = "A bad request was made"),
        @ApiResponse(responseCode = "401", description = "Cannot access. Make sure to be logged in."),
        @ApiResponse(responseCode = "403", description = "Access was forbidden")
})
public interface GlobalResponseDefinition {
}
