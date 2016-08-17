package model.convert.product.rentalproduct;

import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.iface.RentalProduct;

import javax.persistence.AttributeConverter;

/**
 * Created by mi on 8/17/16.
 */
public class RentalProductEntityToRentalProduct implements AttributeConverter<RentalProduct,RentalProductEntity> {
    @Override
    public RentalProductEntity convertToDatabaseColumn(RentalProduct rentalProduct) {
        return (RentalProductEntity)rentalProduct;
    }

    @Override
    public RentalProduct convertToEntityAttribute(RentalProductEntity rentalProductEntity) {
        RentalProduct rentalProduct = rentalProductEntity;
        System.out.println("rentalProduct Converted");
        return rentalProduct;
    }

}
