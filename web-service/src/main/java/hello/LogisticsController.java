package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogisticsController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/createorder")
    public Order createOrder(@RequestParam(value="address") String address) {
		
		System.out.println("New order for address: " + address);
		
        return new Order(counter.incrementAndGet(),
                            String.format(template, address));
    }
}
