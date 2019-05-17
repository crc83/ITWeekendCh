package org.sbelei.spannersample.repos;

import org.sbelei.spannersample.entities.embedded.LebovskiEmb;
import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;

import java.util.UUID;

public interface LebovskiEmbRepository extends SpannerRepository<LebovskiEmb, UUID> {
}
