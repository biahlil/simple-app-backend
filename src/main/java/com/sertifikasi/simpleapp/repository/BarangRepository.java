package com.sertifikasi.simpleapp.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sertifikasi.simpleapp.model.Barang;

import java.util.List;

@Repository
public interface BarangRepository extends MongoRepository<Barang, Integer> {
    public Barang findByKodeBarang(int kodeBarang);
    public List<Barang> findByNamaBarang(String namaBarang);
    @Query(value = "{ 'kodeBarang' : ?0 }", delete = true)
    public Barang deleteByKodeBarang(int kodeBarang);
}