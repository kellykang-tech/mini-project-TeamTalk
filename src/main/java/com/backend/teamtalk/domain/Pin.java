package com.backend.teamtalk.domain;

import com.backend.teamtalk.dto.PinRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@ToString
@Getter @Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
                    property = "id")
public class Pin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "pin", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Card> cards;

    @ManyToOne
    @JsonBackReference
    private Board board;


    //create pin
    public Pin(PinRequestDto requestDto, Board board) {
        this.title = requestDto.getTitle();
        this.board = board;

    }

    //update pin
    public void update(PinRequestDto requestDto) {
        this.title = requestDto.getTitle();
    }
}
