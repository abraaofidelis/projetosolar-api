package com.abraaofidelis.projetosolar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abraaofidelis.projetosolar.models.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

	List<Projeto> findByCliente_Id(Long id);
	
	/*
	@Query(value = "SELECT t FROM Projeto p WHERE p.projeto.id = :id" )
	List<Projeto> findByCliente_Id(@Param("id") Long id);
	*/
	
	/*
	@Query(value = "SELECT * FROM projeto p WHERE p.cliente_id = :id" , nativeQuery = true)
	List<Projeto> findByCliente_Id(@Param("id") Long id);
	 */
}
