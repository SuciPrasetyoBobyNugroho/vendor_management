package com.vendor_managemnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendor_managemnt.model.Vendor;
import com.vendor_managemnt.repository.VendorRepository;

@Service
public class VendorService {
    
    @Autowired
    private VendorRepository vendorRepository;

    public List<Vendor> getAllVendors(){
        return vendorRepository.findAll();
    }

    public Vendor getVendorById(Long id){
        return vendorRepository.findById(id).orElse(null);
    }

    public Vendor saveVendor(Vendor vendor){
        return vendorRepository.save(vendor);
    }

    public Vendor updateVendor(Long id, Vendor vendorDetails) {
        Vendor vendor = vendorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Vendor dengan id " + id + " tidak di temukan"));
        vendor.setName(vendorDetails.getName());
        return vendorRepository.save(vendor);
    }

    public void deleteVendor(Long id){
        vendorRepository.deleteById(id);
    }
}
