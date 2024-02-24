package com.sertifikasi.simpleapp.controller;

import com.sertifikasi.simpleapp.model.Barang;
import com.sertifikasi.simpleapp.repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@RestController
public class BarangController {
    
    @Autowired
    BarangRepository repo;
    
    @GetMapping("/barang")
    public ResponseEntity<?> getBarangAllBarang() {
        List<Barang> list = repo.findAll();
        return new ResponseEntity<List<Barang>>(list, HttpStatus.OK);
    }

    @GetMapping("/barang{kodeBarang}")
    public  ResponseEntity<?> getBarangByid(@RequestParam Barang kodeBarang) {
        Optional<Barang> barangOptional = repo.findById(kodeBarang.getKodeBarang());
        if (barangOptional.isPresent()) {
            return new ResponseEntity<>(barangOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        }
    }
    
    //Only Accecpt Json in body
    // Bug No 1: kodeBarang is not unique
    @PostMapping("/barang/add")
    public ResponseEntity<?> addBarang(@NonNull @RequestBody Barang barang) {
        try {
            repo.save(barang);
            return new ResponseEntity<Barang>(barang, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/barang/update/{kodeBarang}")
    public ResponseEntity<?> updateBarang(@PathVariable("kodeBarang") int kodeBarang, @RequestBody Barang barang) {
    Optional<Barang> barangOptional = repo.findById(kodeBarang);
    if (barangOptional.isPresent()) {
        Barang barangToSave = barangOptional.get();
        barangToSave.setNamaBarang(barang.getNamaBarang() != null ? barang.getNamaBarang() : barangToSave.getNamaBarang());
        barangToSave.setHarga(barang.getHarga() != 0 ? barang.getHarga() : barangToSave.getHarga());
        barangToSave.setJumlah(barang.getJumlah() != 0 ? barang.getJumlah() : barangToSave.getJumlah());
        if (barang.getKodeBarang() != -1 && barang.getKodeBarang() != kodeBarang) {
            // If kodeBarang is different and valid, delete the old entry and create a new one
            repo.deleteById(kodeBarang);
            barangToSave.setKodeBarang(barang.getKodeBarang());
            repo.save(barangToSave);
        } else {
            // If kodeBarang is the same or not valid, just update the old entry
            repo.save(barangToSave);
        }
        return new ResponseEntity<>(barangToSave, HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
}
    

    @DeleteMapping("/barang/delete/{kodeBarang}")
    public ResponseEntity<?> deleteBarang(@PathVariable("kodeBarang") int kodeBarang) {
        try {
            repo.deleteById(kodeBarang);
            return new ResponseEntity<>(kodeBarang + " Deleted",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
