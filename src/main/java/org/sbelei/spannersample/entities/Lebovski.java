package org.sbelei.spannersample.entities;

import lombok.Data;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;

import java.util.UUID;

@Data
public class Lebovski {

    @PrimaryKey
    private UUID id;

    private String quote;
}
