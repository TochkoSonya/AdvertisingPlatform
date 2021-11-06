package com.tochko.advertising_platform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Date createdDate;
    private Date modifiedDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="announcement_id")
    private Announcement announcement;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
