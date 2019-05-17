package org.sbelei.spannersample.entities;

import lombok.Data;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.NotMapped;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;

import java.util.List;
import java.util.UUID;

@Data
public class Lebovski {

    @PrimaryKey
    @Column(spannerTypeMaxLength = 36)
    private UUID id;

    private String quote;

    private int actorId;

}
