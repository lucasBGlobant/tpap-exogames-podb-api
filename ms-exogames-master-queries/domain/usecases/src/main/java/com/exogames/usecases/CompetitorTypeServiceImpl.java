package com.exogames.usecases;

import com.exogames.commons_services.services.CommonServiceImpl;
import com.exogames.entities.CompetitorType;
import com.exogames.persistence_component.repository.CompetitorTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class CompetitorTypeServiceImpl extends CommonServiceImpl<CompetitorType, CompetitorTypeRepository> implements CompetitorTypeService {
    @Override
    public List<CompetitorType> findAll() {
        return null;
    }
}

