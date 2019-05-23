package com.amisoft.apiregistry.model;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ApiRegistryResponse {

    @NonNull
    private String applicationName;
    @NonNull
    private String applicationOwner;
    @NonNull
    private String applicationOwnerEmail;
    @NonNull
    private String applicationApiUrl;
    @NonNull
    private String message;


    public ApiRegistryResponse(@NonNull String message) {
        this.message = message;
    }
}
