package kz.javaee.project.madiyevmirasitis1908.Security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kz.javaee.project.madiyevmirasitis1908.model.User;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@Stateless
public class JWTService {

  private List<String> validJWTTokens = new ArrayList();
  private String secret;
  private int JWT_TOKEN_VALIDITY = 100000;

  public String generateJWTToken(User user) {

    secret = "secret";
    String encodedString = Base64.getEncoder().encodeToString(secret.getBytes());
    String token = Jwts.builder()
        .claim("login", user.getLogin())
        .claim("password", user.getPassword())
        .claim("ROLE", user.getRole())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
        .signWith(SignatureAlgorithm.HS512, encodedString).compact();
    this.validJWTTokens.add(token);
    return token;
  }

  public String valid(String token) {
    System.out.println(token);
    System.out.println(validJWTTokens);
    if (!this.validJWTTokens.contains(token)) {
      return "not valid";
    }
    Base64.Decoder decoder = Base64.getDecoder();
    String[] chunks = token.split("\\.");
    String header = new String(decoder.decode(chunks[0]));
    String payload = new String(decoder.decode(chunks[1]));

    return payload;
  }

  public void removeToken(String token) {
    this.validJWTTokens.remove(token);
  }
}
