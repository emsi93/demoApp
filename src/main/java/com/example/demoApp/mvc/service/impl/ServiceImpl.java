package com.example.demoApp.mvc.service.impl;

import com.example.demoApp.mvc.entity.Pracownik;
import com.example.demoApp.mvc.repository.PracownikRepository;
import com.example.demoApp.mvc.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ServiceImpl implements ServiceInterface {

    @Autowired
    public PracownikRepository pracownikRepository;

    @Override
    public List<Pracownik> findByImie(String imie) {
        return pracownikRepository.findByImie(imie);
    }

    @Override
    public Pracownik findById(Integer id){
        Optional<Pracownik> pracownik = pracownikRepository.findById(id);
        Pracownik prac = null;
        if(pracownik.isPresent()){
            prac = pracownik.get();
        }
        return prac;
    }

    @Override
    public void save(Pracownik pracownik) {
        pracownikRepository.save(pracownik);
    }

    @Override
    public List<Pracownik> findAll(){
        return pracownikRepository.findAll();
    }

    @Override
    public void update(Pracownik pracownik) {
        pracownikRepository.save(pracownik);
    }

    @Override
    public void delete(Integer id) {
        pracownikRepository.deleteById(id);
    }

    @Override
    public void delete(Pracownik pracownik) {
        pracownikRepository.delete(pracownik);
    }


}
