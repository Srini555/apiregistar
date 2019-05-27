package com.amisoft.apiregistry.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ApiRegistryResponseDelete extends ApiRegistryResponse {

    @NonNull
    private Boolean isActive;
}
