package ma.enset.agentservice.web;

import ma.enset.agentservice.agents.BillingAgent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 **/

@RestController
public class AgentRestController {
     private final BillingAgent  billingAgent;

    public AgentRestController(BillingAgent billingAgent) {
        this.billingAgent = billingAgent;
    }

   @GetMapping(value="/chat", produces = MediaType.TEXT_PLAIN_VALUE)
   public String chat(@RequestParam String query,
                      @RequestParam(defaultValue = "default") String conversationId) {
       return billingAgent.chat(conversationId, query);

   }
}
