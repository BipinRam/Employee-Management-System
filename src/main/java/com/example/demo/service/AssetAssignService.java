package com.example.demo.service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetAssign;
import com.example.demo.entity.Employee;
import com.example.demo.model.AssetAssignModel;
import com.example.demo.model.AssetModel;
import com.example.demo.model.EmployeeModel;
import com.example.demo.repository.AssetAssignRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetAssignService {

    @Autowired
    AssetAssignRepository assetAssignRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AssetRepository assetRepository;

//    CREATE
    public AssetAssignModel create (AssetAssignModel assetAssignModel)throws Exception {
        List<AssetAssign> assetAssigns = assetAssignRepository.findByAssetId(assetAssignModel.getAssetId());
        AssetAssign assetAssign = new AssetAssign();
        assetAssign.setAssignDate(assetAssignModel.getAssignDate());
        assetAssign.setAssignRemarks(assetAssignModel.getAssignRemarks());

        Optional<Employee> employeeOptional = employeeRepository.findById(assetAssignModel.getEmployeeId());
        Optional<Asset> assetOptional = assetRepository.findById(assetAssignModel.getAssetId());
        if (employeeOptional.isPresent()) {
            if (assetOptional.isPresent()) {
                assetAssign.setEmployee(employeeOptional.get());
                assetAssign.setAsset(assetOptional.get());

                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setId(assetAssign.getEmployee().getId());
                employeeModel.setEmployeeFullName(assetAssign.getEmployee().getEmployeeFullName());
                employeeModel.setEmail(assetAssign.getEmployee().getEmail());
                employeeModel.setBloodGroup(assetAssign.getEmployee().getBloodGroup());
                assetAssignModel.setEmployeeModel(employeeModel);

                AssetModel assetModel = new AssetModel();
                assetModel.setId(assetAssign.getAsset().getId());
                assetModel.setSerialNo(assetAssign.getAsset().getSerialNo());
                assetModel.setModelName(assetAssign.getAsset().getModelName());
                assetModel.setManufacturingDate(assetAssign.getAsset().getManufacturingDate());
                assetModel.setSpecification(assetAssign.getAsset().getSpecification());
                assetModel.setAddedDate(assetAssign.getAsset().getAddedDate());
                assetAssignModel.setAssetModel(assetModel);
            } else {
                throw new Exception("Invalid asset id");
            }
        } else {
            throw new Exception("invalid employee id");
        }


        if (assetAssigns.isEmpty()) {
            assetAssignRepository.save(assetAssign);
        } else {

            boolean flag = false;
            for (AssetAssign a : assetAssigns) {

                if (a.getReturnDate() == null) {
                    flag = true;

                }
            }
            if (!flag) {
                assetAssignRepository.save(assetAssign);
            } else {
                throw new Exception("Asset is already assigned to another employee");
            }
        }
            assetAssignModel.setId(assetAssign.getId());
            return assetAssignModel;

    }
//    UPDATE

    public AssetAssignModel update (int id , AssetAssignModel assetAssignModel){
        AssetAssign assetAssign = assetAssignRepository.findById(id).get();
        assetAssignModel.setId(assetAssign.getId());
        assetAssignModel.setAssignDate(assetAssign.getAssignDate());
        assetAssignModel.setAssignRemarks(assetAssign.getAssignRemarks());
        assetAssignModel.setEmployeeId(assetAssign.getEmployee().getId());
        assetAssignModel.setAssetId(assetAssign.getAsset().getId());

        assetAssign.setReturnDate(assetAssignModel.getReturnDate());
        assetAssign.setReturnRemarks(assetAssignModel.getReturnRemarks());
        assetAssignRepository.save(assetAssign);

        return assetAssignModel;
    }
//    DELETE
    public void delete (int id)throws Exception{
        Optional<AssetAssign> assetAssign = assetAssignRepository.findById(id);
        if (assetAssign.isPresent()){
            assetAssignRepository.deleteById(id);
        }else {
            throw new Exception("Invalid_id");
        }
    }
    
}
