package com.platzi.market.web.controller;

import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    @Operation(
            summary = "Get all purchases",
            description = "Retrieve a list of all purchases made."
    )
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Successful purchases"),
            @ApiResponse(responseCode = "404", description = "Couldn't found the purchases")
    })
    private ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/byClient/{clientId}")
    @Operation(
            summary = "Get purchases by client",
            description = "Retrieve a list of purchases made by a specific client."
    )
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @Parameter(
            name = "clientId",
            description = "This is the id number of the client that you want his purchases list",
            required = true,
            schema = @Schema(type = "integer")
    )
    private ResponseEntity<List<Purchase>> getByClient(@PathVariable("clientId") String clientId){
        return purchaseService.getByClient(clientId)
                .map(purchase -> new ResponseEntity<>(purchase,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(
            summary = "Save a purchase",
            description = "Save a new purchase in the system."
    )
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Couldn't save the purchase")
    })
    private ResponseEntity<Purchase> save(@RequestBody(required = true) Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase),HttpStatus.OK);
    }
}

