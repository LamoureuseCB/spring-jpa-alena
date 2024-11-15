package org.example.springjpaalena.model;//package model;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import jakarta.persistence.TypedQuery;
//import model.orders.Order;
//import model.orders.OrderProduct;
//import model.orders.Status;
//import model.user.User;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class CreateOrder {
//    public static void main(String[] args) {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
//        EntityManager manager = factory.createEntityManager();
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Введите логин: ");
//        String userLogin = scanner.nextLine();
//        System.out.println("Введите пароль: ");
//        String userPassword = scanner.nextLine();
//
//        TypedQuery<User> query = manager.createQuery(("select u from User u where u.login = ?1 and u.password = ?2"), User.class);
//        query.setParameter(1, userLogin);
//        query.setParameter(2, userPassword);
//        List<User> usersLogins = query.getResultList();
//        if (usersLogins.isEmpty()) {
//            System.out.printf("Пользователь с %s не найден", userLogin);
//        }
//
//        User user = usersLogins.get(0);
//
//        TypedQuery<Product> queryProduct = manager.createQuery("select p from Product p", Product.class);
//        List<Product> productList = queryProduct.getResultList();
//        List<OrderProduct> orderProducts = new ArrayList<>();
//        double totalPrice = 0;
//
//        while (true) {
//            System.out.println("Выберите ID товара:");
//            productList.forEach(product -> System.out.println(product.getId() + "." + product.getName() + "-" + product.getPrice()));
//            String scan = scanner.nextLine().trim();
//            if (scan.isEmpty()) {
//                break;
//            }
//            try {
//                int productId = Integer.parseInt(scan);
//                Product product = manager.find(Product.class, productId);
//                System.out.println("Введите количество товара:");
//                int productQuantity = Integer.parseInt(scanner.nextLine());
//                OrderProduct orderProduct = new OrderProduct();
//                orderProduct.setProduct(product);
//                orderProduct.setQuantity(productQuantity);
//                orderProducts.add(orderProduct);
//                totalPrice += product.getPrice() * productQuantity;
//            } catch (Exception e) {
//                System.out.println("Ошибка:" + e.getMessage());
//            }
//            if (orderProducts.isEmpty()) {
//                System.out.println("Заказ не создан.Товары не выбраны");
//                return;
//            }
//        }
//
//        System.out.println("Введите адрес:");
//        String userAdress = scanner.nextLine();
//        try {
//            manager.getTransaction().begin();
//            Order order = new Order();
//            order.setStatus(Status.CREATED);
//            order.setAdress(userAdress);
//            order.setCreated(LocalDateTime.now());
//            order.setUser(user);
//
//            manager.persist(order);
//            for (OrderProduct orderProduct : orderProducts) {
//                orderProduct.setOrder(order);
//                manager.persist(orderProduct);
//            }
//            manager.getTransaction().commit();
//            System.out.println("Заказ оформлен");
//            System.out.printf("Заказчик:%s \n", userLogin);
//            for (OrderProduct orderProduct : orderProducts) {
//                System.out.printf("Товар:%s \n", orderProduct.getProduct().getName() + " - " + orderProduct.getQuantity() + "шт");
//            }
//            System.out.printf("Итоговая стоимость:%s \n", totalPrice);
//            System.out.printf("Дата оформления:%s \n", order.getCreated());
//            System.out.printf("Cтатус: %s \n", order.getStatus());
//
//        } catch (Exception e) {
//            manager.getTransaction().rollback();
//            System.out.println(e.getMessage());
//        }
//    }
//}
//
