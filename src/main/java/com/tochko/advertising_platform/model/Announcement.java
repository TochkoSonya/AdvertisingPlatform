package com.tochko.advertising_platform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="announcements")
public class Announcement implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String title;
    private String description;
    private AnnouncementType type;
    private Double price;
    private Period period;
    private Date createdDate;
    private Date modifiedDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "announcement", cascade=CascadeType.ALL)
    private List<Comment> comments;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
