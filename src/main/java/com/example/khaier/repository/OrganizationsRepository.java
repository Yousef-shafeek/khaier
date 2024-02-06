package com.example.khaier.repository;

import com.example.khaier.entity.CharitableOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationsRepository extends JpaRepository<CharitableOrganization, Long> {
}