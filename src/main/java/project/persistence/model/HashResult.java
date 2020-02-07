package project.persistence.model;

import lombok.Data;
import project.coder.HashFunction;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "hash_result")
public class HashResult {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String hashFunctionName;

    @ElementCollection
    private List<String> extensions;

    private String email;

    @ElementCollection
    private List<String> attacks;
}