package hello.se.web.service;

import hello.se.domain.DBdata.Login;
import hello.se.domain.respository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
        loginRepository.addAdmin();
    }

    public Login login(String validationId, String validationPassword) {
        return loginRepository.findByLoginId(validationId)
                .filter(l -> l.getPassword().equals(validationPassword))
                .orElse(null);
    }
}
