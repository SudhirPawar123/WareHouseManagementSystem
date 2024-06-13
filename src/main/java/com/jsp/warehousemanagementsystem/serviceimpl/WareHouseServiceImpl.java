package com.jsp.warehousemanagementsystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagementsystem.entity.WareHouse;
import com.jsp.warehousemanagementsystem.exception.WareHouseNotFoundByIdException;
import com.jsp.warehousemanagementsystem.mapper.WareHouseMapper;
import com.jsp.warehousemanagementsystem.repository.WareHouseRepository;
import com.jsp.warehousemanagementsystem.requestdtos.WareHouseRequest;
import com.jsp.warehousemanagementsystem.responsedtos.WareHouseResponse;
import com.jsp.warehousemanagementsystem.service.WareHouseService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class WareHouseServiceImpl implements WareHouseService{
@Autowired
private WareHouseRepository wareHouseRepository;
@Autowired
private WareHouseMapper wareHouseMapper; 

@Override
public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest) {
       WareHouse wareHouse=wareHouseMapper.mapWareHouseRequestToWareHouse(wareHouseRequest,new WareHouse());
       WareHouse saveW = wareHouseRepository.save(wareHouse);
      WareHouseResponse wareHouseResponse=wareHouseMapper.mapWareHouseToWareHouseResponse(saveW);
        return ResponseEntity.status(HttpStatus.CREATED)
        		.body(new ResponseStructure<WareHouseResponse>()
        		.setStatus(HttpStatus.CREATED.value())
        		.setMessage("WareHouse created")
        		.setData(wareHouseResponse));
}

@Override
public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(
		WareHouseRequest wareHouseRequest, int wareHouseId) {
	return wareHouseRepository.findById(wareHouseId)
			.map(wareHouse -> {
			WareHouse updatedWareHouse=	wareHouseMapper.mapWareHouseRequestToWareHouse(wareHouseRequest,new WareHouse());
			updatedWareHouse.setWareHouseId(wareHouse.getWareHouseId());
			updatedWareHouse.setAdmin(wareHouse.getAdmin());
		    WareHouse savedWareHouse=wareHouseRepository.save(updatedWareHouse);
return ResponseEntity.status(HttpStatus.OK)
		.body(new ResponseStructure<WareHouseResponse>()
		.setStatus(HttpStatus.OK.value())
		.setMessage("WareHouse updated")
		.setData(wareHouseMapper.mapWareHouseToWareHouseResponse(savedWareHouse)));
        
		}).orElseThrow(()-> new WareHouseNotFoundByIdException("warehouse is not found by id"));
}


//find WareHouse
@Override
public ResponseEntity<ResponseStructure<WareHouseResponse>> findWareHouse(@Valid int wareHouseId) {
	  return wareHouseRepository.findById(wareHouseId).map(wareHouse -> {
          return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<WareHouseResponse>()
                  .setStatus(HttpStatus.FOUND.value())
                  .setMessage("WareHouseFounded")
                  .setData(wareHouseMapper.mapWareHouseToWareHouseResponse(wareHouse)));
      }).orElseThrow(() -> new WareHouseNotFoundByIdException("WareHouse Id : " + wareHouseId + ", is not present in database"));
}

//find WareHouses
@Override
public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWareHouses() {
    List<WareHouseResponse> wareHouses = wareHouseRepository.findAll()
            .stream()
            .map(wareHouse -> wareHouseMapper.mapWareHouseToWareHouseResponse(wareHouse))
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<List<WareHouseResponse>>()
            .setStatus(HttpStatus.FOUND.value())
            .setMessage("StoreHouses Founded")
            .setData(wareHouses));
}
}
