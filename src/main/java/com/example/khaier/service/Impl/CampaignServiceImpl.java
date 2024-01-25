package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.CampaignRequestDto;
import com.example.khaier.dto.response.CampaignResponseDto;
import com.example.khaier.entity.Campaign;
import com.example.khaier.mapper.CampaignRequestDtoToCampaignMapper;
import com.example.khaier.mapper.CampaignToCampaignResponseDtoMapper;
import com.example.khaier.repository.CampaignRepository;
import com.example.khaier.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignRequestDtoToCampaignMapper toCampaignMapper;
    private final CampaignToCampaignResponseDtoMapper toCampaignResponseDtoMapper;
    @Override
    public void saveCampaign(CampaignRequestDto requestDto) {
        Campaign campaign=toCampaignMapper.apply(requestDto);
        campaignRepository.save(campaign);
    }

    @Override
    public List<CampaignResponseDto> findAllCampaigns() {
        List<Campaign>campaigns=campaignRepository.findAll();
        return campaigns.stream().map(toCampaignResponseDtoMapper).toList();
    }
}