import com.develhope.spring.User.entity.Role;
import com.develhope.spring.User.entity.User;
import com.develhope.spring.User.repository.UsersRepository;
import com.develhope.spring.authentication.*;
import com.develhope.spring.authentication.entities.RefreshToken;
import com.develhope.spring.authentication.DTOs.request.RefreshTokenRequest;
import com.develhope.spring.authentication.DTOs.request.SignInRequest;
import com.develhope.spring.authentication.DTOs.request.SignUpRequest;
import com.develhope.spring.authentication.DTOs.response.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UsersRepository userRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .telephonNumber(request.getTelephoneNumber())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.convertStringToRole(request.getRole())).build();

        userRepository.save(user);
        String jwt = jwtService.generateToken((UserDetails) user);
        RefreshToken refreshToken = jwtService.generateRefreshToken(user);
        return JwtAuthenticationResponse.builder().authToken(jwt).refreshToken(refreshToken.getToken()).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        //TODO Instead of only throw IllegalArgumentException maybe we could handle an error on the controller side
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var jwt = jwtService.generateToken(user);
        RefreshToken refreshToken = jwtService.generateRefreshToken(user);
        return JwtAuthenticationResponse.builder().authToken(jwt).refreshToken(refreshToken.getToken()).build();
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest request) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(request.getRefreshToken());

        if (refreshToken.isPresent() && !jwtService.isRefreshTokenExpired(refreshToken.get())) {
            //TODO Instead of only throw IllegalArgumentException maybe we could handle an error on the controller side
            var user = userRepository.findByEmail(refreshToken.get().getUserInfo().getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

            var jwt = jwtService.generateToken(user);

            return JwtAuthenticationResponse.builder().authToken(jwt).refreshToken(refreshToken.get().getToken()).build();
        } else {
            //TODO Manage errors, for now we return an empty response
            return JwtAuthenticationResponse.builder().build();
        }
    }
}