package com.nirav.modi;

import okhttp3.OkHttpClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.square.retrofit.EnableRetrofitClients;
import org.springframework.cloud.square.retrofit.core.RetrofitClient;
import org.springframework.context.annotation.Bean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;

@SpringBootApplication
@EnableRetrofitClients
public class SquareApplication {

    public static void main(String[] args) {
        SpringApplication.run(SquareApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(GreetingsClient greetingsClient) throws IOException {
        return event -> {
            for (int i = 0; i < 3; i++) {

                Call<String> greet = greetingsClient.getGreet("Hello Nirav Modi - " + i);
                String body = greet.execute().body();
                System.out.println(body);
            }
        };
    }

    @Bean
    @LoadBalanced
    OkHttpClient.Builder okBuilder() {
        return new OkHttpClient.Builder();
    }

    @RetrofitClient("greetings")
    interface GreetingsClient {

        @GET("/hello/{name}")
        public Call<String> getGreet(@Path("name") String name);
    }


}
