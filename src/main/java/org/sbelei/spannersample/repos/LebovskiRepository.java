package org.sbelei.spannersample.repos;

import org.sbelei.spannersample.entities.Lebovski;
import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;

import java.util.UUID;

public interface LebovskiRepository extends SpannerRepository<Lebovski, UUID> {
}
