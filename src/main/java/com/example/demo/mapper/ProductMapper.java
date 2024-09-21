package com.example.demo.mapper;

import com.example.demo.domain.Lb;
import com.example.demo.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    //删、
    @Delete("delete from product where id=#{id}")
    int delete(int id);
    // 改、
    @Update("UPDATE `ebuy`.`product` SET `name` = #{name}, `price` = #{price}, `img` = #{img}, `content` = #{content}, `lbid` = #{lbid} WHERE `id` = #{id}")
    int update(Product product);
    // 增、
    @Insert("INSERT INTO `ebuy`.`product`(`name`, `price`, `img`, `content`, `lbid`) VALUES (#{name}, #{price}, #{img}, #{content}, #{lbid})")
    int insert(Product product);
    // 查
    @Select("SELECT product. *,lb.`name`as lbmc FROM `product` LEFT JOIN lb ON product.lbid = lb.id")
    List<Product> selectAll();
    @Select("SELECT * FROM `lb` WHERE `status` = 1")
    List<Lb> selectLb();
    @Select("SELECT * FROM product WHERE id =#{id}")
    Product selectOne( Integer id);
    @Select("SELECT * FROM product WHERE name LIKE concat('%', #{name}, '%')")
    List<Product> selectProductsByName(String name);
    @Select("SELECT COUNT(id) FROM product")
    int countProducts();
}
