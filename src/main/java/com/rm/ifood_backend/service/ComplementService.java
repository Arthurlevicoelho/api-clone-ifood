package com.rm.ifood_backend.service;

import com.rm.ifood_backend.mapper.ComplementMapper;
import com.rm.ifood_backend.model.complement.Complement;
import com.rm.ifood_backend.model.complement.ComplementResponseDTO;
import com.rm.ifood_backend.model.complement.CreateComplementDTO;
import com.rm.ifood_backend.model.complement.UpdateComplementDTO;
import com.rm.ifood_backend.repository.ComplementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplementService extends BaseService<
    Complement,
    CreateComplementDTO,
    UpdateComplementDTO,
    ComplementResponseDTO> {

  @Autowired
  private ComplementRepository complementRepository;

  private final ComplementMapper complementMapper = ComplementMapper.INSTANCE;

  @Override
  protected ComplementRepository baseRepository() {
    return complementRepository;
  }

  @Override
  protected ComplementResponseDTO toResponseDto(Complement entity) {
    return complementMapper.toResponseDto(entity);
  }

  @Override
  protected Complement toEntityFromCreateDto(CreateComplementDTO createDTO) {
    return complementMapper.toEntityFromCreateDto(createDTO);
  }

  @Override
  protected Complement toEntityFromUpdateDto(UpdateComplementDTO updateDTO) {
    return complementMapper.toEntityFromUpdateDto(updateDTO);
  }
}
