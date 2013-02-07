package org.springframework.webflow.samples.booking;

import net.sf.ehcache.constructs.blocking.CacheEntryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Mathilde Lemee
 */
@Service
public class MyCacheEntryFactory implements CacheEntryFactory {
  @Autowired
  public BookingService bookingService;


  @Override
  public Object createEntry(final Object key) throws Exception {
    return bookingService.findHotelById((Long)key);
  }
}
