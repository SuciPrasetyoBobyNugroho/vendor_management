package com.vendor_managemnt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendor_managemnt.dto.ResponseData;
import com.vendor_managemnt.model.Vendor;
import com.vendor_managemnt.service.VendorService;

import io.github.bucket4j.Bucket;
import io.swagger.v3.oas.models.responses.ApiResponse;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private Bucket bucket;

    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Vendor>> getVendorById(@PathVariable Long id) {
        if (bucket.tryConsume(1)) { 
            Vendor vendor = vendorService.getVendorById(id); 
    
            if (vendor == null) { // Cek jika vendor tidak ditemukan
                ResponseData<Vendor> response = new ResponseData<>(false, "Vendor dengan ID " + id + " tidak ditemukan.", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); 
            }
    
            ResponseData<Vendor> response = new ResponseData<>(true, "Vendor berhasil di temukan", vendor);
            return ResponseEntity.ok(response); // Kembalikan vendor jika ditemukan
        } else {
            ResponseData<Vendor> response = new ResponseData<>(false, "Terlalu banyak permintaan, coba lagi nanti.", null);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseData<Vendor>> createVendor(@RequestBody Vendor vendor) {
        if (bucket.tryConsume(1)) {
            Vendor createdVendor = vendorService.saveVendor(vendor);
            ResponseData<Vendor> response = new ResponseData<>(true, "Vendor berhasil di buat", createdVendor);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            ResponseData<Vendor> response = new ResponseData<>(false, "Terlalu banyak permintaan, coba lagi nanti.",
                    null);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<String>> updateVendor(@PathVariable Long id, @RequestBody Vendor vendor) {
        if (bucket.tryConsume(1)) {
            vendorService.updateVendor(id, vendor);
            ResponseData<String> response = new ResponseData<>(true, "Vendor berhasil di buat", null);
            return ResponseEntity.ok(response);
        } else {
            ResponseData<String> response = new ResponseData<>(false, "Terlalu banyak permintaan, coba lagi nanti.",
                    null);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response);
        }
    }

    @DeleteMapping
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
    }
}
