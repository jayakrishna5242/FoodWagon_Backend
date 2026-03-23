package com.foodwagon.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import io.github.cdimascio.dotenv.Dotenv;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final Dotenv dotenv;

    public WebSocketConfig() {
        this.dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        String frontendUrl = getFrontendUrl();

        registry.addEndpoint("/ws")
                .setAllowedOrigins(frontendUrl)
                .withSockJS();

        // Also add raw WebSocket endpoint for better performance
        registry.addEndpoint("/ws-raw")
                .setAllowedOrigins(frontendUrl);
    }

    private String getFrontendUrl() {
        // Try to get from environment variable first
        String envUrl = System.getenv("FRONTEND_URL");
        if (envUrl != null && !envUrl.isEmpty()) {
            return envUrl;
        }

        // Fallback to .env file
        String dotenvUrl = dotenv.get("FRONTEND_URL");


        if (dotenvUrl != null && !dotenvUrl.isEmpty()) {
            return dotenvUrl;
        }

        // Default for development
        return "http://localhost:3000";
    }
}