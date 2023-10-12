package com.example.demo.service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetType;
import com.example.demo.entity.Branch;
import com.example.demo.model.AssetModel;
import com.example.demo.model.AssetTypeModel;
import com.example.demo.model.BranchModel;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.AssetTypeRepository;
import com.example.demo.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    AssetTypeRepository assetTypeRepository;

//    CREATE
    public AssetModel create (AssetModel assetModel)throws Exception{
        Asset asset = new Asset();
        asset.setSerialNo(assetModel.getSerialNo());
        asset.setModelName(assetModel.getModelName());
        asset.setManufacturingDate(assetModel.getManufacturingDate());
        asset.setSpecification(assetModel.getSpecification());

        LocalDate currentDate = LocalDate.now();
        asset.setAddedDate(currentDate);

        Optional<Branch> branchOptional = branchRepository.findById(assetModel.getBranchId());
        Optional<AssetType> assetTypeOptional = assetTypeRepository.findById(assetModel.getAssetTypeId());

        if (branchOptional.isPresent() && assetTypeOptional.isPresent()){
            asset.setBranch(branchOptional.get());
            asset.setAssetType(assetTypeOptional.get());
            assetRepository.save(asset);

            BranchModel branchModel = new BranchModel();
            branchModel.setId(asset.getBranch().getId());
            branchModel.setBranchName(asset.getBranch().getBranchName());
            branchModel.setBranchCode(asset.getBranch().getBranchCode());
            assetModel.setBranchModel(branchModel);

            AssetTypeModel assetTypeModel = new AssetTypeModel();
            assetTypeModel.setId(asset.getAssetType().getId());
            assetTypeModel.setType(asset.getAssetType().getType());
            assetTypeModel.setDescription(asset.getAssetType().getDescription());
            assetModel.setAssetTypeModel(assetTypeModel);
        }else {
            throw new Exception("Invalid branch id / assetType id");
        }
        assetModel.setId(asset.getId());
        assetModel.setAddedDate(asset.getAddedDate());
        return assetModel;
    }
//    READ
    public AssetModel getId (int id){
        AssetModel assetModel = new AssetModel();
        Optional<Asset> assetOptional = assetRepository.findById(id);
        if (assetOptional.isPresent()){
            Asset asset = assetOptional.get();
            assetModel.setId(asset.getId());
            assetModel.setSerialNo(asset.getSerialNo());
            assetModel.setModelName(asset.getModelName());
            assetModel.setManufacturingDate(asset.getManufacturingDate());
            assetModel.setSpecification(asset.getSpecification());
            assetModel.setAddedDate(asset.getAddedDate());

            BranchModel branchModel = new BranchModel();
            branchModel.setId(asset.getBranch().getId());
            branchModel.setBranchName(asset.getBranch().getBranchName());
            branchModel.setBranchCode(asset.getBranch().getBranchCode());
            assetModel.setBranchModel(branchModel);

            AssetTypeModel assetTypeModel = new AssetTypeModel();
            assetTypeModel.setId(asset.getAssetType().getId());
            assetTypeModel.setType(asset.getAssetType().getType());
            assetTypeModel.setDescription(asset.getAssetType().getDescription());
            assetModel.setAssetTypeModel(assetTypeModel);
        }else {
            return null;
        }
        return assetModel;
    }
    public List<AssetModel> getAll (){
        List<AssetModel> assetModels = new ArrayList<>();
        List<Asset> assetList = assetRepository.findAll();
        if (!assetList.isEmpty()){
            for (Asset asset : assetList){
                AssetModel assetModel = new AssetModel();
                assetModel.setId(asset.getId());
                assetModel.setSerialNo(asset.getSerialNo());
                assetModel.setModelName(asset.getModelName());
                assetModel.setManufacturingDate(asset.getManufacturingDate());
                assetModel.setSpecification(asset.getSpecification());
                assetModel.setAddedDate(asset.getAddedDate());

                BranchModel branchModel = new BranchModel();
                branchModel.setId(asset.getBranch().getId());
                branchModel.setBranchName(asset.getBranch().getBranchName());
                branchModel.setBranchCode(asset.getBranch().getBranchCode());
                assetModel.setBranchModel(branchModel);

                AssetTypeModel assetTypeModel = new AssetTypeModel();
                assetTypeModel.setId(asset.getAssetType().getId());
                assetTypeModel.setType(asset.getAssetType().getType());
                assetTypeModel.setDescription(asset.getAssetType().getDescription());
                assetModel.setAssetTypeModel(assetTypeModel);

                assetModels.add(assetModel);
            }
        }
        return assetModels;
    }
//    UPDATE
    public AssetModel update (int id , AssetModel assetModel)throws Exception{
        Asset asset = assetRepository.findById(id).get();
        asset.setId(assetModel.getId());
        asset.setSerialNo(assetModel.getSerialNo());
        asset.setModelName(assetModel.getModelName());
        asset.setManufacturingDate(assetModel.getManufacturingDate());
        asset.setSpecification(assetModel.getSpecification());
        asset.setAddedDate(asset.getAddedDate());

        Optional<Branch> branchOptional = branchRepository.findById(assetModel.getBranchId());
        Optional<AssetType> assetTypeOptional = assetTypeRepository.findById(assetModel.getAssetTypeId());

        if (branchOptional.isPresent() && assetTypeOptional.isPresent()){
            asset.setBranch(branchOptional.get());
            asset.setAssetType(assetTypeOptional.get());
            assetRepository.save(asset);

            BranchModel branchModel = new BranchModel();
            branchModel.setId(asset.getBranch().getId());
            branchModel.setBranchName(asset.getBranch().getBranchName());
            branchModel.setBranchCode(asset.getBranch().getBranchCode());
            assetModel.setBranchModel(branchModel);

            AssetTypeModel assetTypeModel= new AssetTypeModel();
            assetTypeModel.setId(asset.getAssetType().getId());
            assetTypeModel.setType(asset.getAssetType().getType());
            assetTypeModel.setDescription(asset.getAssetType().getDescription());
            assetModel.setAssetTypeModel(assetTypeModel);
        }else {
            throw  new Exception("Invalid branch id / assetType id");
        }
        assetModel.setAddedDate(asset.getAddedDate());
        return assetModel;
    }
//    DELETE
    public void delete (int id)throws Exception{
        Optional<Asset> assetOptional = assetRepository.findById(id);
        if (assetOptional.isPresent()){
            assetRepository.deleteById(id);
        }else {
            throw  new Exception("Invalid id");
        }
    }
}
