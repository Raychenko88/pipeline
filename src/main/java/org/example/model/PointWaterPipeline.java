package org.example.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "points")
public class PointWaterPipeline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer x;
    private Integer y;
    private String trueFalse;
    private Integer result;

    @Override
    public String toString() {
        return getTrueFalse() + (getResult() == null ? "" : getResult().toString());
    }
}
