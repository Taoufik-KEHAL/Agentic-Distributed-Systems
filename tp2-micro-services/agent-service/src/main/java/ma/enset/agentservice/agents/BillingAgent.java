package ma.enset.agentservice.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

/**
 * @author
 **/

@Service
public class BillingAgent {
    private final ChatClient chatClient;

    public BillingAgent(ChatClient.Builder builder,
                        ChatMemory chatMemory,
                        ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = builder.
                defaultSystem("""
                        Tu es un assistant chargé de répondre aux questions sur les clients,
                        les produits et les factures de l'entreprise.
                        Appuie-toi uniquement sur les outils fournis et sur leurs résultats.
                        Si les outils ne permettent pas de répondre, réponds exactement : "Je ne sais pas".
                        Réponds en français.
                        """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(toolCallbackProvider)
                .build();
    }

    public String chat (String conversationId,String question) {
        return chatClient.prompt()
                .user(question)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}
