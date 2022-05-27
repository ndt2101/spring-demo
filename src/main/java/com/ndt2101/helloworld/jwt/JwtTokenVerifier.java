package com.ndt2101.helloworld.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
 filter thứ 2: phan loai token: neu token co key va subject phù hợp thì sẽ xử lý phân loại role, nếu không thì sẽ tra ve 403
 */
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    public JwtTokenVerifier(SecretKey secretKey, JwtConfig jwtConfig) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // lay Authorization từ header
        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

        // kiem tra token co phu hop khong
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }
        // bỏ "Bearer " ở đầu token đi
        String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
        // định dạng lại token vao object Jws<Claims>
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        // phần body cua token
        Claims body = claimsJws.getBody();
        // lấy subject của body (Principal có thể hiểu là một người, hoặc một thiết bị, hoặc một hệ thống nào đó có thể thực hiện một hành động trong ứng dụng của bạn.)
        String username = body.getSubject();
        // lấy authority của body và map sang đối tượng SimpleGrantedAuthority để dùng trong Authentication
        List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities =
                authorities.stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.get("authority")))
                        .collect(Collectors.toSet());
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
        try {
            // kiểm tra authentication
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            throw new IllegalStateException(e.getMessage());
        }

        // sang lọc tiếp theo
        filterChain.doFilter(request, response);
    }
}
