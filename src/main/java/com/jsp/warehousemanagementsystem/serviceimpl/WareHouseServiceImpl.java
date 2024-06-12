package com.jsp.warehousemanagementsystem.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagementsystem.entity.WareHouse;
import com.jsp.warehousemanagementsystem.mapper.WareHouseMapper;
import com.jsp.warehousemanagementsystem.repository.WareHouseRepository;
import com.jsp.warehousemanagementsystem.requestdtos.WareHouseRequest;
import com.jsp.warehousemanagementsystem.responsedtos.WareHouseResponse;
import com.jsp.warehousemanagementsystem.service.WareHouseService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

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


}
