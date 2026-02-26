package oracleone.forumhub.repository;

import oracleone.forumhub.entity.Perfil;
import oracleone.forumhub.service.EnumPerfilTipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByNome(EnumPerfilTipo nome);
}
