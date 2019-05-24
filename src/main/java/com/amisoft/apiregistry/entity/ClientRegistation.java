package com.amisoft.apiregistry.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="t_client")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ClientRegistation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    private String clientApplicationName;
    @NonNull
    private String clientApplicationOwner;
    @NonNull
    private String clientApplicationOwnerEmail;
    @NonNull
    private String applicationNameToRegister;
    @NonNull
    private String registrationKey;
}
