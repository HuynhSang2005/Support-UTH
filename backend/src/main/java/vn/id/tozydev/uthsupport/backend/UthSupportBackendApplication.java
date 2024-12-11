package vn.id.tozydev.uthsupport.backend;

import java.time.Instant;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.id.tozydev.uthsupport.backend.models.entities.Category;
import vn.id.tozydev.uthsupport.backend.models.entities.Comment;
import vn.id.tozydev.uthsupport.backend.models.entities.Ticket;
import vn.id.tozydev.uthsupport.backend.models.entities.User;
import vn.id.tozydev.uthsupport.backend.models.enums.UserRole;
import vn.id.tozydev.uthsupport.backend.repositories.CategoryRepository;
import vn.id.tozydev.uthsupport.backend.repositories.CommentRepository;
import vn.id.tozydev.uthsupport.backend.repositories.TicketRepository;
import vn.id.tozydev.uthsupport.backend.repositories.UserRepository;

@SpringBootApplication
public class UthSupportBackendApplication {
  public static void main(String[] args) {
    SpringApplication.run(UthSupportBackendApplication.class, args);
  }

  private static User createUser(
      PasswordEncoder passwordEncoder,
      UserRole role,
      String username,
      String email,
      String fullName) {
    var user = new User();
    user.setRole(role);
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode("Passw0rd"));
    user.setEmail(email);
    user.setFullName(fullName);
    return user;
  }

  private static Category createCategory(String name, Set<User> assignees) {
    var category = new Category();
    category.setName(name);
    category.setAssignees(assignees);
    return category;
  }

  private static Ticket createTicket(String subject, Category category, User user, Instant time) {
    var ticket = new Ticket();
    ticket.setSubject(subject);
    ticket.setCategory(category);
    ticket.setCreatedBy(user);
    ticket.setUpdatedBy(user);
    ticket.setCreatedAt(time);
    ticket.setUpdatedAt(time);
    return ticket;
  }

  private static Comment createComment(Ticket ticket, String content, User user, Instant time) {
    var comment = new Comment();
    comment.setTicket(ticket);
    comment.setContent(content);
    comment.setCreatedBy(user);
    comment.setCreatedAt(time);
    return comment;
  }

  private static Comment createComment(Ticket ticket) {
    return createComment(
        ticket, ticket.getSubject() + " comment", ticket.getCreatedBy(), ticket.getCreatedAt());
  }

  @Bean
  public CommandLineRunner commandLineRunner(
      PasswordEncoder passwordEncoder,
      UserRepository userRepository,
      CategoryRepository categoryRepository,
      TicketRepository ticketRepository,
      CommentRepository commentRepository) {
    return args -> {
      if (userRepository.count() == 0) {
        userRepository.save(
            createUser(
                passwordEncoder, UserRole.ADMIN, "admin", "admin@tozydev.id.vn", "Administrator"));
        var lecture1 =
            userRepository.save(
                createUser(
                    passwordEncoder,
                    UserRole.LECTURE,
                    "lecture_1",
                    "lecture.1@tozydev.id.vn",
                    "Lecture 1"));
        var lecture2 =
            userRepository.save(
                createUser(
                    passwordEncoder,
                    UserRole.LECTURE,
                    "lecture_2",
                    "lecture.2@tozydev.id.vn",
                    "Lecture 2"));
        var lecture3 =
            userRepository.save(
                createUser(
                    passwordEncoder,
                    UserRole.LECTURE,
                    "lecture_3",
                    "lecture.3@tozydev.id.vn",
                    "Lecture 3"));
        var student1 =
            userRepository.save(
                createUser(
                    passwordEncoder,
                    UserRole.STUDENT,
                    "student_1",
                    "student.1@tozydev.id.vn",
                    "Student 1"));
        var student2 =
            userRepository.save(
                createUser(
                    passwordEncoder,
                    UserRole.STUDENT,
                    "student_2",
                    "student.2@tozydev.id.vn",
                    "Student 2"));
        var student3 =
            userRepository.save(
                createUser(
                    passwordEncoder,
                    UserRole.STUDENT,
                    "student_3",
                    "student.3@tozydev.id.vn",
                    "Student 3"));

        var category1 =
            categoryRepository.save(createCategory("Category 1", Set.of(lecture1, lecture2)));
        var category2 =
            categoryRepository.save(createCategory("Category 2", Set.of(student1, student2)));
        var category3 =
            categoryRepository.save(createCategory("Category 3", Set.of(lecture1, student1)));

        var now = Instant.now();
        var ticket1 = ticketRepository.save(createTicket("Ticket 1", category1, lecture3, now));
        var ticket2 =
            ticketRepository.save(
                createTicket("Ticket 2", category2, lecture1, now.plusSeconds(3600)));
        var ticket3 =
            ticketRepository.save(
                createTicket("Ticket 3", category3, lecture2, now.minusSeconds(1000)));
        var ticket4 =
            ticketRepository.save(
                createTicket("Ticket 4", category1, student1, now.plusSeconds(44334)));
        var ticket5 =
            ticketRepository.save(
                createTicket("Ticket 5", category2, student3, now.minusSeconds(33223)));
        var ticket6 =
            ticketRepository.save(
                createTicket("Ticket 6", category3, student2, now.minusSeconds(3043)));

        commentRepository.save(createComment(ticket1));
        commentRepository.save(createComment(ticket2));
        commentRepository.save(createComment(ticket3));
        commentRepository.save(createComment(ticket4));
        commentRepository.save(createComment(ticket5));
        commentRepository.save(createComment(ticket6));
      }
    };
  }
}
