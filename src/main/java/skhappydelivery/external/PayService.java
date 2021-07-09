
package skhappydelivery.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="Pay", url="http://Pay:8080", fallback = PayServiceFallback.class)
public interface PayService {

  //  @RequestMapping(method= RequestMethod.GET, path="/pays")
  @RequestMapping(method=RequestMethod.POST, value="/pays",consumes="application/json")
    public void pay(@RequestBody Pay pay);

}