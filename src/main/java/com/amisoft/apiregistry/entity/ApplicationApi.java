package com.amisoft.apiregistry.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="t_app_api")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ApplicationApi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    private String applicationName;
    @NonNull
    private String applicationOwner;
    @NonNull
    private String applicationOwnerEmail;
    @NonNull
    private String applicationApiUrl;
    @NonNull
    private Boolean isActive;
}
