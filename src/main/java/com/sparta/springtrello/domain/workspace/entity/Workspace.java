package com.sparta.springtrello.domain.workspace.entity;

import com.sparta.springtrello.common.timestamped.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Workspace extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "user_role")
//    private UserRole userRole;

//    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<BOard> boards = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

    public Workspace(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void update(String name, String description){
        this.description = description;
        this.name = name;
    }

}
