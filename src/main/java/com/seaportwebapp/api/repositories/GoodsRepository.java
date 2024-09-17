package com.seaportwebapp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaportwebapp.api.models.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
	List<Goods> findByGoodsName(String goodsName);
}
