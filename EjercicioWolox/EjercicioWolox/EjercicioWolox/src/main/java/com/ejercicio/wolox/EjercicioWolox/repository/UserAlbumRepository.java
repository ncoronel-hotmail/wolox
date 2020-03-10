package com.ejercicio.wolox.EjercicioWolox.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ejercicio.wolox.EjercicioWolox.entity.UserAlbum;


@Repository("userAlbumRepository")
public interface UserAlbumRepository extends JpaRepository<UserAlbum, Serializable> {
	
	
	public abstract Optional<UserAlbum> findByAlbumIdAndUserInvitedId(Integer albumId,Integer userInvitedId);
	
	
	public abstract List<UserAlbum> findByAlbumIdAndIsRead(Integer albumId,Boolean isRead);
	public abstract List<UserAlbum> findByAlbumIdAndIsReadAndIsWrite(Boolean isRead,Boolean isWrite,Integer albumId);
	public abstract List<UserAlbum> findByAlbumIdAndIsWrite(Integer albumId,Boolean isWrite);


	


	@Transactional
	@Modifying
	@Query("update  UserAlbum ua set ua.isRead =(?1), ua.isWrite=(?2) where ua.albumId=(?3) and ua.userInvitedId=(?4) ")
	public abstract Integer  updatePermisoAlbum( @Param("isRead") boolean  isRead,@Param("isWrite") boolean  isWrite,@Param("albumId") Integer  albumId,@Param("userInvitedId") Integer  userInvitedId);
	


}
