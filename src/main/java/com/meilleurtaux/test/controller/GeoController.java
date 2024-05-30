package com.meilleurtaux.test.controller;

import com.meilleurtaux.test.record.Town;
import com.meilleurtaux.test.service.IGeoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/geo")
public class GeoController {

    @Autowired
    private IGeoService iGeoService;

    @Operation(
            summary = "get a/all towns by postal code",
            description = "get a/all towns by postal code",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "400", description = "Invalid postal code"),
                    @ApiResponse(responseCode = "404", description = "Town not found"),
                    @ApiResponse(responseCode = "503", description = "External service error")})
    @GetMapping("/getTowns")
    public ResponseEntity<List<Town>> getTowns(@RequestParam String postalCode) {

        List<Town> towns = iGeoService.getTownsByPostalCode(postalCode);
        return ResponseEntity.ok(towns);

    }
}
