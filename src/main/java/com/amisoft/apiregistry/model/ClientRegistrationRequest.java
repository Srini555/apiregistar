package com.amisoft.apiregistry.model;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ClientRegistrationRequest {

    @NonNull
    private String applicationName;
    @NonNull
    private String applicationOwner;
    @NonNull
    private String applicationOwnerEmail;
    @NonNull
    private String applicationApiUrl;

}
