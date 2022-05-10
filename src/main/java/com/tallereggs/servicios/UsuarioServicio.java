package com.tallereggs.servicios;

import com.tallereggs.entidades.Usuario;
import com.tallereggs.enums.EnumROL;
import com.tallereggs.repositorios.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional(rollbackFor = {Exception.class})
    public Usuario crear(String nombre, String apellido, String celular, String direccion, String username, String password, EnumROL ROL) throws Exception {

        validar(nombre, apellido, celular, direccion, username, password);
        Usuario usuario = new Usuario(); // nuevo usuario 

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCelular(celular);
        usuario.setDireccion(direccion);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setROL(EnumROL.CLIENTE);
        usuario.setAlta(Boolean.TRUE);

        return usuarioRepositorio.save(usuario);

    }

    public void validar(String nombre, String apellido, String celular, String direccion, String username, String password) throws Exception {

        if (nombre == null) {
            throw new Exception("Debe ingresar un Nombre");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new Exception("Debe ingresar un Apellido");
        }

        if (celular == null) {
            throw new Exception("Debe ingresar un numero de telefono");
        }

        if (direccion == null || direccion.isEmpty()) {
            throw new Exception("Debe ingresar una direccion");
        }

        if (username == null || username.isEmpty()) {
            throw new Exception("Debe ingresar un nombre de usuario");
        }

        List<Usuario> usuarios = buscarPorUsername(username);
        if (!usuarios.isEmpty()) {
            throw new Exception("El nombre de usuario ya existe");
        }

        if (password == null || password.isEmpty()) {
            throw new Exception("Debe ingresar una clave de usuario");
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    public void modificarUsuario(String id, String nombre, String apellido, String celular, String direccion, String username, String password) throws Exception {

        Usuario u = buscarUsuarioPorId(id);

        if (!u.getAlta()) {
            throw new Exception("El usuario está dado de baja");
        }

        validar(nombre, apellido, celular, direccion, username, password);

        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setCelular(celular);
        u.setDireccion(direccion);
        u.setUsername(username);
        u.setPassword(password);

        usuarioRepositorio.save(u);

    }

    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(String id) throws Exception {

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            return usuario;

        } else {
            throw new Exception("El usuario NO existe");
        }
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarPorNombre(String nombre) {

        List<Usuario> usuario = usuarioRepositorio.buscarPorNombre(nombre);

        return usuario;

    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarPorApellido(String apellido) {

        List<Usuario> usuario = usuarioRepositorio.buscarPorApellido(apellido);

        return usuario;

    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarPorUsername(String username) {

        List<Usuario> usuario = usuarioRepositorio.buscarPorUsername(username);

        return usuario;

    }

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {

        return usuarioRepositorio.findAll();

    }

    @Transactional(rollbackFor = Exception.class)
    public void darBajaUsuario(String id) throws Exception {

        Usuario u = buscarUsuarioPorId(id);

        if (!u.getAlta()) {
            throw new Exception("El libro ya está dado de baja");
        }

        u.setAlta(Boolean.FALSE);

        usuarioRepositorio.save(u);
    }

    @Transactional(rollbackFor = Exception.class)
    public void darAltaUsuario(String id) throws Exception {

        Usuario u = buscarUsuarioPorId(id);

        if (u.getAlta()) {
            System.out.println("El usuario ya está dado de baja");
        }

        u.setAlta(Boolean.TRUE);

        usuarioRepositorio.save(u);
    }

    @Transactional(rollbackFor = Exception.class)
    public void personalAlta(String id) throws Exception {

        Usuario u = buscarUsuarioPorId(id);

        if (u.getROL() == EnumROL.PERSONAL) {
            System.out.println("El usuario ya fue modificado como personal");
        }

        u.setROL(EnumROL.PERSONAL);

        usuarioRepositorio.save(u);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Usuario crearAdmin() throws Exception {

        Usuario usuario = new Usuario(); // Admin 

        usuario.setNombre("Vicotr");
        usuario.setApellido("Asis");
        usuario.setCelular("3512920915");
        usuario.setDireccion("Cordoba");
        usuario.setUsername("victhor");
        usuario.setPassword("tallerdevictor");
        usuario.setROL(EnumROL.ADMIN);
        usuario.setAlta(Boolean.TRUE);

        return usuarioRepositorio.save(usuario);

    }

}
