package com.example.khaier.entity.banner;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "banner_image")
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BannerImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private byte[] data;
    private String title;
    private String url;

}
