package com.example.demo.service;

import com.example.demo.entity.AssetType;
import com.example.demo.model.AssetTypeModel;
import com.example.demo.repository.AssetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssetTypeService {

    @Autowired
    AssetTypeRepository assetTypeRepository;

//    CREATE
    public AssetTypeModel create (AssetTypeModel asset){
        AssetType assetType = new AssetType();
            assetType.setType(asset.getType());
            assetType.setDescription(asset.getDescription());
            AssetType saved = assetTypeRepository.save(assetType);

            AssetTypeModel assetTypeModel = new AssetTypeModel();
            assetTypeModel.setId(saved.getId());
            assetTypeModel.setType(saved.getType());
            assetTypeModel.setDescription(saved.getDescription());
            return assetTypeModel;
    }
//    READ
    public AssetTypeModel getId (int id){
        AssetTypeModel assetTypeModel = new AssetTypeModel();
        Optional<AssetType> assetTypeOptional = assetTypeRepository.findById(id);
        if (assetTypeOptional.isPresent()){
            AssetType assetType = assetTypeOptional.get();
            assetTypeModel.setId(assetType.getId());
            assetTypeModel.setType(assetType.getType());
            assetTypeModel.setDescription(assetType.getDescription());
        }else {
            return null;
        }
        return assetTypeModel;
    }
    public List<AssetTypeModel> get (){
        List<AssetTypeModel> assetTypeModelList= new ArrayList<>();
        List<AssetType> assetTypeModels = assetTypeRepository.findAll();
        if (!assetTypeModels.isEmpty()){
            for (AssetType assetType : assetTypeModels){
                AssetTypeModel assetTypeModel = new AssetTypeModel();
                assetTypeModel.setId(assetType.getId());
                assetTypeModel.setType(assetType.getType());
                assetTypeModel.setDescription(assetType.getDescription());
                assetTypeModelList.add(assetTypeModel);
            }
        }
        return assetTypeModelList;
    }
//    UPDATE
    public AssetTypeModel update (int id , AssetTypeModel assetTypeModel){
        AssetType assetType = assetTypeRepository.findById(id).get();
        assetType.setId(assetTypeModel.getId());
        assetType.setType(assetTypeModel.getType());
        assetType.setDescription(assetTypeModel.getDescription());
        AssetType saved = assetTypeRepository.save(assetType);

        AssetTypeModel assetTypeModel1 = new AssetTypeModel();
        assetTypeModel1.setId(saved.getId());
        assetTypeModel1.setType(saved.getType());
        assetTypeModel1.setDescription(saved.getDescription());
        return assetTypeModel1;
    }
//    DELETE
    public void delete (int id)throws Exception{
        Optional<AssetType> assetType = assetTypeRepository.findById(id);
        if (assetType.isPresent()){
            assetTypeRepository.deleteById(id);
        }else {
            throw new Exception("Invalid_id");
        }
    }
}
