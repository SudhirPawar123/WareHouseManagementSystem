package com.jsp.warehousemanagementsystem.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagementsystem.entity.Address;
import com.jsp.warehousemanagementsystem.exception.AddressNotExistException;
import com.jsp.warehousemanagementsystem.exception.WareHouseNotFoundByIdException;
import com.jsp.warehousemanagementsystem.mapper.AddressMapper;
import com.jsp.warehousemanagementsystem.repository.AddressRepository;
import com.jsp.warehousemanagementsystem.repository.WareHouseRepository;
import com.jsp.warehousemanagementsystem.requestdtos.AddressRequest;
import com.jsp.warehousemanagementsystem.responsedtos.AddressResponse;
import com.jsp.warehousemanagementsystem.service.AddressService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private WareHouseRepository wareHouseRepository;

    @Override
    public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest, int wareHouseId) {
        return wareHouseRepository.findById(wareHouseId).map(wareHouse -> {
            Address address = addressMapper.mapAddressRequestToAddress(addressRequest, new Address());
            address.setWareHouse(wareHouse);
            
            wareHouseRepository.save(wareHouse);
            Address savedAddress = addressRepository.save(address);

          return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AddressResponse>()
                  .setStatus(HttpStatus.CREATED.value())
                  .setMessage("Address Created")
                  .setData(addressMapper.mapAddressToAddressResponse(savedAddress)));
        }).orElseThrow(() -> new WareHouseNotFoundByIdException("WareHouse Id : " + wareHouseId + ", is not exist"));
    }

    @Override
    public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest, Long addressId) {
      return addressRepository.findById(addressId).map(address->{
          Address address1 = addressMapper.mapAddressRequestToAddress(addressRequest, new Address());
          address1.setAddressId(address.getAddressId());
          address1.setWareHouse(address.getWareHouse());
          address = addressRepository.save(address1);
          return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<AddressResponse>()
                  .setStatus(HttpStatus.OK.value())
                  .setMessage("Address Updated")
                  .setData(addressMapper.mapAddressToAddressResponse(address)));
      }).orElseThrow(()->new AddressNotExistException("AddressId : "+addressId+", is not exist"));
    }

    @Override
    public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(Long addressId) {
        return addressRepository.findById(addressId).map(address->{
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<AddressResponse>()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Address Updated")
                    .setData(addressMapper.mapAddressToAddressResponse(address)));
        }).orElseThrow(()->new AddressNotExistException("AddressId : "+addressId+", is not exist"));
    }

    @Override
    public ResponseEntity<ResponseStructure<List<AddressResponse>>> addresses() {
        List<AddressResponse> listAddressResponse = addressRepository
                .findAll()
                .stream()
                .map(addressMapper::mapAddressToAddressResponse)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<List<AddressResponse>>()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Addresses Founded")
                .setData(listAddressResponse));
    }

	@Override
	public ResponseEntity<ResponseStructure<List<Map<String, Object>>>> findWareHousesByCity(String city) {
	
		  List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
	        addressRepository.findByCity(city).forEach(address->{
	        Map<String, Object> mapWareHouseRes = new HashMap<String, Object>();
	            mapWareHouseRes.put("WareHouseId", address.getWareHouse().getWareHouseId());
	            mapWareHouseRes.put("Name", address.getWareHouse().getName());
	            mapWareHouseRes.put("Address", addressMapper.mapAddressToAddressResponse(address));
	            result.add(mapWareHouseRes);
	        });
	        return ResponseEntity.status(HttpStatus.FOUND).body(
	                new ResponseStructure<List<Map<String, Object>>>()
	                .setStatus(HttpStatus.FOUND.value())
	                .setMessage("WareHouses Founded")
	                .setData(result));
	}
}