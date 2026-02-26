package oracleone.forumhub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import oracleone.forumhub.service.EnumPerfilTipo;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    @Enumerated(EnumType.STRING)
    private EnumPerfilTipo nome;

    @Override
    public String getAuthority() {
        return nome.name();
    }
}
