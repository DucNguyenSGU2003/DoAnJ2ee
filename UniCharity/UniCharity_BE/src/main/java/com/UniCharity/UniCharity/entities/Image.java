package com.UniCharity.UniCharity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "images", schema = "UniversityCharityDB", indexes = {
        @Index(name = "campaign_id", columnList = "campaign_id")
})
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @NotNull
    @Lob
    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Size(max = 50)
    @NotNull
    @Column(name = "image_type", nullable = false, length = 50)
    private String imageType;

}