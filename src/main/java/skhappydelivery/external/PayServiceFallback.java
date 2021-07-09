
package skhappydelivery.external;

/**
 * Created by uengine on 2020. 4. 18..
 */
public class PayServiceFallback implements PayService {
   @Override
    public void pay(Pay pay) {
        //do nothing if you want to forgive it

        System.out.println("Circuit breaker has been opened. Fallback returned instead.");
    }
}
