package org.sbelei.spannersample.entities.embedded;

import lombok.Data;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;

@Data
public class LebovskiActorEmb {

    @PrimaryKey
    private int number;

    private String name;
}
