package com.jurisitsm.test.service;

import com.jurisitsm.test.exception.UsedCarAdException;
import com.jurisitsm.test.model.AppUser;
import com.jurisitsm.test.model.CarAdvertisement;
import com.jurisitsm.test.repository.AdRepository;
import com.jurisitsm.test.repository.UserRepository;
import com.jurisitsm.test.repository.specificiation.CarAdvertisementSpecification;
import com.jurisitsm.test.web.dto.request.AdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdService {
    private static final String adPathName = "/ad/";
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdService(AdRepository adRepository, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    public String createAd(AdRequest adRequest, AppUser user){
        return adRepository.save(new CarAdvertisement(adRequest.getBrand(), adRequest.getType(),
                adRequest.getDescription(), adRequest.getPrice(), user)).getId();
    }

    public CarAdvertisement getById(String id) throws UsedCarAdException {
        return adRepository.findById(id)
                .orElseThrow(() -> new UsedCarAdException("Ad with given id could not be found: " + id,
                        HttpStatus.NOT_FOUND));
    }

    public void deleteById(String id, AppUser user) throws UsedCarAdException {
        CarAdvertisement ad = getById(id);
        if (!ad.getAuthor().getId().equals(user.getId())) {
            throw new UsedCarAdException("You cannot delete the advert of another user.",
                    HttpStatus.BAD_REQUEST);
        }
        adRepository.deleteById(id);
    }

    public List<String> searchAds(String brand, String type, Long price) {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return getAdvertismentsFiltered(brand, type, price).stream()
                .map(ad -> baseUrl + adPathName + ad.getId())
                .collect(Collectors.toList());
    }

    private List<CarAdvertisement> getAdvertismentsFiltered(String brand, String type, Long price){
        return adRepository.findAll(Specification
                .where(CarAdvertisementSpecification.brandContains(brand))
                .and(CarAdvertisementSpecification.typeContains(type))
                .and(CarAdvertisementSpecification.priceEquals(price)));
    }
}
