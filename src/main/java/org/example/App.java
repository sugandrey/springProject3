package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try(sessionFactory) {
            session.beginTransaction();
//                session.createQuery("delete from Person where age > 30").executeUpdate();

            Person person = session.get(Person.class, 1);
            System.out.println("Получили человека");
            // получим связанные сущности(Lazy)
//            System.out.println(person.getItems());
            Hibernate.initialize(person.getItems());
            Item item = session.get(Item.class, 1);
            System.out.println("Получили товар");
            //получим связанные сущности(Eager)
//            System.out.println(item.getOwner());
//            Person person = new Person("Test Cascading2", 78);
//            person.addItem(new Item("Cascading item1"));
//            person.addItem(new Item("Cascading item2"));
//            person.addItem(new Item("Cascading item3"));
//            session.save(person);
//            Person person = session.get(Person.class, 4);
//            Item item = session.get(Item.class, 1);
//            item.getOwner().getItems().remove(item);
//            item.setOwner(person);
//            person.getItems().add(item);
//            session.delete(person);
//            person.getItems().forEach(i -> i.setOwner(null));
//            List<Item> items = person.getItems();
//            for (Item item : items) {
//                session.delete(item);
//            }
//            person.getItems().clear();
//            Person person = new Person("Константин Чмоков", 44);
//            Item newItem2 = new Item("fridge", person);
//            person.setItems(new ArrayList<Item>(Collections.singletonList(newItem2)));
//            session.save(person);
//            session.save(newItem2);
//            Item newItem = new Item("vacuum", person);
//            person.getItems().add(newItem);
//            session.save(newItem);
//            System.out.println(newItem);
//            Item item = session.get(Item.class, 2);
//            System.out.println(item);
//            Person person = item.getOwner();
//            System.out.println(person);
//            Person person = session.get(Person.class, 1);
//            System.out.println(person);
//            List<Item> items = person.getItems();
//            System.out.println(items);
//            session.delete(person);
//            person.setName("Новое имя");
//            Person person1 = new Person("Иван", 30);
//            Person person2 = new Person("Степан", 35);
//            Person person3 = new Person("Никанор", 32);
//            session.save(person1);
//            session.save(person2);
//            session.save(person3);
            session.getTransaction().commit();
            System.out.println(person.getItems());
            System.out.println(item.getOwner());
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            person = (Person) session.merge(person);
            Hibernate.initialize(person.getItems());
            System.out.println("получение человека в новой сессии");
            session.getTransaction().commit();
            System.out.println("получение списка товаров после новой сессии");
            System.out.println(person.getItems());

        }
    }
}
