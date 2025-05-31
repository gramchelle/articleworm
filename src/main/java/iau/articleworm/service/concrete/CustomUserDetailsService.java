/*package iau.articleworm.service.concrete;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import iau.articleworm.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + email));
    }

    /*     @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        // Burada User entity'sini Spring Security'nin UserDetails'ine dönüştürmeliyiz.
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            AuthorityUtils.createAuthorityList(user.getRole())
        );
     // burayı yorum satırı olarak bırakıyoruz çünkü UserDetails'i doğrudan User entity'sinden dönüştürmek yerine, UserRepository'den dönen UserDetails'i kullanıyoruz. Bu, daha temiz ve anlaşılır bir kod sağlar.
}
*/