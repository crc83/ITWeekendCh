package org.sbelei.spannersample.entities.embedded;

import lombok.Data;
import org.sbelei.spannersample.entities.LebovskiActor;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Embedded;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;

import java.util.UUID;

@Data
public class LebovskiEmb {

    @PrimaryKey
    @Column(spannerTypeMaxLength = 36)
    private UUID id;

    private String quote;

    @Embedded
    private LebovskiActorEmb actor;

}
