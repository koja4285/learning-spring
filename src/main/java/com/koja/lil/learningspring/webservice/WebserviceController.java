package com.koja.lil.learningspring.webservice;

import java.util.Date;
import java.util.List;

import com.koja.lil.learningspring.business.ReservationService;
import com.koja.lil.learningspring.business.RoomReservation;
import com.koja.lil.learningspring.data.Guest;
import com.koja.lil.learningspring.data.Room;
import com.koja.lil.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString) {
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @RequestMapping(path = "/guests", method = RequestMethod.GET)
    public List<Guest> getGuests() {
        return reservationService.getHotelGuests();
    }

    @PostMapping(path = "/guests")
    public Guest addGuest(@RequestBody Guest newGuest) {
        return reservationService.addGuest(newGuest);
    }

    @RequestMapping(path = "/rooms", method = RequestMethod.GET)
    public List<Room> getRooms() {
        return reservationService.getHotelRooms();
    }
}
