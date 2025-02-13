package com.rm.ifood_backend.repository;

import com.rm.ifood_backend.model.complement.Complement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComplementRepository extends BaseRepository<Complement>{
  List<Complement> findByProductId(UUID productId);
  Optional<Complement> findByIdAndProductId(UUID id, UUID productId);
}
