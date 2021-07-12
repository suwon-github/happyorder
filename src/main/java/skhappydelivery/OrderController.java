package skhappydelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
 public class OrderController {     

    @Autowired
    private OrderService orderService;
  
    @GetMapping("/orders")
    public Iterable<Order> orderListController() throws Exception { 
        
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□ orderListController start "+System.currentTimeMillis()+"□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
	
        return  orderService.orderListService();       
    }//orderCheck

   	/*----  POST-----order 주문 들어옴 */
	@RequestMapping(value="/orderPlaced", method=RequestMethod.POST)
	public String OrderPlaced(@RequestBody OrderPlaced orderPlacedObj) throws Exception {
		
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□orderPlacedController start "+System.currentTimeMillis()+"□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
		System.out.println(" INput orderPlacedObj :  " + orderPlacedObj.toString());

    return orderService.orderPlacedService(orderPlacedObj);

	} //OrderPlaced

	@RequestMapping(value="/ordercanceled", method=RequestMethod.POST)
	public String orderCanceled(@RequestBody OrderCanceled orderCanceledObj) throws Exception {
		
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□orderPlacedController start "+System.currentTimeMillis()+"□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
		System.out.println(" INput orderCanceledObj :  " + orderCanceledObj.toString());
		
		return orderService.orderCanceledService(orderCanceledObj);
	} 






//수정




 }//classOrderController
