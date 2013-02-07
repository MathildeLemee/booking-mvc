package org.springframework.webflow.samples.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class HotelsController {


  @Autowired
  private BookingService bookingServiceEhCache;

  @RequestMapping(value = "/hotels/search", method = RequestMethod.GET)
  public void search(SearchCriteria searchCriteria, Principal currentUser, Model model) {
    if (currentUser != null) {
      List<Booking> booking = bookingServiceEhCache.findBookings(currentUser.getName());
      model.addAttribute(booking);
    }
  }

  @RequestMapping(value = "/hotels", method = RequestMethod.GET)
  public String list(SearchCriteria criteria, Model model) {
    List<Hotel> hotels = bookingServiceEhCache.findHotels(criteria);
    model.addAttribute(hotels);
    return "hotels/list";
  }

  @RequestMapping(value = "/hotels/{id}", method = RequestMethod.GET)
  public String show(@PathVariable Long id, Model model) {
    model.addAttribute(bookingServiceEhCache.findHotelById(id));
    return "hotels/show";
  }

  @RequestMapping(value = "/bookings/{id}", method = RequestMethod.DELETE)
  public String deleteBooking(@PathVariable Long id) {
    bookingServiceEhCache.cancelBooking(id);
    return "redirect:../hotels/search";
  }

}
