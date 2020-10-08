package org.example.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "parameters")
public class WaterPipeline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer x;
    private Integer y;
    private Integer length;

    @Override
    public String toString() {
        return String.format(getX().toString(), String.format(getY().toString(), String.format(getLength().toString())));
    }
}
