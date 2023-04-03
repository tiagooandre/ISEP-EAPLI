/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.eapli.demolock;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

/**
 *
 * @author arocha
 */
public class Demo2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("JPA2PU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Pessoa p1 = new Pessoa("Manuel");
        em.persist(p1);
        System.out.println(" ID gerado: " + p1.getId()); 
        em.getTransaction().commit();

        em.getTransaction().begin();
        Pessoa umaPessoa = em.find(Pessoa.class, (long)1 );
        em.lock(umaPessoa, LockModeType.OPTIMISTIC); 
        //em.lock(umaPessoa, LockModeType.PESSIMISTIC_WRITE); 
        umaPessoa.alteraNome("Antonio");           
               
        System.out.println("Uma Pausa para alterar o registo na BD");
        int read = System.in.read();
         
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}
