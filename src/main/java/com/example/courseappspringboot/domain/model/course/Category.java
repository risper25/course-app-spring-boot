package com.example.courseappspringboot.domain.model.course;

import lombok.*;
import lombok.experimental.Tolerate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Category {
    private int category_id;
    @NonNull
    private String category_name;
}
