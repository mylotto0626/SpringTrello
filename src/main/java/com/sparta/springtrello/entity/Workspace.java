package com.sparta.springtrello.entity;

import com.sparta.springtrello.common.entity.Timestamped;
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

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "workspace_id")
    private List<User> members = new ArrayList<>();

    public Workspace(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void update(String name, String description){
        this.description = description;
        this.name = name;
    }

}
