package org.sbelei.spannersample.repos;

import org.sbelei.spannersample.entities.interleaved.LebovskiIl;
import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;

import java.util.UUID;

public interface LebovskiIlRepository extends SpannerRepository<LebovskiIl, UUID> {
}
