package ma.enset.customerservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestController {

    @Value("${global.message}")
    private String globalMessage;

    @Value("${customer.message}")
    private String customerMessage;

    @GetMapping("/config-test")
    public Map<String, String> configTest() {
        return Map.of("global", globalMessage, "customer", customerMessage);
    }
}