package org.sbelei.spannersample.repos;

import org.sbelei.spannersample.entities.LebovskiActor;
import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;

public interface LebovskiActorRepository extends SpannerRepository<LebovskiActor, Integer> {
}
