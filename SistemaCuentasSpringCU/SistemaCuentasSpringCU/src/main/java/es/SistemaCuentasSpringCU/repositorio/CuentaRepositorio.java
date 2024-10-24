package es.SistemaCuentasSpringCU.repositorio;

import es.SistemaCuentasSpringCU.modelo.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepositorio extends JpaRepository<Cuenta, Integer> {
}
