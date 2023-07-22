package com.frankmoley.lil.learningspring.webservice;


import com.frankmoley.lil.learningspring.business.ReservationService;
import com.frankmoley.lil.learningspring.business.RoomReservation;
import com.frankmoley.lil.learningspring.data.Guest;
import com.frankmoley.lil.learningspring.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    @Autowired
    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path="/reservations",method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value="date",required = false) String dateString) {
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);

    }

    @GetMapping(path = "guests")
//    @RequestMapping(path="/guests",method = RequestMethod.GET) - same as above
    public List<Guest> getGuests() {
        return this.reservationService.getGuests();
    }

    @RequestMapping(path="addGuest",method = RequestMethod.POST)
    public void addGuest(Guest guest) {
        this.reservationService.addGuest(guest);
    }

}
