/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Coffee;

/**
 *
 * @author Ivan
 */
public interface DAOCoffee {
     
    Coffee get(int id);
     
    List<Coffee> getAll();
     
    void save(Coffee t);
     
    void update(Coffee t);
     
    void delete(Coffee t);
}
