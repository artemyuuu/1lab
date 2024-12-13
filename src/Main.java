
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Главное меню:");
            System.out.println("1. Посмотреть список студентов");
            System.out.println("2. Добавить студента");
            System.out.println("3. Выполнить действие ");
            System.out.println("4. Выход");
            System.out.println();

            try {
                System.out.println("Выберите действие");
                int choice = Integer.parseInt(scan.nextLine());
                switch (choice) {
                    case 1:
                        viewStudents();
                        break;
                    case 2:
                        addStudent(scan);
                        break;
                    case 3:
                        performActions(scan);
                        break;
                    case 4:

                        try (FileWriter fileWriter = new FileWriter("students.txt", true);
                             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                            Iterator<Student> iterator = students.iterator();
                            while (iterator.hasNext()) {
                                Student student = iterator.next();
                                bufferedWriter.write(student.toString());
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                            }

                        } catch (Exception e) {
                            System.out.println("Записываю список в файл");
                        }

                        System.out.println("Выход из программы...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Ошибка ввода. Пожайлуста, повторите попытку.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ощибка ввода. Введите число");
            }

        }

    }

    public static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            System.out.println("Список студентов: ");
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println();
        }
    }

    public static void addStudent(Scanner scan) {
        System.out.println("Введите имя студента");
        String name = scan.nextLine();
        String group = null;
        while (true) {
            try {
                System.out.println("Введите группу");
                group = scan.nextLine();
                if (isValidGroupNumber(group)) {
                    break;
                } else {
                    System.out.println("Неверный ввод, повторите попытку");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка. Введите верную группу");
            }
        }
        int course = 0;
        while (true) {
            try {
                System.out.println("Введите номер курса:");
                course = Integer.parseInt(scan.nextLine());
                if (course < 1 || course > 5) {
                    System.out.println("Номер курса должен быть от 1 до 5");
                } else {
                    break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Ошибка. Введите верный номер группы");
            }
        }
        double averageMark = 0.0;
        while (true) {
            try {
                System.out.println("Введите средий балл");
                averageMark = Double.parseDouble(scan.nextLine());
                if (averageMark < 2) {
                    System.out.println("Средний балл не может быть меньше 2");
                } else if (averageMark > 5) {
                    System.out.println("Средний балл не может быть больше 5");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка. Введите верный средний балл");
            }
        }
        Student student = new Student(name, group, course, averageMark);
        students.add(student);
        System.out.println("Студент добавлен");
    }

    public static boolean isValidGroupNumber(String group) {
        String regex = "^[Б]{1}\\d{2}-\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(group);
        return matcher.matches();
    }

    public static void performActions(Scanner scan) {
        boolean backToMenu = false;
        if (students.isEmpty()) {
            System.out.println("Список пуст");
            backToMenu = true;
        } else {
            while (!backToMenu) {
                System.out.println("Выберите действие:");
                System.out.println("1. Вывести список обучающихся на одном курсе");
                System.out.println("2. Удалить студентов с баллом меньше 3");
                System.out.println("3. Перевести студентов на следующий курс");
                System.out.println("4. Вернуться в главное меню");
                int choice = 0;
                try {
                    choice = Integer.parseInt(scan.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("Введите курс, студентов которого хотите увидеть: ");
                            int course = Integer.parseInt(scan.nextLine());
                            printStudents(students, course);
                            System.out.println();
                            break;
                        case 2:
                            System.out.println("Студенты, у которых средний балл ниже 3:");
                            Iterator<Student> iterator = students.iterator();
                            while (iterator.hasNext()) {
                                Student student1 = iterator.next();
                                if (student1.getAverageMark() < 3) {
                                    System.out.println(student1);
                                }
                            }
                            System.out.println("Студенты отчисляются...");
                            Thread.sleep(5000);
                            System.out.println();
                            iterator = students.iterator();
                            while (iterator.hasNext()) {
                                Student student1 = iterator.next();
                                if (student1.getAverageMark() < 3) {
                                    iterator.remove();
                                }
                            }
                            System.out.println("Студенты отчислены, поздравляю!");
                            System.out.println();
                            break;
                        case 3:
                            System.out.println("Переводим студентов на соедующий курс");
                            Iterator<Student> iterator1 = students.iterator();
                            while (iterator1.hasNext()) {
                                Student student = iterator1.next();
                                if (student.getCourse() >= 5) {
                                    System.out.println("Поздравляем," + student.getName() + "из группы" + student.getGroup() + ", вы выпустились");
                                } else {
                                    System.out.println(student.getName() + ", вы перешли на " + (student.getCourse() + 1) + " курс");
                                }
                            }
                            break;
                        case 4:
                            backToMenu = true;
                            break;
                        default:
                            System.out.println("Неведрный выбор, попробуйте снова.");
                    }


                } catch (NumberFormatException e) {
                    System.out.println();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }


    public static void printStudents(List<Student> students, int course) {
        if (students.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }

        boolean isFound = false; // Флаг, чтобы отследить наличие студентов на указанном курсе

        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student);
                isFound = true; // Найден хотя бы один студент
            }
        }

        if (!isFound) {
            System.out.println("Список студентов на указанном курсе пуст");
        }
    }
}
