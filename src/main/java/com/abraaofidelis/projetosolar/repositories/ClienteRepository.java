package com.abraaofidelis.projetosolar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abraaofidelis.projetosolar.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {

}
