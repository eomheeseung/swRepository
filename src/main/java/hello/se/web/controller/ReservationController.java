package hello.se.web.controller;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.ResTable;
import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.LoginRepository;
import hello.se.domain.respository.ResTableRepository;
import hello.se.domain.respository.ReservationRepository;
import hello.se.web.service.ResTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class ReservationController {
    private ReservationRepository reservationRepository;
    private LoginRepository loginRepository;
    private ResTableRepository resTableRepository;
    private ResTableService resTableService;
    private Boolean isCovers = true;


    @Autowired
    public ReservationController(ReservationRepository reservationRepository, LoginRepository loginRepository,
                                 ResTableService resTableService,ResTableRepository resTableRepository) {
        this.reservationRepository = reservationRepository;
        this.loginRepository = loginRepository;
        this.resTableService = resTableService;
        this.resTableRepository = resTableRepository;
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
    public String addLoginReservation(@PathVariable Long key, @ModelAttribute Reservation reservation,
                                      BindingResult bindingResult,HttpServletRequest request,
                                      RedirectAttributes redirectAttributes,Model model) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        reservation.setLoginKey(currentUser.getKey());
        isCovers = resTableService.addIsCovers(reservation);
        reservation.setError(isCovers);

        reservationRepository.save(reservation);
        resTableService.remove(isCovers,reservation);

        /**
         * TODO
         * 맞지 않은 인원수에 대해 DB에서 삭제하고
         * 표시문구를 바꿈.
         */
        if (!isCovers) {
            model.addAttribute("reservation", new Reservation());
            model.addAttribute("login", currentUser);
            model.addAttribute("coversError", false);
            model.addAttribute("table", resTableRepository.findTable(reservation.getTable_id()));
            return "SW-Project-main/loginBook";
        }

        //확인
//        Reservation reservation1 = currentRes.getReservation();
//        log.info("login reservation", reservation1.getCovers());
        redirectAttributes.addAttribute("key", currentUser.getKey());
        return "redirect:/book/{key}";
    }
}
