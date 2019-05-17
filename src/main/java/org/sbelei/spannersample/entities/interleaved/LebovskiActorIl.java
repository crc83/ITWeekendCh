package org.sbelei.spannersample.entities.interleaved;

import lombok.Data;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;

import java.util.UUID;

@Data
public class LebovskiActorIl {

    @PrimaryKey
    @Column(spannerTypeMaxLength = 36)
    private UUID id;

    @PrimaryKey(keyOrder = 2)
    private int number;

    private String name;
}
