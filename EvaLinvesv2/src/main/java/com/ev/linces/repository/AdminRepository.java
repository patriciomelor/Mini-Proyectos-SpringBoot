package com.ev.linces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ev.linces.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}