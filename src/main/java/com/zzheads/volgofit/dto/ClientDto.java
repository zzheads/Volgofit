package com.zzheads.volgofit.dto;//

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zzheads.volgofit.model.Person.Client;
import com.zzheads.volgofit.model.Person.Trainer;
import com.zzheads.volgofit.model.Workout.Workout;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.stream.Collectors;

//  created by zzheads on 15.02.17
//
public class ClientDto extends PersonDto {
    private Workout[] workouts;

    public ClientDto(Long id, String firstName, String lastName, String photo, String birthDate, String street, String city, String country, String zipCode, String phone, String email, String[] social, Workout[] workouts) {
        super(id, firstName, lastName, photo, birthDate, street, city, country, zipCode, phone, email, social);
        this.workouts = workouts;
    }

    public ClientDto(Client client) {
        super(client);
        this.workouts = client.getWorkouts().toArray(new Workout[client.getWorkouts().size()]);
    }

    public ClientDto(String json) {
        super(json);
        ClientDto clientDto = gson.fromJson(json, ClientDto.class);
        this.workouts = clientDto.workouts;
    }

    public Workout[] getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Workout[] workouts) {
        this.workouts = workouts;
    }

    private static class ClientDtoExclusionStrategy implements ExclusionStrategy {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return (f.getName().equals("clients") && f.getDeclaringClass().equals(Workout.class))
                    || (f.getName().equals("workouts") && f.getDeclaringClass().equals(Trainer.class));
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    }

    private static Gson gson = new GsonBuilder().setExclusionStrategies(new ClientDtoExclusionStrategy()).serializeNulls().create();

    public static Collection<ClientDto> toDto(Collection<Client> clients) {
        return clients.stream().map(ClientDto::new).collect(Collectors.toList());
    }

    public static Collection<Client> fromDto(Collection<ClientDto> clientDtos) {
        return clientDtos.stream().map(Client::new).collect(Collectors.toList());
    }

    public String toJson() {
        return gson.toJson(this, ClientDto.class);
    }

    public static String toJson(Collection<ClientDto> clientDtos) {
        Type token = new TypeToken<Collection<ClientDto>>(){}.getType();
        return gson.toJson(clientDtos, token);
    }

    public static Collection<ClientDto> fromJson(String json) {
        Type token = new TypeToken<Collection<ClientDto>>(){}.getType();
        return gson.fromJson(json, token);
    }
}
