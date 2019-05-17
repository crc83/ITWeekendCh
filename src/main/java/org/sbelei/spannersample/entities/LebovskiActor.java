package org.sbelei.spannersample.entities;

import lombok.Data;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;

@Data
public class LebovskiActor {

    @PrimaryKey
    private int id;

    private String name;
}
