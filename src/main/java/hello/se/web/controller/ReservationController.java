package hello.se.web.controller;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.LoginRepository;
import hello.se.domain.respository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@Slf4j
public class ReservationController {
    private ReservationRepository reservationRepository;
    private LoginRepository loginRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository, LoginRepository loginRepository) {
        this.reservationRepository = reservationRepository;
        this.loginRepository = loginRepository;
    }

    @PostMapping("/book")
    public String addReservation(@ModelAttribute Reservation reservation) {

        log.info("reservation phone={}", reservation.getPhoneNumber());
        log.info("reservation date={}", reservation.getDate());
        log.info("reservation name={}", reservation.getName());
        log.info("reservation covers={}", reservation.getCovers());
        log.info("reservation email={}", reservation.getEmail());

        reservationRepository.save(reservation);
        return "redirect:/";
    }

    @PostMapping("/book/{key}")
    public String addLoginReservation(@PathVariable Long key, @ModelAttribute Reservation reservation, HttpServletRequest request,
                                      RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        reservation.setLoginKey(currentUser.getKey());
        reservationRepository.save(reservation);

        //확인
//        Reservation reservation1 = currentRes.getReservation();

//        log.info("login reservation", reservation1.getCovers());
        redirectAttributes.addAttribute("key", currentUser.getKey());
        return "redirect:/book/{key}";
    }
}
