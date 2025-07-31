package com.jaavexpress.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaavexpress.user.model.Credential;

public interface CredentialRespository   extends JpaRepository<Credential, Long>{

}
