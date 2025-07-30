package com.zosh.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.zosh.domain.UserRole;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank(message = "full name is mandatory")
	    private String fullName;

	    @Column(nullable = false, unique = true)
	    @NotBlank(message = "username is mandatory")
	    private String username;

	    @Column(nullable = false, unique = true)
	    @NotBlank(message = "Email is mandatory")
	    @Email(message = "Email should be valid")
	    private String email;

	    private String phone;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private UserRole role = UserRole.CUSTOMER;

	    @Column(nullable = false, updatable = false)
	    @CreationTimestamp
	    private LocalDateTime createdAt;

	    @Column(nullable = false)
	    @UpdateTimestamp
	    private LocalDateTime updatedAt;
}
