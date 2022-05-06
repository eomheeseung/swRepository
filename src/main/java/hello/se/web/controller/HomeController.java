package hello.se.web.controller;

import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.regex.Pattern;

@Controller
@Slf4j
public class HomeController {

    private ReservationRepository reservationRepository;

    @Autowired
    public HomeController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping()
    public String homeView() {
        log.info("first page");
        return "SW-Project-main/index";
    }

    @GetMapping("/menu")
    public String menuView() {
        log.info("menu page");
        return "SW-Project-main/menu";
    }

    @GetMapping("/book")
    public String bookView(Model model) {
        model.addAttribute("reservation", new Reservation());
        log.info("book page");
        return "SW-Project-main/book";
    }

    @GetMapping("/about")
    public String aboutView() {
        log.info("about page");
        return "SW-Project-main/about";
    }

    @GetMapping("/login_signup")
    public String loginView() {
        log.info("about page");
        return "SW-Project-main/login_signup";
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
}
