package com.amisoft.apiregistry.model;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ClientRegistrationResponse {

    @NonNull
    private String clientApplicationName;
    @NonNull
    private String clientApplicationOwner;
    @NonNull
    private String clientApplicationOwnerEmail;
    @NonNull
    private String registrationKey;
    @NonNull
    private String message;

    public ClientRegistrationResponse(@NonNull String message) {
        this.message = message;
    }
}
