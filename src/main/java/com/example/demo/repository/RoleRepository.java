package com.example.demo.repository;

import com.example.demo.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Asset, Long> {

}