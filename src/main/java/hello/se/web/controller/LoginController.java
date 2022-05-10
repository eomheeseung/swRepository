package hello.se.web.controller;

import hello.se.domain.DBdata.Login;
import hello.se.domain.respository.LoginRepository;
import hello.se.web.Form.LoginForm;
import hello.se.web.Form.LoginValidationForm;
import hello.se.web.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@Slf4j
public class LoginController {
    private LoginRepository loginRepository;
    private LoginService loginService;

    @Autowired
    public LoginController(LoginRepository loginRepository, LoginService loginService) {
        this.loginRepository = loginRepository;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String enterLogin(@Validated @ModelAttribute("loginValidation") LoginValidationForm validationForm,
                             BindingResult bindingResult) {
        validationForm.increaseKey();
        if (bindingResult.hasErrors()) {
            return "SW-Project-main/login_signup";
        }
        /*Login findUser = loginRepository.findFromDB(validationForm);
        Optional<Login> wrapperLogin = Optional.of(findUser);*/

        /*if (wrapperLogin.isEmpty()) {
            log.info("not exist");
            return "redirect:/login";
        } else {
            log.info("current user={}", findUser.getId(), findUser.getUsername());
            return "redirect:/";
        }*/
        Login loginMember = loginService.login(validationForm.getId(), validationForm.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "SW-Project-main/login_signup";
        }

        return "redirect:/actionLogin";
    }

    @PostMapping("/login/register")
    public String addInfo(@ModelAttribute LoginForm loginForm) {
        loginRepository.saveWeb(loginForm);
        return "redirect:/";
    }
}
