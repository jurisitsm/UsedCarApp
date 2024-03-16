package com.jurisitsm.test.repository.specificiation;

import com.jurisitsm.test.model.CarAdvertisement;
import org.springframework.data.jpa.domain.Specification;

public class CarAdvertisementSpecification {
    public static Specification<CarAdvertisement> brandContains(String brand) {
        return (root, query, cb) -> {
            if (brand == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.like(cb.lower(root.get("brand")), "%" + brand.toLowerCase() + "%");
        };
    }

    public static Specification<CarAdvertisement> typeContains(String type) {
        return (root, query, cb) -> {
            if (type == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.like(cb.lower(root.get("type")), "%" + type.toLowerCase() + "%");
        };
    }

    public static Specification<CarAdvertisement> priceEquals(Long price) {
        return (root, query, cb) -> {
            if (price == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.equal(root.get("price"), price);
        };
    }
}
