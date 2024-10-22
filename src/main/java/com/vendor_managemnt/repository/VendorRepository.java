package com.vendor_managemnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendor_managemnt.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    
}
