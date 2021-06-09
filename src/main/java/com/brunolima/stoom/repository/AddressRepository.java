package com.brunolima.stoom.repository;

import com.brunolima.stoom.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT * FROM address WHERE id = :id", nativeQuery = true)
    Address findAddressById(Long id);
}
