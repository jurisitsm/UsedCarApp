package com.jurisitsm.test.web.mapper;

import com.jurisitsm.test.model.CarAdvertisement;
import com.jurisitsm.test.web.dto.response.AdResponse;
import org.springframework.stereotype.Service;

@Service
public class AdMapper {
    public AdResponse toAdResponse(CarAdvertisement ad) {
        AdResponse adResponse = new AdResponse();
        adResponse.setId(ad.getId());
        adResponse.setBrand(ad.getBrand());
        adResponse.setType(ad.getType());
        adResponse.setDescription(ad.getDescription());
        adResponse.setPrice(ad.getPrice());
        return adResponse;
    }

}
