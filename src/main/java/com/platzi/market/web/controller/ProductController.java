package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @Operation(summary = "Get all supermarket products")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "All products has been found"),
            @ApiResponse(responseCode = "400",description = "There is a problem with the products")
    })
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }


    @GetMapping("/{productId}")
    @Operation(
            summary = "Get a specific product",
            description = "This endpoint allows you to receive a specific product into inventory whit its id"
    )
    @Parameter(
            name ="productId",
            description = "This is the id number of the product that you need",
            required = true,
            schema = @Schema(type = "integer")
    )
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "400",description = "The product could not be found (Check the product id)")
    })
    public ResponseEntity <Product> getProduct(@PathVariable("productId") int productId){
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product,HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    @Operation(
            summary = "Get products by their category",
            description = "You can get all of products that share category, filter them by category id"
    )
    @Parameter(
            name = "categoryId",
            description = "This is the id number of the category that you want to get all of products",
            required = true,
            schema = @Schema(type = "integer")
    )
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Products found"),
            @ApiResponse(responseCode = "404",description = "The products could not be found (Check if the category id is available)")
    })
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoriaId){
        return productService.getByCategory(categoriaId)
                .map(product -> new ResponseEntity<>(product,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(
            summary = "Save a new product",
            description = "To save a new product to inventory you must to send the correct JSON information in the request body"
    )
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Product saved successfully"),
            @ApiResponse(responseCode = "404",description = "The new product couldn't be saved (Check body information)")
    })
    public ResponseEntity<Product> save(@RequestBody(required = true) Product product){
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{productId}")
    @Operation(
            summary = "Delete products",
            description = "Whit this method you could delete  any product with its product id"
    )
    @Parameter(
            name = "productId",
            required = true,
            description = "The number of the product to delete",
            schema = @Schema(type = "integer")
    )
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404",description = "The new product couldn't be deleted (Check if product id is available)")
    })
    public ResponseEntity delete(@PathVariable("productId") int productId){
        if(productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
