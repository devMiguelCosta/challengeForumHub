package oracleone.forumhub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import oracleone.forumhub.dto.login.AutenticacaoDTO;
import oracleone.forumhub.dto.login.TokenDTO;
import oracleone.forumhub.entity.Usuario;
import oracleone.forumhub.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoController {

     private final AuthenticationManager authenticationManager;
     private final TokenService tokenService;


     @PostMapping
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid AutenticacaoDTO authDTO){
         var authToken = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.senha());
         var authentication = authenticationManager.authenticate((authToken));

         var usuario = (Usuario) authentication.getPrincipal();
         var tokenJWT = tokenService.gerarToken(usuario);

         return ResponseEntity.ok(new TokenDTO(tokenJWT));

     }
}
