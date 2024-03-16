package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import entities.Employee;

public class Main {

    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);

        String path = "/home/lucas/eclipse-workspace/lambda5-java/in.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            
            List<Employee> employees = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(", ");
                String name = fields[0];
                String email = fields[1];
                Double salary = Double.parseDouble(fields[2]);

                employees.add(new Employee(name, email, salary));

                line = br.readLine();
            }
            
            Double baseSalary = 2000.0;

            Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

            // mostrar email dos funcionarios em ordem alfabetica cujo salario sejam maior que baseSalary 
            List<String> emails = employees.stream()
                .filter(emp -> emp.getSalary() > baseSalary)
                .map(emp -> emp.getEmail())
                .sorted(comp)
                .collect(Collectors.toList());

            // mostrar a soma dos salarios do funcionarios que começam com a letra 'M'
            Double sum = employees.stream()
                .filter(emp -> emp.getName().charAt(0) == 'M')
                .map(emp -> emp.getSalary())
                .reduce(0.0, (x,y) -> x + y);

            System.out.println("Email of people whose salary is more than " + baseSalary);

            emails.forEach(System.out::println);

            System.out.println("Sum of salary of people whose name  starts with 'M' : " +  sum);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}