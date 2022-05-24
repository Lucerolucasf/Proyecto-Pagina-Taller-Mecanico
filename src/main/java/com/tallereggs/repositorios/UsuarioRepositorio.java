package com.tallereggs.repositorios;

import com.tallereggs.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    
//@Query("SELECT u FROM Usuario u WHERE u.id = :id")
//    public List<Usuario> buscarUsuarioPorId(@Param("id") String id);
    
@Query("SELECT u FROM Usuario u WHERE u.nombre LIKE %:nombre% ")
    public List<Usuario> buscarPorNombre(@Param("nombre") String nombre);    
    
@Query("SELECT u FROM Usuario u WHERE u.apellido LIKE %:apellido% ")
    public List<Usuario> buscarPorApellido(@Param("apellido") String apellido);    
    
@Query("SELECT u FROM Usuario u WHERE u.username LIKE :username ")
    public Usuario buscarPorUsername(@Param("username") String username);      
}
