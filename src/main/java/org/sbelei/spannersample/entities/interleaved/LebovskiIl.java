package org.sbelei.spannersample.entities.interleaved;

import lombok.Data;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Embedded;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Interleaved;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;

import java.util.List;
import java.util.UUID;

@Data
public class LebovskiIl {

    @PrimaryKey
    @Column(spannerTypeMaxLength = 36)
    private UUID id;

    private String quote;

    @Interleaved
    private List<LebovskiActorIl> actor;

}
