package com.zzheads.volgofit.model.Workout;

import com.zzheads.volgofit.dto.Workout.WorkoutDto;
import com.zzheads.volgofit.model.Imageable.Imageable;
import com.zzheads.volgofit.model.User.User;
import com.zzheads.volgofit.util.DateConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 /
 / Project: <volgofit>
 / created by zzheads on 10.02.17
 /
 **/

@Entity(name = "workout")
public class Workout extends Imageable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private Long id;
    private String title;
    private String description;
    private String place;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "DD/MM/YYYY HH:MM")
    private Date beginTime;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "DD/MM/YYYY HH:MM")
    private Date endTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "trainer_id")
    private User trainer;

    private String imagePath;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "workouts_clients", joinColumns = { @JoinColumn(name = "workout_id") }, inverseJoinColumns = { @JoinColumn(name = "client_id") })
    private List<User> clients;

    public Workout() {
    }

    public Workout(WorkoutDto workoutDto) {
        if (workoutDto != null) {
            this.id = workoutDto.getId();
            this.title = workoutDto.getTitle();
            this.description = workoutDto.getDescription();
            this.place = workoutDto.getPlace();
            this.imagePath = workoutDto.getImagePath();
            this.beginTime = DateConverter.stringToDate(workoutDto.getBeginTime(), true);
            this.endTime = DateConverter.stringToDate(workoutDto.getEndTime(), true);
            if (workoutDto.getTrainer() != null) {
                this.trainer = new User(workoutDto.getTrainer());
            }
            if (workoutDto.getClients() != null) {
                this.clients = Arrays.stream(workoutDto.getClients()).map(User::new).collect(Collectors.toList());
            }
        }
    }

    public Workout(Long id, String title, String description, String place, String imagePath, Date beginTime, Date endTime, User trainer, List<User> clients) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.place = place;
        this.imagePath = imagePath;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.trainer = trainer;
        this.clients = clients;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    public List<User> getClients() {
        return clients;
    }

    public void setClients(List<User> clients) {
        this.clients = clients;
    }

    public void addClient(User client) {
        clients.add(client);
    }

    public void removeClient(User client) {
        clients.remove(client);
    }

    public static Comparator<Workout> compareByDates = ( (o1, o2) -> {
        if (o1.getBeginTime().getTime() == o2.getBeginTime().getTime()) return 0;
        if (o1.getBeginTime().getTime() > o2.getBeginTime().getTime()) return 1;
        return -1;
    });

    public String toJson() {
        return (new WorkoutDto(this)).toJson();
    }

    public Workout(String json) {
        WorkoutDto workoutDto = new WorkoutDto(json);
        this.id = workoutDto.getId();
        this.title = workoutDto.getTitle();
        this.description = workoutDto.getDescription();
        this.place = workoutDto.getPlace();
        this.imagePath = workoutDto.getImagePath();
        this.beginTime = DateConverter.stringToDate(workoutDto.getBeginTime(), true);
        this.endTime = DateConverter.stringToDate(workoutDto.getEndTime(), true);
        if (workoutDto.getTrainer() != null) {
            this.trainer = new User(workoutDto.getTrainer());
        }
        if (workoutDto.getClients() != null) {
            this.clients = Arrays.stream(workoutDto.getClients()).map(User::new).collect(Collectors.toList());
        }
    }

    public static String toJson(Collection<Workout> workouts) {
        return WorkoutDto.toJson(WorkoutDto.toDto(workouts));
    }

    public static Collection<Workout> fromJson(String json) {
        return WorkoutDto.fromDto(WorkoutDto.fromJson(json));
    }
}
